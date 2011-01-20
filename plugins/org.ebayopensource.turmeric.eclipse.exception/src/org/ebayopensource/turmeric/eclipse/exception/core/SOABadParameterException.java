/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.exception.core;

import org.ebayopensource.turmeric.eclipse.exception.AbstractSOAException;

/**
 * 
 * This is the base exception to be thrown in case if any of the API gets a bad
 * parameter. This is a very broad exception, and the framework is supposed to
 * create specialized exceptions to handle different cases
 * 
 * @author smathew
 * 
 */
public class SOABadParameterException extends AbstractSOAException {

	public SOABadParameterException() {
		super();
	}

	public SOABadParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public SOABadParameterException(String message) {
		super(message);
	}

	public SOABadParameterException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
