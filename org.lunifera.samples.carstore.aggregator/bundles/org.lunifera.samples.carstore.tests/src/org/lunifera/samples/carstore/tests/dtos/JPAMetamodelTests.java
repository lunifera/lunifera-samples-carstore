package org.lunifera.samples.carstore.tests.dtos;

import static org.junit.Assert.fail;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.junit.Before;
import org.junit.Test;
import org.knowhowlab.osgi.testing.utils.BundleUtils;
import org.lunifera.samples.carstore.entities.general.CascadeNo;
import org.lunifera.samples.carstore.entities.general.CascadeRoot;
import org.lunifera.samples.carstore.entities.general.CascadeYes;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class JPAMetamodelTests extends AbstractJPATest {

	@Before
	public void setup() throws Exception {
		BundleUtils.startBundleAsync(getBundleContext(),
				"org.eclipse.equinox.coordinator");
	}

	@Test
	public void testMapper_DtoToEntity() throws Exception {
		setUpDatabase();

		EntityManagerImpl em = (EntityManagerImpl) emf.createEntityManager();

		SessionEventAdapter listener = new SessionEventAdapter() {
			@Override
			public void postCommitUnitOfWork(SessionEvent event) {
				RepeatableWriteUnitOfWork uow = (RepeatableWriteUnitOfWork) event
						.getSource();
				for (Object x : uow.getCloneMapping().values()) {
					System.out.println(x);
				}
			}
		};
		em.getActiveSession().getEventManager().addListener(listener);

		Transaction txn = new Transaction(em);
		txn.begin();
		CascadeNo cNo = new CascadeNo();
		CascadeRoot root = new CascadeRoot();
		root.addToRefNo(cNo);
		root.addToRefYes(new CascadeYes());
		em.persist(cNo);
		em.persist(root);
		txn.commit();

		txn.begin();
		try {
			em.remove(root);
			txn.commit();
			fail("Needs exception since CascadeNo could not be deleted -> ConstraintViolation");
		} catch (Exception e) {
			// everything is fine
		}

	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(SimpleMapperTests.class)
				.getBundleContext();
	}

	protected static class Transaction {

		private final EntityManager em;
		private boolean isJTA;
		private UserTransaction ut;
		private EntityTransaction txn;

		/**
		 * Returns true, if JTA is used.
		 * 
		 * @param em
		 * @return
		 */
		public static boolean isJTA(EntityManager em) {
			try {
				em.getTransaction();
			} catch (Exception e) {
				return true;
			}
			return false;
		}

		public Transaction(EntityManager em) {
			super();
			this.em = em;

			isJTA = isJTA(em);
		}

		public void begin() throws NamingException, NotSupportedException,
				SystemException {
			if (isJTA) {
				ut = (UserTransaction) new InitialContext()
						.lookup("osgi:service/javax.transaction.UserTransaction");

				// start the user transaction
				ut.begin();
				em.joinTransaction();
			} else {
				txn = em.getTransaction();
				txn.begin();
			}
		}

		public void commit() throws IllegalStateException, SecurityException,
				HeuristicMixedException, HeuristicRollbackException,
				RollbackException, SystemException {
			if (isJTA) {
				ut.commit();
			} else {
				txn.commit();
			}
		}

		public void rollback() throws IllegalStateException, SecurityException,
				SystemException {
			if (isJTA) {
				ut.rollback();
			} else {
				txn.rollback();
			}
		}

	}

}
