package org.lunifera.samples.carstore.tests.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.naming.InitialContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.knowhowlab.osgi.testing.utils.BundleUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class JavaxValidationTests {

	protected ValidatorFactory validationFactory;

	@Before
	public void setup() throws Exception {
		BundleUtils.startBundleAsync(getBundleContext(),
				"org.lunifera.runtime.jsr303.validation");

		validationFactory = (ValidatorFactory) new InitialContext()
				.lookup("osgi:service/javax.validation.ValidatorFactory");
	}

	@Test
	public void testValidationAPIWithJPA() throws Exception {
		// UnitOfMeasure uom = new UnitOfMeasure();
		// Set<ConstraintViolation<UnitOfMeasure>> result = validationFactory
		// .getValidator().validate(uom, new Class[0]);
		// assertEquals(2, result.size());
	}

	@Test
	public void testValidationAPI() throws Exception {
		Child child = new Child(null);
		Header header = new Header(null, child);

		Set<ConstraintViolation<Header>> result = validationFactory
				.getValidator().validate(header, new Class[0]);
		assertEquals(1, result.size());

		Set<ConstraintViolation<Child>> result2 = validationFactory
				.getValidator().validate(child, new Class[0]);
		assertEquals(1, result2.size());
	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(JavaxValidationTests.class)
				.getBundleContext();
	}

}
