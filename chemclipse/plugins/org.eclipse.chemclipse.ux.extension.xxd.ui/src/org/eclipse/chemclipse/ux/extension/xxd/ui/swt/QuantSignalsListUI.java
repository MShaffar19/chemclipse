/*******************************************************************************
 * Copyright (c) 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.ux.extension.xxd.ui.swt;

import java.util.List;

import org.eclipse.chemclipse.support.ui.provider.ListContentProvider;
import org.eclipse.chemclipse.support.ui.swt.ExtendedTableViewer;
import org.eclipse.chemclipse.ux.extension.xxd.ui.internal.provider.QuantSignalsEditingSupport;
import org.eclipse.chemclipse.ux.extension.xxd.ui.internal.provider.QuantSignalsLabelProvider;
import org.eclipse.chemclipse.ux.extension.xxd.ui.internal.provider.QuantSignalsTableComparator;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;

public class QuantSignalsListUI extends ExtendedTableViewer {

	private String[] titles = QuantSignalsLabelProvider.TITLES;
	private int[] bounds = QuantSignalsLabelProvider.BOUNDS;
	private IBaseLabelProvider labelProvider = new QuantSignalsLabelProvider();
	private ViewerComparator tableComparator = new QuantSignalsTableComparator();

	public QuantSignalsListUI(Composite parent, int style) {
		super(parent, style);
		createColumns();
	}

	public void clear() {

		setInput(null);
	}

	private void createColumns() {

		createColumns(titles, bounds);
		setLabelProvider(labelProvider);
		setContentProvider(new ListContentProvider());
		setComparator(tableComparator);
		setEditingSupport();
	}

	private void setEditingSupport() {

		List<TableViewerColumn> tableViewerColumns = getTableViewerColumns();
		for(int i = 0; i < tableViewerColumns.size(); i++) {
			TableViewerColumn tableViewerColumn = tableViewerColumns.get(i);
			String label = tableViewerColumn.getColumn().getText();
			if(label.equals(QuantSignalsLabelProvider.RELATIVE_RESPONSE)) {
				tableViewerColumn.setEditingSupport(new QuantSignalsEditingSupport(this, label));
			} else if(label.equals(QuantSignalsLabelProvider.UNCERTAINTY)) {
				tableViewerColumn.setEditingSupport(new QuantSignalsEditingSupport(this, label));
			} else if(label.equals(QuantSignalsLabelProvider.USE)) {
				tableViewerColumn.setEditingSupport(new QuantSignalsEditingSupport(this, label));
			}
		}
	}
}
