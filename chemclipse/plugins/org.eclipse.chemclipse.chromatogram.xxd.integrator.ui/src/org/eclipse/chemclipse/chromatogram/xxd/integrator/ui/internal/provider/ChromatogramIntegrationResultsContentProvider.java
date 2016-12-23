/*******************************************************************************
 * Copyright (c) 2011, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.integrator.ui.internal.provider;

import org.eclipse.chemclipse.chromatogram.xxd.integrator.result.IChromatogramIntegrationResults;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ChromatogramIntegrationResultsContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {

		if(inputElement instanceof IChromatogramIntegrationResults) {
			IChromatogramIntegrationResults results = (IChromatogramIntegrationResults)inputElement;
			return results.getChromatogramIntegrationResultList().toArray();
		} else {
			return null;
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}
}
