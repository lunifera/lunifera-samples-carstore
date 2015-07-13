package org.lunifera.samples.carstore.tests.setup;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Convertible;
import org.lunifera.samples.carstore.entities.general.Length;
import org.lunifera.samples.carstore.entities.general.Pickup;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.RoofType;
import org.lunifera.samples.carstore.entities.general.SiType;
import org.lunifera.samples.carstore.entities.general.UOMFamily;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;
import org.lunifera.samples.carstore.entities.general.Weight;

public class DBSetupHelper {

	private final EntityManagerFactory emf;
	private final UserTransaction ut;
	private EntityManager em;

	public DBSetupHelper(EntityManagerFactory emf) throws NamingException {
		super();
		this.emf = emf;

		ut = (UserTransaction) new InitialContext()
				.lookup("osgi:service/javax.transaction.UserTransaction");
	}

	public void setup() throws NotSupportedException, SystemException,
			IllegalStateException, SecurityException, HeuristicMixedException,
			HeuristicRollbackException, RollbackException {

		em = emf.createEntityManager();
		ut.begin();
		em.joinTransaction();
		try {
			prepareUoms();
			prepareCars();
			ut.commit();
		} finally {
		}
	}

	private void prepareUoms() {
		
		Query q1 = em.createQuery("DELETE FROM UnitOfMeasure");
		q1.executeUpdate();
		
		UnitOfMeasure kg = new UnitOfMeasure();
		kg.setNumber("0001");
		kg.setDescription("Kilogram");
		kg.setFamily(UOMFamily.WEIGHT);
		kg.setSiType(SiType.KG);
		em.persist(kg);

		UnitOfMeasure t = new UnitOfMeasure();
		t.setNumber("0002");
		t.setDescription("Tonne");
		t.setFamily(UOMFamily.WEIGHT);
		em.persist(t);

		UnitOfMeasure meter = new UnitOfMeasure();
		meter.setNumber("00003");
		meter.setDescription("Meter");
		meter.setFamily(UOMFamily.LENGTH);
		meter.setSiType(SiType.METER);
		em.persist(meter);

		UnitOfMeasure liter = new UnitOfMeasure();
		liter.setNumber("0004");
		liter.setDescription("Liter");
		liter.setFamily(UOMFamily.VOLUME);
		liter.setSiType(SiType.LITER);
		em.persist(liter);

	}

	private void prepareCars() {
		
		Query q1 = em.createQuery("DELETE FROM ConfigDetailDefinition");
		q1.executeUpdate();
		Query q2 = em.createQuery("DELETE FROM Convertible");
		q2.executeUpdate();
		Query q3 = em.createQuery("DELETE FROM Pickup");
		q3.executeUpdate();
		Query q4 = em.createQuery("DELETE FROM Car");
		q4.executeUpdate();
		
		Car volvo = new Car();
		volvo.setNumber("00001");
		volvo.setDescription("A swedish car");
		volvo.setAxes(2);
		volvo.setPrice(createPrice(10000, "EUR"));
		volvo.setWeight(createWeight(1.73f, "Tons"));
		em.persist(volvo);

		Car bmw = new Car();
		bmw.setNumber("00002");
		bmw.setDescription("A german car");
		bmw.setAxes(2);
		bmw.setPrice(createPrice(12000, "EUR"));
		bmw.setWeight(createWeight(2.54f, "Tons"));
		em.persist(bmw);

		Convertible bmw_convertible = new Convertible();
		bmw_convertible.setNumber("00003");
		bmw_convertible.setDescription("A german car with soft roof");
		bmw_convertible.setAxes(2);
		bmw_convertible.setPrice(createPrice(16000, "EUR"));
		bmw_convertible.setWeight(createWeight(1.89f, "Tons"));
		bmw_convertible.setRoofType(RoofType.SOFT);

		ConfigDetailDefinition bmw_Detail1 = new ConfigDetailDefinition();
		bmw_Detail1.setCar(bmw_convertible);
		bmw_Detail1.setNumber("0001");
		bmw_Detail1.setPrice(createPrice(700, "EUR"));

		em.persist(bmw_convertible);

		Pickup man = new Pickup();
		man.setNumber("00004");
		man.setDescription("A pickup to carry hughe weights");
		man.setAxes(4);
		man.setPrice(createPrice(27000, "EUR"));
		man.setWeight(createWeight(6.77f, "Tons"));
		man.setLoadingAreaLength(createLength(7.88f, "Meters"));
		man.setLoadingAreaWidth(createLength(3.54f, "Meters"));
		em.persist(man);

	}

	private Price createPrice(float value, String currency) {
		Price price = new Price();
		price.setAmount(value);
		return price;
	}

	private Weight createWeight(float value, String uom) {
		Weight weight = new Weight();
		weight.setAmount(value);
		return weight;
	}

	private Length createLength(float value, String currency) {
		Length length = new Length();
		length.setAmount(value);
		return length;
	}

}
