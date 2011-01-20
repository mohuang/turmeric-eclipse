/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.errorlibrary.properties.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.providers.PropertiesSOAConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author yayu
 *
 */
public class ErrorLibraryDeltaVisitor implements IResourceDeltaVisitor {
	private IProject project;
	private List<IProject> deletedErrorLibProjects = new ArrayList<IProject>();
	private List<IFile> modifiedErrorDomainList = new ArrayList<IFile>();
	private List<IFile> deletedErrorDomainList = new ArrayList<IFile>();

	/**
	 * 
	 */
	public ErrorLibraryDeltaVisitor() {
		super();
	}

	

	public ErrorLibraryDeltaVisitor(IProject project) {
		this.project = project;
	}

	/*
	 * Visits the delta, finds out if there are modified error domains and returns true
	 * if delta is not over. In addition it populates the deleted xsd files and
	 * modified xsd files list. (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {
		final IPath projectPath = delta.getProjectRelativePath();
		final String projectPathStr = delta.getProjectRelativePath().toString();
		if (StringUtils.isNotEmpty(projectPathStr)
				&& projectPathStr.endsWith(
						PropertiesSOAConstants.FILE_ERROR_DATA)
				&& projectPathStr.startsWith(
						PropertiesSOAConstants.FOLDER_ERROR_DOMAIN)) {
			if (project != null) {
				if (project.getFile(projectPath).isAccessible()) {
					modifiedErrorDomainList.add(project.getFile(projectPath));
				}
			} else {
				if (isValidErrorDomainFileDeleted(delta.getResource())) {
					deletedErrorDomainList.add((IFile) delta.getResource());
				} else if (isValidErrorDomainFileModified(delta
						.getResource())) {
					modifiedErrorDomainList.add((IFile) delta.getResource());
				}
			}
		}
		if (delta.getKind() == IResourceDelta.REMOVED && 
				delta.getResource() instanceof IProject) {
			final IProject errorLibProject = (IProject)delta.getResource();
			if (deletedErrorLibProjects.contains(errorLibProject) == false
					&& errorLibProject.getName().contains("ErrorLibrary")) {
				//TODO this is a dirty fix to check the name, 
				//but the project already been deleted so no way to 
				//check the project nature
				deletedErrorLibProjects.add(errorLibProject);
			}
		}
		return true;
	}
	
	public List<IProject> getDeletedProject() {
		return deletedErrorLibProjects;
	}

	/**
	 * @return list of modified error domains.
	 * @return
	 */
	public List<IFile> getModifiedErrorDomains() {
		return modifiedErrorDomainList;
	}

	/**
	 * @return list of deleted error domains.
	 */
	public List<IFile> getDeletedErrorDomainList() {
		return deletedErrorDomainList;
	}

	public static boolean isValidErrorDomainFileDeleted(Object fileObject)
	throws CoreException {
		IFile file = null;
		if (fileObject instanceof IFile) {
			file = (IFile) fileObject;
		}
		return file != null
		&& StringUtils.equalsIgnoreCase(PropertiesSOAConstants.FILE_ERROR_DATA,
				file.getName())
				&& file.exists() == false
				&& file.getProject() != null
				&& file.getProject().isAccessible()
				&& file.getProject().hasNature(
						TurmericErrorLibraryProjectNature.getNatureId());
	}

	public static boolean isValidErrorDomainFileModified(Object fileObject)
	throws CoreException {
		IFile file = null;
		if (fileObject instanceof IFile) {
			file = (IFile) fileObject;
		}
		return file != null
		&& StringUtils.equalsIgnoreCase(PropertiesSOAConstants.FILE_ERROR_DATA,
				file.getName())
				&& file.exists()
				&& file.getProject() != null
				&& file.getProject().isAccessible()
				&& file.getProject().hasNature(
						TurmericErrorLibraryProjectNature.getNatureId());
	}

}
