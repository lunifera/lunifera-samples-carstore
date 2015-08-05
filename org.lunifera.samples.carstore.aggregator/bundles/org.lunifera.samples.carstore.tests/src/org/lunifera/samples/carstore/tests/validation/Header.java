package org.lunifera.samples.carstore.tests.validation;

import javax.validation.constraints.NotNull;

public class Header {

	@NotNull
	private String number;

	private Child child;

	public Header(String number, Child child) {
		super();
		this.number = number;
		this.child = child;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the child
	 */
	public Child getChild() {
		return child;
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChild(Child child) {
		this.child = child;
	}

}
