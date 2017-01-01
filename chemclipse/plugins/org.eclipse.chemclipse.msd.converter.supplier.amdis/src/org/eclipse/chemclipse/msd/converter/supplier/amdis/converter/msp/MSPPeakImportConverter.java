/*******************************************************************************
 * Copyright (c) 2012, 2017 Lablicate GmbH.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.msd.converter.supplier.amdis.converter.msp;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.chemclipse.msd.converter.peak.AbstractPeakImportConverter;
import org.eclipse.chemclipse.msd.converter.processing.peak.IPeakImportConverterProcessingInfo;
import org.eclipse.chemclipse.msd.converter.processing.peak.PeakImportConverterProcessingInfo;

public class MSPPeakImportConverter extends AbstractPeakImportConverter {

	@Override
	public IPeakImportConverterProcessingInfo convert(File file, IProgressMonitor monitor) {

		IPeakImportConverterProcessingInfo processingInfo = new PeakImportConverterProcessingInfo();
		processingInfo.addErrorMessage("AMDIS MSP Peak Import", "The converter supports no *.msp file import.");
		return processingInfo;
	}
}
