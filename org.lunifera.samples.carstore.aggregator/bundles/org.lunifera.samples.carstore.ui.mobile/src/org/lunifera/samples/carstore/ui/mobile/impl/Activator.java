package org.lunifera.samples.carstore.ui.mobile.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	// bundle is singleton
	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		// bundle is singleton
		Activator.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
	}

}
