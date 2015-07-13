package org.lunifera.samples.carstore.tests.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.UUID;

import org.junit.After;
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

	@After
	public void teardown() throws Exception {
		stop();
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
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				runMapperAndMappingContext_DtoToEntity(true);
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);

	}

	@Test
	public void testMapper_SharedState_EntityToDto() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				runMapperAndMappingContext_EntityToDto(true);
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testServices() throws Exception {
		runServices(false);
	}

	@Test
	public void testServices_SharedState() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runServices(true);
				} catch (Exception e) {
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testUpdateDto() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runUpdateDto();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testDeleteDto() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runDeleteDto();
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testContainerPropertyMapped() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runContainerPropertyMapped();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testServices_DirtyState() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runServicesDirtyStateTest(true);
				} catch (Exception e) {
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testServices_AutomaticDirtyState() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runServicesAutomaticDirtyStateTest();
				} catch (Exception e) {
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	@Test
	public void testServices_AutomaticDirtyState_Beans() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);

		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runServicesAutomaticDirtyState_Beans();
				} catch (Exception e) {
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	/**
	 * Run mapper with and without shared state.
	 * 
	 * @param withSharedState
	 */
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
		IMapper<ConvertibleDto, Convertible> mapper = access.getToEntityMapper(
				ConvertibleDto.class, Convertible.class);

		MappingContext context = new MappingContext(withSharedState);
		// increase the level to avoid flushing the state to the shared state.
		context.increaseLevel();

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
			// decrease the level and flush the context to the shared state
			context.decreaseLevel();
			context.flush();

			ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
					.getPropertyFromCurrentCoordination(ISharedStateContext.class);
			IDataState state = sharedStateContext.getGlobalDataState();
			assertNotNull(state.get(mapper.createEntityHash(cDto)));
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
		IMapper<ConvertibleDto, Convertible> mapper = access.getToDtoMapper(
				ConvertibleDto.class, Convertible.class);

		MappingContext context = new MappingContext(withSharedState);
		// increase the level to avoid flushing the state to the shared state.
		context.increaseLevel();

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
		assertEquals(de1.getPrice().getCurrency(), de2.getPrice().getCurrency());
		assertEquals(cDto.getPrice().getCurrency(), de2.getPrice()
				.getCurrency());

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
			// decrease the level and flush the context to the shared state
			context.decreaseLevel();
			context.flush();

			ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
					.getPropertyFromCurrentCoordination(ISharedStateContext.class);
			IDataState state = sharedStateContext.getGlobalDataState();
			assertSame(state.get(mapper.createDtoHash(cEntityRoot)),
					convertibleDto);
		}
	}

	/**
	 * Demonstrates the use of DtoServices.
	 * 
	 * @param sharedState
	 * @throws Exception
	 */
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

	/**
	 * Ensures, that dtos contained in the same shared state are shared by
	 * service access.
	 * 
	 * @param sharedState
	 * @throws Exception
	 */
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
				HashUtil.createObjectWithIdHash(ConvertibleDto.class, dto),
				dirty);

		// execute query again
		ConvertibleDto dto2 = (ConvertibleDto) service.find(query).iterator()
				.next();
		assertSame(dirty, dto2);
		assertEquals("inDirtyState", dto2.getDescription());

	}

	/**
	 * Changing a property of a dto will add it automatically to the dirty state
	 * cache.
	 * 
	 * @throws Exception
	 */
	protected void runServicesAutomaticDirtyStateTest() throws Exception {
		setUpDatabase();

		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		IDataState globalState = sharedStateContext.getGlobalDataState();
		IDataState dirtyState = sharedStateContext.getDirtyState();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Query query = new Query(new LCompare.Equal("number", "00003"));
		ConvertibleDto dto = (ConvertibleDto) service.find(query).iterator()
				.next();

		// contains all retrieved and mapped DTOs (car and its config detail
		// DTO)
		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());

		dto.setDescription("Just a test");

		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		ConvertibleDto dto2 = (ConvertibleDto) service.find(query).iterator()
				.next();
		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());
		assertSame(dto, dto2);

		dto.setDescription("Another description");
		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		service.update(dto2);
		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());

		dto2.setDescription("Test 3");
		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		dto2.dispose();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

	}

	/**
	 * 
	 * @throws Exception
	 */
	protected void runServicesAutomaticDirtyState_Beans() throws Exception {
		setUpDatabase();

		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		IDataState globalState = sharedStateContext.getGlobalDataState();
		IDataState dirtyState = sharedStateContext.getDirtyState();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Query query = new Query(new LCompare.Equal("number", "00003"));
		ConvertibleDto dto = (ConvertibleDto) service.find(query).iterator()
				.next();

		// contains all retrieved and mapped DTOs (car and its config detail
		// DTO)
		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());

		// changing the bean (embeddable) will add its parent DTO to the shared
		// state
		dto.getPrice().setAmount(5000);

		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		// check that dto was added to shared state
		ConvertibleDto dto2 = (ConvertibleDto) dirtyState.get(HashUtil
				.createObjectWithIdHash(ConvertibleDto.class, dto));
		assertSame(dto, dto2);

	}

	@Test
	public void testSharedStateTest_GC() {
		ISharedStateContextProvider provider = ServiceUtils.getService(
				getBundleContext(), ISharedStateContextProvider.class);
		ISharedStateContext sharedStateContext = provider.getContext(UUID
				.randomUUID().toString(), null);
		// run the test in the shared state work
		new SharedStateUnitOfWork<Object>() {
			@Override
			protected Object doExecute() {
				try {
					runSharedStateTest_GC();
				} catch (Exception e) {
					fail(e.getMessage());
				}
				return null;
			}
		}.execute(sharedStateContext);

		provider.unget(sharedStateContext);
	}

	/**
	 * Dtos contained in the shared state will be removed if no references point
	 * to them.
	 * 
	 * @throws Exception
	 */
	protected void runSharedStateTest_GC() throws Exception {
		// TODO seems that GC does not run properly
		// setUpDatabase();
		//
		// ISharedStateContext sharedStateContext = (ISharedStateContext)
		// CoordinationManager
		// .getPropertyFromCurrentCoordination(ISharedStateContext.class);
		// IDataState globalState = sharedStateContext.getGlobalDataState();
		// IDataState dirtyState = sharedStateContext.getDirtyState();
		// assertEquals(0, globalState.size());
		// assertEquals(0, dirtyState.size());
		//
		// IDTOService<CarDto> service =
		// DtoServiceAccess.getService(CarDto.class);
		// Collection<CarDto> dtos = service.find(new Query());
		//
		// // contains all retrieved and mapped DTOs
		// assertEquals(5, globalState.size());
		// assertEquals(0, dirtyState.size());
		//
		// CarDto car1 = dtos.iterator().next();
		// car1.setDescription("Do not GC me!");
		//
		// // prepare for GC
		// dtos.clear();
		// dtos = null;
		// System.gc();
		//
		// Thread.sleep(10000);
		//
		// // contains all retrieved and mapped DTOs
		// assertEquals(1, globalState.size());
		// assertEquals(1, dirtyState.size());
		//
		// // prepare for GC 2
		// car1 = null;
		// System.gc();
		//
		// Thread.sleep(10000);
		//
		// // no records available anymore
		// assertEquals(0, globalState.size());
		// assertEquals(0, dirtyState.size());
	}

	protected void runUpdateDto() throws Exception {
		setUpDatabase();

		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		IDataState globalState = sharedStateContext.getGlobalDataState();
		IDataState dirtyState = sharedStateContext.getDirtyState();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Query query = new Query(new LCompare.Equal("number", "00003"));
		ConvertibleDto dto = (ConvertibleDto) service.find(query).iterator()
				.next();

		// contains all retrieved and mapped DTOs (convertible and the config
		// detail DTO)
		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());

		dto.setDescription("Just a test");
		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		service.update(dto);

		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());
	}

	protected void runDeleteDto() throws Exception {
		setUpDatabase();

		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		IDataState globalState = sharedStateContext.getGlobalDataState();
		IDataState dirtyState = sharedStateContext.getDirtyState();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

		IDTOService<CarDto> service = DtoServiceAccess.getService(CarDto.class);
		Query query = new Query(new LCompare.Equal("number", "00003"));
		ConvertibleDto dto = (ConvertibleDto) service.find(query).iterator()
				.next();

		// contains all retrieved and mapped DTOs (convertible and the config
		// detail DTO)
		assertEquals(2, globalState.size());
		assertEquals(0, dirtyState.size());

		dto.setDescription("Just a test");
		assertEquals(2, globalState.size());
		assertEquals(1, dirtyState.size());

		service.delete(dto);

		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());
	}

	protected void runContainerPropertyMapped() throws Exception {
		setUpDatabase();

		ISharedStateContext sharedStateContext = (ISharedStateContext) CoordinationManager
				.getPropertyFromCurrentCoordination(ISharedStateContext.class);
		IDataState globalState = sharedStateContext.getGlobalDataState();
		IDataState dirtyState = sharedStateContext.getDirtyState();
		assertEquals(0, globalState.size());
		assertEquals(0, dirtyState.size());

		// part 1: Read the detail and ensure that container is mapped
		//
		IDTOService<ConfigDetailDefinitionDto> service = DtoServiceAccess
				.getService(ConfigDetailDefinitionDto.class);
		Query query = new Query(new LCompare.Equal("number", "0001"));
		ConfigDetailDefinitionDto dto = (ConfigDetailDefinitionDto) service
				.find(query).iterator().next();

		// contains all retrieved and mapped DTOs (convertible and the config
		// detail DTO)
		assertEquals("0001", dto.getNumber());

		CarDto container = dto.getCar();
		assertTrue(container instanceof ConvertibleDto);
		assertEquals("00003", container.getNumber());

		// check that no exception is thrown
		dto.setDescription("A new one");
		service.update(dto);

		// part 2: Read the car and ensure that details are being mapped
		//
		IDTOService<CarDto> carService = DtoServiceAccess
				.getService(CarDto.class);
		Query carQuery = new Query(new LCompare.Equal("number", "00003"));
		CarDto carDto = (CarDto) carService.find(carQuery).iterator().next();

		assertEquals("00003", carDto.getNumber());
		assertEquals(1, carDto.getConfigDetails().size());

		ConfigDetailDefinitionDto newDetail = new ConfigDetailDefinitionDto();
		newDetail.setNumber("00002");
		carDto.addToConfigDetails(newDetail);

		// check that no exception is thrown
		carService.update(carDto);
		
		// part 3: now read the data again 
		//
		carDto = (CarDto) carService.find(carQuery).iterator().next();
		assertEquals("00003", carDto.getNumber());
		assertEquals(2, carDto.getConfigDetails().size());
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
