/*******************************************************************************
 * Copyright (c) 2012, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.process.supplier;

import java.util.List;

import org.eclipse.chemclipse.chromatogram.msd.process.support.IProcessTypeSupplier;
import org.eclipse.chemclipse.chromatogram.xxd.baseline.detector.core.BaselineDetector;
import org.eclipse.chemclipse.chromatogram.xxd.baseline.detector.core.IBaselineDetectorSupplier;
import org.eclipse.chemclipse.msd.model.core.selection.IChromatogramSelectionMSD;
import org.eclipse.chemclipse.processing.core.IProcessingInfo;
import org.eclipse.core.runtime.IProgressMonitor;

public class BaselineDetectorTypeSupplier extends AbstractProcessTypeSupplier implements IProcessTypeSupplier {

	public static final String CATEGORY = "Baseline Detector";

	@Override
	public String getCategory() {

		return CATEGORY;
	}

	@Override
	public String getProcessorName(String processorId) throws Exception {

		IBaselineDetectorSupplier baselineSupplier = BaselineDetector.getBaselineDetectorSupport().getBaselineDetectorSupplier(processorId);
		return baselineSupplier.getDetectorName();
	}

	@Override
	public List<String> getPluginIds() throws Exception {

		return BaselineDetector.getBaselineDetectorSupport().getAvailableDetectorIds();
	}

	@Override
	public IProcessingInfo applyProcessor(IChromatogramSelectionMSD chromatogramSelection, String processorId, IProgressMonitor monitor) {

		return BaselineDetector.setBaseline(chromatogramSelection, processorId, monitor);
	}
}
