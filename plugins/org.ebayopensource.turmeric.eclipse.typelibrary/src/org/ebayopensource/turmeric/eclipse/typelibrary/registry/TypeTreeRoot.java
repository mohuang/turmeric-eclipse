/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.typelibrary.registry;

import org.ebayopensource.turmeric.tools.library.SOATypeRegistry;

/**
 * @author smathew
 *
 */
public class TypeTreeRoot extends AbstractRegistryTreeNode{

	public TypeTreeRoot(SOATypeRegistry typeRegistry){
		super((IRegistryTreeNode)null, typeRegistry);
	}
	
	public SOATypeRegistry getTypeRegistry() {
		return getNode() instanceof SOATypeRegistry 
		? (SOATypeRegistry)getNode() : null;
	}
	
	public void setTypeRegistry(SOATypeRegistry typeRegistry) {
		setNode(typeRegistry);
	}
	
	@Override
	public String getLabel() {
		return "All Type Libraries";
	}
}
