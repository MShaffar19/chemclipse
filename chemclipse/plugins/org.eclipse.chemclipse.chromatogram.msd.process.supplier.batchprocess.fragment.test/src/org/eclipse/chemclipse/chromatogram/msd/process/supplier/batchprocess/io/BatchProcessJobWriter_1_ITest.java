/*******************************************************************************
 * Copyright (c) 2010, 2016 Lablicate GmbH.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import junit.framework.TestCase;

import org.eclipse.core.runtime.NullProgressMonitor;

import org.eclipse.chemclipse.converter.exceptions.FileIsNotWriteableException;
import org.eclipse.chemclipse.converter.model.ChromatogramInputEntry;
import org.eclipse.chemclipse.converter.model.ChromatogramOutputEntry;
import org.eclipse.chemclipse.chromatogram.msd.process.model.ChromatogramProcessEntry;
import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.TestPathHelper;
import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.model.BatchProcessJob;
import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.model.IBatchProcessJob;

/**
 * @author Dr. Philip Wenig
 * 
 */
public class BatchProcessJobWriter_1_ITest extends TestCase {

	private IBatchProcessJob batchProcessJob;
	private BatchProcessJobWriter writer;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		writer = new BatchProcessJobWriter();
		batchProcessJob = new BatchProcessJob();
		String inputChromatogram = TestPathHelper.getAbsolutePath(TestPathHelper.TESTFILE_IMPORT_TEST);
		String outputChromatogram = TestPathHelper.getAbsolutePath(TestPathHelper.TESTFILE_EXPORT_TEST);
		/*
		 * Input
		 */
		batchProcessJob.getChromatogramInputEntries().add(new ChromatogramInputEntry(inputChromatogram));
		/*
		 * Process
		 */
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("FILTER", "org.eclipse.chemclipse.chromatogram.msd.filter.supplier.denoising"));
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("FILTER", "org.eclipse.chemclipse.chromatogram.xxd.filter.supplier.savitzkygolay"));
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("BASELINE_DETECTOR", "org.eclipse.chemclipse.chromatogram.msd.baseline.detector.supplier.smoothed"));
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("PEAK_DETECTOR", "org.eclipse.chemclipse.chromatogram.msd.peak.detector.supplier.firstderivative"));
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("COMBINED_INTEGRATOR", "org.eclipse.chemclipse.chromatogram.xxd.integrator.supplier.trapezoid.combinedIntegrator"));
		batchProcessJob.getChromatogramProcessEntries().add(new ChromatogramProcessEntry("PEAK_IDENTIFIER", "org.eclipse.chemclipse.chromatogram.msd.identifier.supplier.nist.peak"));
		/*
		 * Output
		 */
		batchProcessJob.getChromatogramOutputEntries().add(new ChromatogramOutputEntry(outputChromatogram, "net.openchrom.msd.converter.supplier.cdf"));
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void testProcess_1() {

		File file = new File(TestPathHelper.getAbsolutePath(TestPathHelper.TESTFILE_EXPORT_BATCH_PROCESS_JOB));
		try {
			writer.writeBatchProcessJob(file, batchProcessJob, new NullProgressMonitor());
		} catch(FileNotFoundException e) {
			assertTrue("FileNotFoundException should not be thrown here.", false);
		} catch(FileIsNotWriteableException e) {
			assertTrue("FileIsNotWriteableException should not be thrown here.", false);
		} catch(IOException e) {
			assertTrue("IOException should not be thrown here.", false);
		} catch(XMLStreamException e) {
			assertTrue("XMLStreamException should not be thrown here.", false);
		}
		assertNotNull(file);
	}
}
