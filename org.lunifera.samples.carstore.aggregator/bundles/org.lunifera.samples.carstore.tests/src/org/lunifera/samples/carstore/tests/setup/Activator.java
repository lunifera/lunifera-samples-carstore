/**
 * Copyright (c) 2011 - 2014, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * 		Florian Pirchner - Initial implementation
 */
package org.lunifera.samples.carstore.tests.setup;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	public BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.context = null;
	}

//	/**
//	 * Returns the entity manager factory or <code>null</code>.
//	 * 
//	 * @return
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static EntityManagerFactory getEMF() {
//		BundleContext context = FrameworkUtil.getBundle(Activator.class)
//				.getBundleContext();
//		ServiceReference[] refs;
//		try {
//			refs = context.getServiceReferences(
//					EntityManagerFactory.class.getName(),
//					"(osgi.unit.name=dbDerby)");
//			if (refs != null) {
//				return (EntityManagerFactory) context.getService(refs[0]);
//			}
//		} catch (InvalidSyntaxException e) {
//			throw new RuntimeException(e);
//		}
//
//		return null;
//	}
}
