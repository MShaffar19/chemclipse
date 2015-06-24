/*******************************************************************************
 * Copyright (c) 2013, 2015 Dr. Philip Wenig.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.msd.converter.supplier.mzml.converter;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;

import org.eclipse.chemclipse.msd.converter.processing.chromatogram.IChromatogramMSDImportConverterProcessingInfo;
import org.eclipse.chemclipse.msd.converter.supplier.mzml.TestPathHelper;
import org.eclipse.chemclipse.msd.model.core.IChromatogramMSD;
import org.eclipse.chemclipse.msd.model.core.IVendorMassSpectrum;

import junit.framework.TestCase;

public class ChromatogramImportConverter_2_ITest extends TestCase {

	private File importFile;
	private IChromatogramMSD chromatogram;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		importFile = new File(TestPathHelper.getAbsolutePath(TestPathHelper.TESTFILE_IMPORT_CHROMATOGRAM_2));
		ChromatogramImportConverter converter = new ChromatogramImportConverter();
		IChromatogramMSDImportConverterProcessingInfo processingInfo = converter.convert(importFile, new NullProgressMonitor());
		chromatogram = processingInfo.getChromatogram();
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void testImport_1() {

		assertEquals("NumberOfScans", 257, chromatogram.getNumberOfScans());
	}

	public void testImport_2() {

		assertEquals("Start RT", 47946, chromatogram.getStartRetentionTime());
	}

	public void testImport_3() {

		assertEquals("Stop RT", 309087, chromatogram.getStopRetentionTime());
	}

	public void testImport_4() {

		assertEquals("Min Signal", 12.0f, chromatogram.getMinSignal());
	}

	public void testImport_5() {

		assertEquals("Max Signal", 171636.0f, chromatogram.getMaxSignal());
	}

	public void testImport_6() {

		IVendorMassSpectrum massSpectrum = (IVendorMassSpectrum)chromatogram.getScan(1);
		assertEquals("Ions", 3, massSpectrum.getNumberOfIons());
	}

	public void testImport_7() {

		IVendorMassSpectrum massSpectrum = (IVendorMassSpectrum)chromatogram.getScan(130);
		assertEquals("Ions", 4, massSpectrum.getNumberOfIons());
	}

	public void testImport_8() {

		IVendorMassSpectrum massSpectrum = (IVendorMassSpectrum)chromatogram.getScan(257);
		assertEquals("Ions", 1, massSpectrum.getNumberOfIons());
	}
}
