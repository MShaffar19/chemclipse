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
package org.eclipse.chemclipse.msd.converter.chromatogram;

import org.eclipse.chemclipse.converter.chromatogram.ChromatogramConverterSupport;
import org.eclipse.chemclipse.converter.exceptions.NoConverterAvailableException;
import org.eclipse.chemclipse.msd.converter.chromatogram.ChromatogramConverterMSD;
import junit.framework.TestCase;

/**
 * Testing the method getChromatogramConverterSupport() in
 * ChromatogramConverter.
 * 
 * @author eselmeister
 */
public class ChromatogramConverter_2_Test extends TestCase {

	private ChromatogramConverterSupport support;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		support = ChromatogramConverterMSD.getChromatogramConverterSupport();
	}

	@Override
	protected void tearDown() throws Exception {

		support = null;
		super.tearDown();
	}

	public void testFilterNames_1() {

		try {
			String[] filterNames = support.getFilterNames();
			String result = "";
			for(String name : filterNames) {
				result += name + ";";
			}
			/*
			 * There could be more converter. But these 3 should be there in
			 * every case.
			 */
			assertTrue("Amount Filter Names", 3 <= filterNames.length);
			assertEquals("FilterName", true, result.contains("OpenChrom Chromatogram (*.ocb)"));
			assertEquals("FilterName", true, result.contains("Agilent Chromatogram (*.D/DATA.MS)"));
			assertEquals("FilterName", true, result.contains("ANDI/AIA CDF Chromatogram (*.CDF)"));
		} catch(NoConverterAvailableException e) {
			assertTrue("NoConverterAvailableException", false);
		}
	}
}
