/*******************************************************************************
 * Copyright (c) 2008, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.msd.model.implementation;

import org.eclipse.chemclipse.msd.model.core.PeakIonType;
import junit.framework.TestCase;

public class PeakIon_1_Test extends TestCase {

	private PeakIon peakIon;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		peakIon = new PeakIon(45.5f, 2500.4f);
	}

	@Override
	protected void tearDown() throws Exception {

		peakIon = null;
		super.tearDown();
	}

	public void testGetUncertaintyFactor_1() {

		assertEquals("GetUncertaintyFactor", 1.0f, peakIon.getUncertaintyFactor());
	}

	public void testGetPeakIonType_1() {

		assertEquals("GetPeakIonType", PeakIonType.NO_TYPE, peakIon.getPeakIonType());
	}
}
