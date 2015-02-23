package org.lunifera.samples.carstore.tests.dtos;

import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.eclipse.persistence.internal.jpa.transaction.EntityTransactionImpl;
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

		EntityTransactionImpl txn = (EntityTransactionImpl) em.getTransaction();
		txn.begin();
		CascadeNo cNo = new CascadeNo();
		CascadeRoot root = new CascadeRoot();
		root.addToRefNo(cNo);
		root.addToRefYes(new CascadeYes());
		em.persist(cNo);
		em.persist(root);
		txn.commit();

		txn = (EntityTransactionImpl) em.getTransaction();
		txn.begin();
		em.remove(root);
		txn.commit();

	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(SimpleMapperTests.class)
				.getBundleContext();
	}

}
