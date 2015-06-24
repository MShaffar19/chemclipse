/*******************************************************************************
 * Copyright (c) 2015 Dr. Philip Wenig.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.xxd.converter.supplier.chemclipse.io;

import org.eclipse.chemclipse.model.core.IScan;
import org.eclipse.chemclipse.xxd.converter.supplier.chemclipse.TestPathHelper;

public class ChromatogramReader_3_FID_1003_ITest extends ChromatogramReaderFIDTestCase {

	@Override
	protected void setUp() throws Exception {

		pathImport = TestPathHelper.getAbsolutePath(TestPathHelper.TESTFILE_IMPORT_CHROMATOGRAM_3_FID_1003);
		super.setUp();
	}

	public void testReader_1() {

		assertEquals(1549, chromatogram.getNumberOfScans());
	}

	public void testReader_2() {

		assertEquals("Chromatogram3-1003", chromatogram.getName());
	}

	public void testReader_3() {

		assertEquals(7, chromatogram.getNumberOfPeaks());
	}

	public void testReader_4() {

		assertEquals(2330900, chromatogram.getStartRetentionTime());
	}

	public void testReader_5() {

		assertEquals(2408300, chromatogram.getStopRetentionTime());
	}

	public void testReader_6() {

		assertEquals(137045.0f, chromatogram.getMaxSignal());
	}

	public void testReader_7() {

		assertEquals(11251.0f, chromatogram.getMinSignal());
	}

	public void testReader_8() {

		assertEquals(2330900, chromatogram.getScanDelay());
	}

	public void testReader_9() {

		assertEquals(1554, chromatogram.getScanInterval());
	}

	public void testReader_10() {

		assertEquals(4.0808584E7f, chromatogram.getTotalSignal());
	}

	public void testReader_11() {

		IScan scan = chromatogram.getScan(1);
		assertEquals(2330900, scan.getRetentionTime());
		assertEquals(0.0f, scan.getRetentionIndex());
		assertEquals(11463.0f, scan.getTotalSignal());
	}

	public void testReader_12() {

		IScan scan = chromatogram.getScan(593);
		assertEquals(2360500, scan.getRetentionTime());
		assertEquals(0.0f, scan.getRetentionIndex());
		assertEquals(27412.0f, scan.getTotalSignal());
	}

	public void testReader_13() {

		IScan scan = chromatogram.getScan(1549);
		assertEquals(2408300, scan.getRetentionTime());
		assertEquals(0.0f, scan.getRetentionIndex());
		assertEquals(16266.0f, scan.getTotalSignal());
	}
}
