package org.lunifera.samples.carstore.tests;

import org.junit.Before;
import org.knowhowlab.osgi.testing.utils.ServiceUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public abstract class AbstractTestClass {

	@Before
	public void setup() {

	}

	protected BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

	/**
	 * Returns an instance of the requested OSGi service, or <code>null</code>
	 * if no service could be found after 500ms.
	 * 
	 * @param clazz
	 * @return
	 */
	protected <A> A getService(Class<A> clazz) {
		return ServiceUtils.getService(getBundleContext(), clazz, 500);
	}

}
