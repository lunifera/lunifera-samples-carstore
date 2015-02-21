package org.lunifera.samples.carstore.tests.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.knowhowlab.osgi.testing.utils.BundleUtils;
import org.knowhowlab.osgi.testing.utils.ServiceUtils;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.dsl.dto.lib.impl.DtoServiceAccess;
import org.lunifera.dsl.dto.lib.services.IDTOService;
import org.lunifera.dsl.dto.lib.services.Query;
import org.lunifera.dsl.dto.lib.services.filters.LCompare;
import org.lunifera.runtime.common.coordination.CoordinationManager;
import org.lunifera.runtime.common.hash.HashUtil;
import org.lunifera.runtime.common.state.IDataState;
import org.lunifera.runtime.common.state.ISharedStateContext;
import org.lunifera.runtime.common.state.ISharedStateContextProvider;
import org.lunifera.runtime.common.state.SharedStateUnitOfWork;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.dtos.general.ConvertibleDto;
import org.lunifera.samples.carstore.dtos.general.CurrencyDto;
import org.lunifera.samples.carstore.dtos.general.PickupDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.RoofType;
import org.lunifera.samples.carstore.dtos.general.SiType;
import org.lunifera.samples.carstore.dtos.general.UOMFamily;
import org.lunifera.samples.carstore.dtos.general.UnitOfMeasureDto;
import org.lunifera.samples.carstore.dtos.general.WeightDto;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Convertible;
import org.lunifera.samples.carstore.entities.general.Currency;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;
import org.lunifera.samples.carstore.entities.general.Weight;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
public class SimpleMapperTests extends AbstractJPATest {

	private CurrencyDto eurDto;
	private CurrencyDto usdDto;
	private UnitOfMeasureDto tonDto;
	private Currency eur;
	private Currency usd;
	private UnitOfMeasure ton;

	@Before
	public void setup() throws Exception {
		BundleUtils.startBundleAsync(getBundleContext(),
				"org.eclipse.equinox.coordinator");
	}

	@Test
	public void testMapper_DtoToEntity() {
		runMapperAndMappingContext_DtoToEntity(false);
	}

	@Test
	public void testMapper_EntityToDto() {
		runMapperAndMappingContext_EntityToDto(false);
	}

	@Test
	public void testMapper_SharedState_DtoToEntity() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext("0001");

		// run the test in the shared state work
		new SharedStateUnitOfWork() {
			@Override
			protected void doExecute() {
				runMapperAndMappingContext_DtoToEntity(true);
			}
		}.execute(sharedStateContext);

		provider.disposeContext(sharedStateContext);

	}

	@Test
	public void testMapper_SharedState_EntityToDto() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext("0001");

		// run the test in the shared state work
		new SharedStateUnitOfWork() {
			@Override
			protected void doExecute() {
				runMapperAndMappingContext_EntityToDto(true);
			}
		}.execute(sharedStateContext);

		provider.disposeContext(sharedStateContext);
	}

	@Test
	public void testServices() throws Exception {
		runServices(false);
	}

	@Test
	public void testServices_SharedState() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext("0001");

		// run the test in the shared state work
		new SharedStateUnitOfWork() {
			@Override
			protected void doExecute() {
				try {
					runServices(true);
				} catch (Exception e) {
					fail(e.getMessage());
				}
			}
		}.execute(sharedStateContext);

		provider.disposeContext(sharedStateContext);
	}

	@Test
	public void testServices_DirtyState() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext("0001");

		// run the test in the shared state work
		new SharedStateUnitOfWork() {
			@Override
			protected void doExecute() {
				try {
					runServicesDirtyStateTest(true);
				} catch (Exception e) {
					fail(e.getMessage());
				}
			}
		}.execute(sharedStateContext);

		provider.disposeContext(sharedStateContext);
	}

	protected void runMapperAndMappingContext_DtoToEntity(
			boolean withSharedState) {
		setupBaseDtos();

		ConvertibleDto cDto = new ConvertibleDto();
		cDto.setAxes(4);
		cDto.setColor("red");
		cDto.setDescription("Convertible VW");
		cDto.setNumber("0001");
		cDto.setPrice(createPriceDto(10000, eurDto));
		cDto.setRoofType(RoofType.SOFT);
		cDto.setWeight(createWeightDto(1.23f, tonDto));

		ConfigDetailDefinitionDto d1 = new ConfigDetailDefinitionDto();
		d1.setNumber("01");
		d1.setDescription("Config 01");
		d1.setPrice(createPriceDto(120f, eurDto));
		cDto.addToConfigDetails(d1);

		ConfigDetailDefinitionDto d2 = new ConfigDetailDefinitionDto();
		d2.setNumber("02");
		d2.setDescription("Config 02");
		d2.setPrice(createPriceDto(100f, eurDto));
		cDto.addToConfigDetails(d2);

		IMapperAccess access = ServiceUtils.getService(getBundleContext(),
				IMapperAccess.class);
		IMapper<ConvertibleDto, Convertible> mapper = access.getMapper(
				ConvertibleDto.class, Convertible.class);

		MappingContext context = new MappingContext(false);
		Convertible cEntity = new Convertible();
		mapper.mapToEntity(cDto, cEntity, context);

		assertEquals(cDto.getAxes(), cEntity.getAxes());
		assertEquals(cDto.getColor(), cEntity.getColor());
		assertEquals(cDto.getDescription(), cEntity.getDescription());
		assertEquals(cDto.getId(), cEntity.getId());
		assertEquals(cDto.getNumber(), cEntity.getNumber());
		assertEquals(cDto.getPrice().getAmount(), cEntity.getPrice()
				.getAmount(), 0);
		assertEquals(cDto.getPrice().getCurrency().getIso3Code(), cEntity
				.getPrice().getCurrency().getIso3Code());
		assertEquals(cDto.getWeight().getAmount(), cEntity.getWeight()
				.getAmount(), 0);
		assertEquals(cDto.getWeight().getUom().getNumber(), cEntity.getWeight()
				.getUom().getNumber());

		ConfigDetailDefinition de1 = cEntity.getConfigDetails().get(0);
		assertEquals(d1.getDescription(), de1.getDescription());
		assertEquals(d1.getId(), de1.getId());
		assertEquals(d1.getNumber(), de1.getNumber());
		assertEquals(d1.getPrice().getAmount(), de1.getPrice().getAmount(), 0);
		assertEquals(d1.getPrice().getCurrency().getIso3Code(), de1.getPrice()
				.getCurrency().getIso3Code());

		ConfigDetailDefinition de2 = cEntity.getConfigDetails().get(1);
		assertEquals(d2.getDescription(), de2.getDescription());
		assertEquals(d2.getId(), de2.getId());
		assertEquals(d2.getNumber(), de2.getNumber());
		assertEquals(d2.getPrice().getAmount(), de2.getPrice().getAmount(), 0);
		assertEquals(d2.getPrice().getCurrency().getIso3Code(), de2.getPrice()
				.getCurrency().getIso3Code());

		assertNotSame(de1.getPrice(), de2.getPrice());
		assertNotSame(cEntity.getPrice(), de2.getPrice());

		// ensure same price entity after mapping
		assertSame(de1.getPrice().getCurrency(), de2.getPrice().getCurrency());
		assertSame(cEntity.getPrice().getCurrency(), de2.getPrice()
				.getCurrency());

		//
		// now map again with the same context
		// Assume that the returned entities are the same objects as before
		//
		Convertible cEntity2 = new Convertible();
		mapper.mapToEntity(cDto, cEntity2, context);
		assertNotSame(cEntity2.getPrice(), cEntity.getPrice());
		assertSame(cEntity2.getPrice().getCurrency(), cEntity.getPrice()
				.getCurrency());

		assertNotSame(cEntity2.getWeight(), cEntity.getWeight());
		assertSame(cEntity2.getWeight().getUom(), cEntity.getWeight().getUom());

		ConfigDetailDefinition de12 = cEntity2.getConfigDetails().get(0);
		ConfigDetailDefinition de22 = cEntity2.getConfigDetails().get(1);
		assertSame(de1, de12);
		assertSame(de2, de22);

		//
		// test mapping context
		//
		Convertible convertibleEntity = context.get(mapper
				.createEntityHash(cDto));
		// last registered entity
		assertSame(convertibleEntity, cEntity2);

		//
		// test the shared state
		//
		if (withSharedState) {
			ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
					.getPropertyFromCurrentCoordination(ISharedStateContext.class);
			IDataState state = sharedStateContext.getGlobalDataState();
			assertNull(state.get(mapper.createEntityHash(cDto)));
		}
	}

	protected void runMapperAndMappingContext_EntityToDto(
			boolean withSharedState) {

		setupBaseEntities();

		Convertible cEntityRoot = new Convertible();
		cEntityRoot.setAxes(4);
		cEntityRoot.setColor("red");
		cEntityRoot.setDescription("Convertible VW");
		cEntityRoot.setNumber("0001");
		cEntityRoot.setPrice(createPrice(10000, eur));
		cEntityRoot
				.setRoofType(org.lunifera.samples.carstore.entities.general.RoofType.SOFT);
		cEntityRoot.setWeight(createWeight(1.23f, ton));

		ConfigDetailDefinition d1 = new ConfigDetailDefinition();
		d1.setNumber("01");
		d1.setDescription("Config 01");
		d1.setPrice(createPrice(120f, eur));
		cEntityRoot.addToConfigDetails(d1);

		ConfigDetailDefinition d2 = new ConfigDetailDefinition();
		d2.setNumber("02");
		d2.setDescription("Config 02");
		d2.setPrice(createPrice(100f, eur));
		cEntityRoot.addToConfigDetails(d2);

		IMapperAccess access = ServiceUtils.getService(getBundleContext(),
				IMapperAccess.class);
		IMapper<ConvertibleDto, Convertible> mapper = access.getMapper(
				ConvertibleDto.class, Convertible.class);

		MappingContext context = new MappingContext();
		ConvertibleDto cDto = new ConvertibleDto();
		mapper.mapToDTO(cDto, cEntityRoot, context);

		assertEquals(cEntityRoot.getAxes(), cDto.getAxes());
		assertEquals(cEntityRoot.getColor(), cDto.getColor());
		assertEquals(cEntityRoot.getDescription(), cDto.getDescription());
		assertEquals(cEntityRoot.getId(), cDto.getId());
		assertEquals(cEntityRoot.getNumber(), cDto.getNumber());
		assertEquals(cEntityRoot.getPrice().getAmount(), cDto.getPrice()
				.getAmount(), 0);
		assertEquals(cEntityRoot.getPrice().getCurrency().getIso3Code(), cDto
				.getPrice().getCurrency().getIso3Code());
		assertEquals(cEntityRoot.getWeight().getAmount(), cDto.getWeight()
				.getAmount(), 0);
		assertEquals(cEntityRoot.getWeight().getUom().getNumber(), cDto
				.getWeight().getUom().getNumber());

		ConfigDetailDefinitionDto de1 = cDto.getConfigDetails().get(0);
		assertEquals(d1.getDescription(), de1.getDescription());
		assertEquals(d1.getId(), de1.getId());
		assertEquals(d1.getNumber(), de1.getNumber());
		assertEquals(d1.getPrice().getAmount(), de1.getPrice().getAmount(), 0);
		assertEquals(d1.getPrice().getCurrency().getIso3Code(), de1.getPrice()
				.getCurrency().getIso3Code());

		ConfigDetailDefinitionDto de2 = cDto.getConfigDetails().get(1);
		assertEquals(d2.getDescription(), de2.getDescription());
		assertEquals(d2.getId(), de2.getId());
		assertEquals(d2.getNumber(), de2.getNumber());
		assertEquals(d2.getPrice().getAmount(), de2.getPrice().getAmount(), 0);
		assertEquals(d2.getPrice().getCurrency().getIso3Code(), de2.getPrice()
				.getCurrency().getIso3Code());

		assertNotSame(de1.getPrice(), de2.getPrice());
		assertNotSame(cDto.getPrice(), de2.getPrice());

		// ensure same price entity after mapping
		assertSame(de1.getPrice().getCurrency(), de2.getPrice().getCurrency());
		assertSame(cDto.getPrice().getCurrency(), de2.getPrice().getCurrency());

		//
		// now map again with the same context
		// Assume that the returned entities are the same objects as before
		//
		ConvertibleDto cDto2 = new ConvertibleDto();
		mapper.mapToDTO(cDto2, cEntityRoot, context);
		assertNotSame(cDto2.getPrice(), cDto.getPrice());
		assertSame(cDto2.getPrice().getCurrency(), cDto.getPrice()
				.getCurrency());

		assertNotSame(cDto2.getWeight(), cDto.getWeight());
		assertSame(cDto2.getWeight().getUom(), cDto.getWeight().getUom());

		ConfigDetailDefinitionDto de12 = cDto2.getConfigDetails().get(0);
		ConfigDetailDefinitionDto de22 = cDto2.getConfigDetails().get(1);
		assertSame(de1, de12);
		assertSame(de2, de22);

		//
		// test mapping context
		//
		ConvertibleDto convertibleDto = context.get(mapper
				.createDtoHash(cEntityRoot));
		// last registered dto
		assertSame(convertibleDto, cDto2);

		//
		// test the shared state
		//
		if (withSharedState) {
			ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
					.getPropertyFromCurrentCoordination(ISharedStateContext.class);
			IDataState state = sharedStateContext.getGlobalDataState();
			assertSame(state.get(mapper.createDtoHash(cEntityRoot)),
					convertibleDto);
		}
	}

	protected void runServices(boolean sharedState) throws Exception {
		setUpDatabase();

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Collection<CarDto> cars = service.find(new Query());
		for (CarDto car : cars) {
			car.setDescription("Changed convertible");
			service.update(car);

			CarDto newCar = service.get(car.getId());
			assertEquals(newCar.getId(), car.getId());
			assertEquals("Changed convertible", newCar.getDescription());

			if (sharedState) {
				// shared state returns the same instance
				assertSame(car, newCar);
			} else {
				assertNotSame(car, newCar);
			}

			if (car.getNumber().equals("00003")) {
				assertTrue(car instanceof ConvertibleDto);
				assertTrue(newCar instanceof ConvertibleDto);
			} else if (car.getNumber().equals("00004")) {
				assertTrue(car instanceof PickupDto);
				assertTrue(newCar instanceof PickupDto);
			}
		}
	}

	protected void runServicesDirtyStateTest(boolean sharedState)
			throws Exception {
		setUpDatabase();

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Query query = new Query(new LCompare.Equal("number", "00003"));
		ConvertibleDto dto = (ConvertibleDto) service.find(query).iterator()
				.next();

		// now create dto Car(id=00003) and put it in dirty state.
		ConvertibleDto dirty = new ConvertibleDto();
		dirty.setId(dto.getId());
		dirty.setDescription("inDirtyState");

		// put it into dirty state cache
		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		sharedStateContext.getDirtyState().register(
				HashUtil.createObjectWithIdHash(ConvertibleDto.class,
						dto.getId()), dirty);

		// execute query again
		ConvertibleDto dto2 = (ConvertibleDto) service.find(query).iterator()
				.next();
		assertSame(dirty, dto2);
		assertEquals("inDirtyState", dto2.getDescription());

	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(SimpleMapperTests.class)
				.getBundleContext();
	}

	protected void setupBaseDtos() {
		eurDto = new CurrencyDto();
		eurDto.setDescription("Euro");
		eurDto.setIso3Code("EUR");
		eurDto.setNumber("0001");

		usdDto = new CurrencyDto();
		usdDto.setDescription("US-Dollar");
		usdDto.setIso3Code("USD");
		usdDto.setNumber("0002");

		tonDto = new UnitOfMeasureDto();
		tonDto.setDescription("Tonne");
		tonDto.setFamily(UOMFamily.WEIGHT);
		tonDto.setNumber("0001");
		tonDto.setSiType(SiType.KG);
	}

	protected void setupBaseEntities() {
		eur = new Currency();
		eur.setDescription("Euro");
		eur.setIso3Code("EUR");
		eur.setNumber("0001");

		usd = new Currency();
		usd.setDescription("US-Dollar");
		usd.setIso3Code("USD");
		usd.setNumber("0002");

		ton = new UnitOfMeasure();
		ton.setDescription("Tonne");
		ton.setFamily(org.lunifera.samples.carstore.entities.general.UOMFamily.WEIGHT);
		ton.setNumber("0001");
		ton.setSiType(org.lunifera.samples.carstore.entities.general.SiType.KG);
	}

	protected PriceDto createPriceDto(float amount, CurrencyDto currency) {
		PriceDto cPrice = new PriceDto();
		cPrice.setAmount(amount);
		cPrice.setCurrency(currency);
		return cPrice;
	}

	protected WeightDto createWeightDto(float amount, UnitOfMeasureDto uom) {
		WeightDto weight = new WeightDto();
		weight.setAmount(amount);
		weight.setUom(uom);
		return weight;
	}

	protected Price createPrice(float amount, Currency currency) {
		Price cPrice = new Price();
		cPrice.setAmount(amount);
		cPrice.setCurrency(currency);
		return cPrice;
	}

	protected Weight createWeight(float amount, UnitOfMeasure uom) {
		Weight weight = new Weight();
		weight.setAmount(amount);
		weight.setUom(uom);
		return weight;
	}

}
