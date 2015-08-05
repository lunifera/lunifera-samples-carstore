package org.lunifera.samples.carstore.tests.validation;

import javax.validation.constraints.NotNull;

public class Child {

	@NotNull
	private String number;

	public Child(String number) {
		super();
		this.number = number;
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

}
