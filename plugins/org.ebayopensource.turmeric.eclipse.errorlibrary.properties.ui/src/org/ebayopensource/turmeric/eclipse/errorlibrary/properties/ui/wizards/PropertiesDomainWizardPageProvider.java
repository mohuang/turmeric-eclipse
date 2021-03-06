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
package org.ebayopensource.turmeric.eclipse.errorlibrary.properties.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.eclipse.core.logging.SOALogger;
import org.ebayopensource.turmeric.eclipse.core.model.BaseServiceParamModel;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ISOAErrorLibraryWizardPageProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.ui.model.DomainParamModel;
import org.ebayopensource.turmeric.eclipse.ui.SOABasePage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * The Class PropertiesDomainWizardPageProvider.
 *
 * @author yayu
 */
public class PropertiesDomainWizardPageProvider implements
		ISOAErrorLibraryWizardPageProvider {
	private NewPropertiesContentErrorDomainWizardPage propPage = null;
	private static final SOALogger logger = SOALogger.getLogger();

	/**
	 * Instantiates a new properties domain wizard page provider.
	 */
	public PropertiesDomainWizardPageProvider() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SOABasePage> getWizardpages(IStructuredSelection selection) {
		final List<SOABasePage> pages = new ArrayList<SOABasePage>(1);
		propPage = new NewPropertiesContentErrorDomainWizardPage(selection);
		pages.add(propPage);
		return pages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseServiceParamModel performFinish() {
		if (SOALogger.DEBUG)
			logger.entering();
		
		final DomainParamModel model = new DomainParamModel();
		model.setPackageName(propPage.getPackageName());
		model.setDomain(propPage.getDomainName());
		model.setOrganization(propPage.getOrganization());
		model.setErrorLibrary(propPage.getErrorLibrary());
		model.setLocale(propPage.getLocale());
		
		if (SOALogger.DEBUG)
			logger.exiting(model);
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus preValidate() {
		return Status.OK_STATUS;
	}

}
