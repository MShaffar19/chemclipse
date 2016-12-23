/*******************************************************************************
 * Copyright (c) 2011, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.integrator.core.chromatogram;

import org.eclipse.chemclipse.model.selection.IChromatogramSelection;
import org.eclipse.chemclipse.chromatogram.xxd.integrator.core.settings.chromatogram.IChromatogramIntegrationSettings;
import org.eclipse.chemclipse.chromatogram.xxd.integrator.exceptions.ValueMustNotBeNullException;

public abstract class AbstractChromatogramIntegrator implements IChromatogramIntegrator {

	protected void validate(IChromatogramSelection chromatogramSelection, IChromatogramIntegrationSettings chromatogramIntegrationSettings) throws ValueMustNotBeNullException {

		/*
		 * Test that the values are not null.
		 */
		if(chromatogramSelection == null) {
			throw new ValueMustNotBeNullException("The given chromatogram selection must not be null.");
		}
		testChromatogramIntegrationSettings(chromatogramIntegrationSettings);
	}

	private void testChromatogramIntegrationSettings(IChromatogramIntegrationSettings chromatogramIntegrationSettings) throws ValueMustNotBeNullException {

		if(chromatogramIntegrationSettings == null) {
			throw new ValueMustNotBeNullException("The given chromatogram integration settings must not be null");
		}
	}
}
