package org.lunifera.samples.carstore.bootstrap.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Convertible;
import org.lunifera.samples.carstore.entities.general.Currency;
import org.lunifera.samples.carstore.entities.general.Length;
import org.lunifera.samples.carstore.entities.general.Pickup;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.general.RoofType;
import org.lunifera.samples.carstore.entities.general.SiType;
import org.lunifera.samples.carstore.entities.general.UOMFamily;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;
import org.lunifera.samples.carstore.entities.general.Weight;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(service = {}, enabled = true, immediate = true)
public class DBSetupComponent {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Currency eur;
	private Currency usd;
	private UnitOfMeasure kg;
	private UnitOfMeasure t;
	private UnitOfMeasure meter;
	private UnitOfMeasure liter;
	private UserTransaction ut;
	private Currency currency16;

	@Activate
	protected void activate(ComponentContext context) {

		em = emf.createEntityManager();
		try {
			ut.begin();
			em.joinTransaction();
			try {
				prepareCurrencies();
				prepareUoms();
				prepareCars();
				
				ut.commit();
			} finally {
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}

	}

	private void prepareCurrencies() {
		eur = new Currency();
		eur.setNumber("0001");
		eur.setDescription("Euro");
		eur.setIso3Code("EUR");
		em.persist(eur);

		usd = new Currency();
		usd.setNumber("0002");
		usd.setDescription("US-Dollar");
		usd.setIso3Code("USD");
		em.persist(usd);
		
		Currency cur = new Currency();
		cur.setNumber("0003");
		cur.setDescription("Currency 3");
		cur.setIso3Code("Cur 3");
		em.persist(cur);
		
		
		cur = new Currency();
		cur.setNumber("0004");
		cur.setDescription("Currency 4");
		cur.setIso3Code("Cur 4");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0005");
		cur.setDescription("Currency 5");
		cur.setIso3Code("Cur 5");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0006");
		cur.setDescription("Currency 6");
		cur.setIso3Code("Cur 6");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0007");
		cur.setDescription("Currency 7");
		cur.setIso3Code("Cur 7");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0008");
		cur.setDescription("Currency 8");
		cur.setIso3Code("Cur 8");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0009");
		cur.setDescription("Currency 9");
		cur.setIso3Code("Cur 9");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0010");
		cur.setDescription("Currency 10");
		cur.setIso3Code("Cur 10");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0011");
		cur.setDescription("Currency 11");
		cur.setIso3Code("Cur 11");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0012");
		cur.setDescription("Currency 12");
		cur.setIso3Code("Cur 12");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0013");
		cur.setDescription("Currency 13");
		cur.setIso3Code("Cur 13");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0014");
		cur.setDescription("Currency 14");
		cur.setIso3Code("Cur 14");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0015");
		cur.setDescription("Currency 15");
		cur.setIso3Code("Cur 15");
		em.persist(cur);
		
		cur = new Currency();
		cur.setNumber("0016");
		cur.setDescription("Currency 16");
		cur.setIso3Code("Cur 16");
		em.persist(cur);
		
		currency16 = cur;

	}

	private void prepareUoms() {
		kg = new UnitOfMeasure();
		kg.setNumber("0001");
		kg.setDescription("Kilogram");
		kg.setFamily(UOMFamily.WEIGHT);
		kg.setSiType(SiType.KG);
		em.persist(kg);

		t = new UnitOfMeasure();
		t.setNumber("0002");
		t.setDescription("Tonne");
		t.setFamily(UOMFamily.WEIGHT);
		em.persist(t);

		meter = new UnitOfMeasure();
		meter.setNumber("00003");
		meter.setDescription("Meter");
		meter.setFamily(UOMFamily.LENGTH);
		meter.setSiType(SiType.METER);
		em.persist(meter);

		liter = new UnitOfMeasure();
		liter.setNumber("0004");
		liter.setDescription("Liter");
		liter.setFamily(UOMFamily.VOLUME);
		liter.setSiType(SiType.LITER);
		em.persist(liter);

	}

	private void prepareCars() {
		Car volvo = new Car();
		volvo.setNumber("00001");
		volvo.setDescription("A swedish car");
		volvo.setAxes(2);
		volvo.setPrice(createPrice(10000, eur));
		volvo.setWeight(createWeight(1.73f, t));
		em.persist(volvo);

		Car bmw = new Car();
		bmw.setNumber("00002");
		bmw.setDescription("A german car");
		bmw.setAxes(2);
		bmw.setPrice(createPrice(12000, eur));
		bmw.setWeight(createWeight(2540f, kg));
		em.persist(bmw);

		// bmw with configs
		Convertible bmw_convertible = new Convertible();
		bmw_convertible.setNumber("00003");
		bmw_convertible.setDescription("A german car with soft roof");
		bmw_convertible.setAxes(2);
		bmw_convertible.setPrice(createPrice(16000, currency16));
		bmw_convertible.setWeight(createWeight(1.89f, t));
		bmw_convertible.setRoofType(RoofType.SOFT);
		em.persist(bmw_convertible);

		ConfigDetailDefinition bmw_def1 = new ConfigDetailDefinition();
		bmw_def1.setNumber("01");
		bmw_def1.setDescription("Aircondition plus");
		bmw_def1.setPrice(createPrice(700, eur));
		bmw_def1.setCar(bmw_convertible);

		ConfigDetailDefinition bmw_def2 = new ConfigDetailDefinition();
		bmw_def2.setNumber("02");
		bmw_def2.setDescription("Super control");
		bmw_def2.setPrice(createPrice(1200, usd));
		bmw_def2.setCar(bmw_convertible);

		Pickup man = new Pickup();
		man.setNumber("00004");
		man.setDescription("A pickup to carry hughe weights");
		man.setAxes(4);
		man.setPrice(createPrice(27000, usd));
		man.setWeight(createWeight(6.77f, t));
		man.setLoadingAreaLength(createLength(7.88f, meter));
		man.setLoadingAreaWidth(createLength(3.54f, meter));
		em.persist(man);

		ConfigDetailDefinition man_def1 = new ConfigDetailDefinition();
		man_def1.setNumber("01");
		man_def1.setDescription("Extra loading space");
		man_def1.setPrice(createPrice(3200, eur));
		man_def1.setCar(man);

		ConfigDetailDefinition man_def2 = new ConfigDetailDefinition();
		man_def2.setNumber("02");
		man_def2.setDescription("ABS Plus");
		man_def2.setPrice(createPrice(450, usd));
		man_def2.setCar(man);

	}

	private Price createPrice(float value, Currency currency) {
		Price price = new Price();
		price.setAmount(value);
		price.setCurrency(currency);
		return price;
	}

	private Quantity createQuantity(float value, UnitOfMeasure uom) {
		Quantity qty = new Quantity();
		qty.setAmount(value);
		qty.setUom(uom);
		return qty;
	}

	private Weight createWeight(float value, UnitOfMeasure uom) {
		Weight weight = new Weight();
		weight.setAmount(value);
		weight.setUom(uom);
		return weight;
	}

	private Length createLength(float value, UnitOfMeasure uom) {
		Length length = new Length();
		length.setAmount(value);
		length.setUom(uom);
		return length;
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, target = "(osgi.unit.name=carstore)")
	protected void bindEMF(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	protected void unbindEMF(EntityManagerFactory emf) {
		this.emf = null;
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	protected void bindTxnManager(UserTransaction ut) {
		this.ut = ut;
	}
	
	protected void unbindTxnManager(UserTransaction ut) {
		this.ut = null;
	}

	@Deactivate
	protected void deactivate() {
		emf = null;
	}

}
