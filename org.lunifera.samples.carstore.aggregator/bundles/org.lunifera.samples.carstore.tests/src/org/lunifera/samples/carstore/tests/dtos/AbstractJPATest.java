/**
 * Copyright (c) 2011 - 2014, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.lunifera.samples.carstore.tests.dtos;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

import org.lunifera.samples.carstore.tests.setup.DBSetupHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class AbstractJPATest {

	protected Map<String, Object> properties = new HashMap<String, Object>();
	protected EntityManagerFactory emf;
	protected UserTransaction ut;
	private ServiceRegistration<EntityManagerFactory> reg;

	public void setUpDatabase() throws Exception {
		// PersistenceProviderResolverHolder
		// .setPersistenceProviderResolver(new PersistenceProviderResolver() {
		// private List<PersistenceProvider> providers = new
		// ArrayList<PersistenceProvider>();
		//
		// @Override
		// public List<PersistenceProvider> getPersistenceProviders() {
		// org.eclipse.persistence.jpa.PersistenceProvider provider = new
		// org.eclipse.persistence.jpa.PersistenceProvider();
		// providers.add(provider);
		// return providers;
		// }
		//
		// @Override
		// public void clearCachedProviders() {
		// providers.clear();
		// }
		// });
		// properties.put(PersistenceUnitProperties.CLASSLOADER,
		// Currency.class.getClassLoader());
		// properties.put(PersistenceUnitProperties.WEAVING, "false");
		//
		// emf = Persistence.createEntityManagerFactory("carstore", properties);
		//
		// Hashtable<String, Object> emfProps = new Hashtable<String, Object>();
		// emfProps.put("", value)
		//
		// reg = bundle.getBundleContext().registerService(
		// EntityManagerFactory.class, emf, emfProps);

		// new approach using gemini JPA
		Bundle bundle = FrameworkUtil.getBundle(AbstractJPATest.class);
		ServiceReference<EntityManagerFactory> reference = bundle
				.getBundleContext().getServiceReference(
						EntityManagerFactory.class);
		emf = bundle.getBundleContext().getService(reference);

		ut = (UserTransaction) new InitialContext()
				.lookup("osgi:service/javax.transaction.UserTransaction");

		// create the database contents
		new DBSetupHelper(emf).setup();
	}

	public void stop() {
		if (reg != null) {
			reg.unregister();
			reg = null;
		}

		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

}
