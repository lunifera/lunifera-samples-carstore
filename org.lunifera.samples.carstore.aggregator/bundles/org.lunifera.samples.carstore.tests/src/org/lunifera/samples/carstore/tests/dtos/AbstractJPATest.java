/**
 * Copyright (c) 2011 - 2014, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.lunifera.samples.carstore.tests.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.lunifera.samples.carstore.entities.general.Currency;
import org.lunifera.samples.carstore.tests.setup.DBSetupHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

public class AbstractJPATest {

	protected Map<String, Object> properties = new HashMap<String, Object>();
	protected EntityManagerFactory emf;
	protected UserTransaction ut;
	private ServiceRegistration<EntityManagerFactory> reg;

	public void setUpDatabase() throws Exception {
		PersistenceProviderResolverHolder
				.setPersistenceProviderResolver(new PersistenceProviderResolver() {
					private List<PersistenceProvider> providers = new ArrayList<PersistenceProvider>();

					@Override
					public List<PersistenceProvider> getPersistenceProviders() {
						org.eclipse.persistence.jpa.PersistenceProvider provider = new org.eclipse.persistence.jpa.PersistenceProvider();
						providers.add(provider);
						return providers;
					}

					@Override
					public void clearCachedProviders() {
						providers.clear();
					}
				});
		properties.put(PersistenceUnitProperties.CLASSLOADER,
				Currency.class.getClassLoader());
		properties.put(PersistenceUnitProperties.WEAVING, "false");

		emf = Persistence.createEntityManagerFactory("carstore", properties);
		Bundle bundle = FrameworkUtil.getBundle(AbstractJPATest.class);
		reg = bundle.getBundleContext().registerService(
				EntityManagerFactory.class, emf, null);

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
