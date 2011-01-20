/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.buildsystem.core;

import java.io.IOException;

import org.ebayopensource.turmeric.eclipse.buildsystem.utils.BuildSystemUtil;
import org.ebayopensource.turmeric.eclipse.config.core.SOAGlobalConfigAccessor;
import org.ebayopensource.turmeric.eclipse.repositorysystem.core.GlobalRepositorySystem;
import org.ebayopensource.turmeric.eclipse.resources.constants.SOAProjectConstants;
import org.ebayopensource.turmeric.eclipse.resources.constants.SOAProjectConstants.SupportedProjectType;
import org.ebayopensource.turmeric.eclipse.resources.model.SOAConsumerProject;
import org.ebayopensource.turmeric.eclipse.resources.model.SOAImplProject;
import org.ebayopensource.turmeric.eclipse.resources.model.SOAIntfProject;
import org.ebayopensource.turmeric.eclipse.utils.plugin.JDTUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Responsible for adding SOA Support for any natural project. SOA Support needs
 * Java Support to be added and so we add both Java support and SOA Support in
 * one shot. Adding support in a broad sense means adding the builders and
 * natures, and class path containers. Next most important functionality is to
 * perform build system initializations. The APIS in this class are very
 * straight forward that we don't want to create documentation for each API.
 * 
 * @author smathew
 */
public class BuildSystemConfigurer {

	public static void configure(SOAIntfProject intfProject,
			IProgressMonitor monitor) throws CoreException, IOException {
		// add java support
		JDTUtil.addJavaSupport(intfProject.getEclipseMetadata().getProject(),
				intfProject.getSourceDirectoryNames(), 
				SOAGlobalConfigAccessor.getDefaultCompilerLevel(), 
				SOAProjectConstants.FOLDER_OUTPUT_DIR, monitor);
		// add SOA support
		
		BuildSystemUtil.addSOASupport(intfProject, GlobalRepositorySystem.instanceOf()
				.getActiveRepositorySystem().getProjectNatureId(SupportedProjectType.INTERFACE), monitor);
	}

	public static void configure(SOAImplProject implProject,
			IProgressMonitor monitor) throws Exception {
		// add java support
		JDTUtil.addJavaSupport(implProject.getEclipseMetadata().getProject(),
				implProject.getSourceDirectoryNames(),
				SOAGlobalConfigAccessor.getDefaultCompilerLevel(), 
				SOAProjectConstants.FOLDER_OUTPUT_DIR, monitor);
		// add SOA support
		BuildSystemUtil.addSOASupport(implProject, GlobalRepositorySystem.instanceOf()
				.getActiveRepositorySystem().getProjectNatureId(SupportedProjectType.IMPL), monitor);
	}

	public static void configure(SOAConsumerProject consumerProject,
			IProgressMonitor monitor) throws CoreException, IOException {
		// add java support
		JDTUtil.addJavaSupport(consumerProject.getEclipseMetadata()
				.getProject(), consumerProject.getSourceDirectoryNames(),
				SOAGlobalConfigAccessor.getDefaultCompilerLevel(), 
				SOAProjectConstants.FOLDER_OUTPUT_DIR, monitor);
		// add SOA support
		BuildSystemUtil.addSOASupport(consumerProject, GlobalRepositorySystem.instanceOf()
				.getActiveRepositorySystem().getProjectNatureId(SupportedProjectType.CONSUMER), monitor);
	}

	public static void performRepositorySpecificTasks(
			SOAIntfProject intfProject, SOAImplProject implProject,
			IProgressMonitor monitor) throws Exception {
		GlobalRepositorySystem.instanceOf().getActiveRepositorySystem()
				.getProjectConfigurer().initializeProject(intfProject,
						implProject, monitor);
	}

	public static void performRepositorySpecificTasks(
			SOAIntfProject intfProject, IProgressMonitor monitor)
			throws Exception {
		GlobalRepositorySystem.instanceOf().getActiveRepositorySystem()
				.getProjectConfigurer().initializeProject(intfProject, monitor);
	}

	public static void performRepositorySpecificTasks(
			SOAImplProject implProject, IProgressMonitor monitor)
			throws Exception {
		GlobalRepositorySystem.instanceOf().getActiveRepositorySystem()
				.getProjectConfigurer().initializeProject(implProject, monitor);
	}

	public static void performRepositorySpecificTasks(
			SOAConsumerProject consumerProject, String serviceVersion,
			boolean convertingJavaProject, 
			IProgressMonitor monitor) throws Exception {
		GlobalRepositorySystem.instanceOf().getActiveRepositorySystem()
				.getProjectConfigurer().initializeProject(consumerProject,
						serviceVersion, convertingJavaProject, monitor);
	}

}
