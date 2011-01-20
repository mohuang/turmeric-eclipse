/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
/**
 * 
 */
package org.ebayopensource.turmeric.eclipse.errorlibrary.buildsystem;

import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ErrorLibraryProviderFactory;
import org.ebayopensource.turmeric.eclipse.errorlibrary.ui.model.DomainParamModel;
import org.ebayopensource.turmeric.eclipse.utils.plugin.WorkspaceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author yayu
 *
 */
public class ErrorDomainCreator {

	/**
	 * 
	 */
	private ErrorDomainCreator() {
		super();
	}
	
	public static void createErrorDomain(DomainParamModel model,
			IProgressMonitor monitor) throws Exception {
		final IProject project = WorkspaceUtil.getProject(model.getErrorLibrary());
		if (project.isAccessible()) {
			ErrorLibraryProviderFactory.getPreferredProvider().getErrorDomainCreator()
			.postCreation(model, monitor);
			WorkspaceUtil.refresh(monitor, project);
		}
		
	}

}
