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
package org.ebayopensource.turmeric.eclipse.ui.components;

import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
 * A modified version of {@link #org.eclipse.jface.fieldassist.ComboContentAdapter}
 * to support CCombo.
 * @author yayu
 * @since 1.0.0
 */
public class SOACComboControlAdapter implements IControlContentAdapter{

	/**
	 * Instantiates a new sOAC combo control adapter.
	 */
	public SOACComboControlAdapter() {
		super();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getControlContents(Control control) {
		return ((CCombo) control).getText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setControlContents(Control control, String text,
			int cursorPosition) {
		((CCombo) control).setText(text);
		((CCombo) control)
		.setSelection(new Point(cursorPosition, cursorPosition));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertControlContents(Control control, String text,
			int cursorPosition) {
		CCombo combo = (CCombo) control;
		String contents = combo.getText();
		Point selection = combo.getSelection();
		StringBuffer sb = new StringBuffer();
		sb.append(contents.substring(0, selection.x));
		sb.append(text);
		if (selection.y < contents.length()) {
			sb.append(contents.substring(selection.y, contents.length()));
		}
		combo.setText(sb.toString());
		selection.x = selection.x + cursorPosition;
		selection.y = selection.x;
		combo.setSelection(selection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorPosition(Control control) {
		return ((CCombo) control).getSelection().x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getInsertionBounds(Control control) {
		CCombo combo = (CCombo) control;
		int position = combo.getSelection().y;
		String contents = combo.getText();
		GC gc = new GC(combo);
		gc.setFont(combo.getFont());
		Point extent = gc.textExtent(contents.substring(0, Math.min(position,
				contents.length())));
		gc.dispose();
		return new Rectangle(combo.getClientArea().x + extent.x, combo
				.getClientArea().y, 1, combo.getClientArea().height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCursorPosition(Control control, int index) {
		((CCombo) control).setSelection(new Point(index, index));
	}

}
