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
package org.ebayopensource.turmeric.eclipse.codegen.model;

import java.util.Map;

import org.ebayopensource.turmeric.eclipse.repositorysystem.model.BaseCodeGenModel;

/**
 * @author yayu
 *
 */
public abstract class AbstractGenTypeGlobalConfig extends BaseCodeGenModel {
	private String metadataDirectory; //-mdest Destination location for generated configuration and other XML files
	
	/**
	 * 
	 */
	public AbstractGenTypeGlobalConfig() {
		super();
	}

	/**
	 * @param genType
	 * @param namespace
	 * @param serviceLayerFile
	 * @param serviceInterface
	 * @param serviceName
	 * @param serviceVersion
	 * @param serviceImpl
	 * @param projectRoot
	 * @param serviceLayer
	 * @param sourceDirectory
	 * @param destination
	 * @param outputDirectory
	 * @param metadataDirectory
	 */
	public AbstractGenTypeGlobalConfig(String genType, String namespace,
			String serviceLayerFile, String serviceInterface,
			String serviceName, String serviceVersion, String serviceImpl,
			String projectRoot, String serviceLayer, String sourceDirectory,
			String destination, String outputDirectory, String metadataDirectory) {
		super(genType, namespace, serviceLayerFile, serviceInterface,
				serviceName, serviceVersion, serviceImpl, projectRoot,
				serviceLayer, sourceDirectory, destination, outputDirectory);
		this.metadataDirectory = metadataDirectory;
	}
	
	public String getMetadataDirectory() {
		return metadataDirectory;
	}

	public void setMetadataDirectory(String metadataDirectory) {
		this.metadataDirectory = metadataDirectory;
	}

	@Override
	public Map<String, String> getCodeGenOptions() {
		Map<String, String> result = super.getCodeGenOptions();
		if (this.metadataDirectory != null)
			result.put(PARAM_MDEST, this.metadataDirectory);
		return result;
	}
}
