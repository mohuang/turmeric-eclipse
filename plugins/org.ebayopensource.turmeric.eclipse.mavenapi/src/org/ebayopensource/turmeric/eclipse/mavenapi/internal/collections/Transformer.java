/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.mavenapi.internal.collections;

import java.io.Serializable;

/**
 * 
 * @author James Ervin</a>
 * 
 */
public interface Transformer<T> extends Serializable {
	public T transform(final Object input);
}
