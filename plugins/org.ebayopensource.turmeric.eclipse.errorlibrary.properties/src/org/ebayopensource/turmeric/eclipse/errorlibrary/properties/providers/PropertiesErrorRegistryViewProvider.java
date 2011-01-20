/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.errorlibrary.properties.providers;

import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.ui.AbstractErrorNodeAction;
import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.ui.DeleteErrorNodeAction;
import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.ui.RefreshErrorNodeAction;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorRegistryViewProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.views.ISOAErrLibrary;
import org.ebayopensource.turmeric.eclipse.errorlibrary.views.ISOAErrUIComp;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;


/**
 * @author yayu
 *
 */
public class PropertiesErrorRegistryViewProvider implements
		IErrorRegistryViewProvider {
	private AbstractErrorNodeAction deleteAction;
	private AbstractErrorNodeAction refreshAction;

	/**
	 * 
	 */
	public PropertiesErrorRegistryViewProvider() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorRegistryViewProvider#postClientAreaCreation(org.eclipse.swt.widgets.Composite, org.eclipse.jface.viewers.TreeViewer)
	 */
	public void postClientAreaCreation(Composite parent, TreeViewer errorViewer)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorRegistryViewProvider#createContextMenu(org.eclipse.jface.action.MenuManager, org.eclipse.jface.viewers.TreeViewer)
	 */
	public void createContextMenu(MenuManager menuMgr, final TreeViewer errorViewer)
	throws Exception {
		if (deleteAction == null)
			deleteAction = new DeleteErrorNodeAction(errorViewer);
		if (refreshAction == null)
			refreshAction = new RefreshErrorNodeAction(errorViewer);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
				if (errorViewer.getSelection() instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)errorViewer.getSelection())
					.getFirstElement();
					if (obj instanceof ISOAErrUIComp) {
						if ((obj instanceof ISOAErrLibrary) == false) {
							mgr.add(deleteAction);
							deleteAction.setSelectedErrorNode((ISOAErrUIComp)obj);
						}
						mgr.add(refreshAction);
						refreshAction.setSelectedErrorNode((ISOAErrUIComp)obj);
					}
				}

			}
		}); 
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorRegistryViewProvider#createToolBar(org.eclipse.jface.action.IToolBarManager, org.eclipse.jface.viewers.TreeViewer)
	 */
	public void createToolBar(IToolBarManager mgr, TreeViewer errorViewer)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
