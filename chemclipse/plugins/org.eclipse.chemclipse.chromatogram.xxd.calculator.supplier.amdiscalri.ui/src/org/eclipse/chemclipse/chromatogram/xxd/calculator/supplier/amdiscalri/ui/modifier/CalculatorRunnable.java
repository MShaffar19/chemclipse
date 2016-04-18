/*******************************************************************************
 * Copyright (c) 2014, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.calculator.supplier.amdiscalri.ui.modifier;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.chemclipse.chromatogram.msd.filter.core.peak.PeakFilter;
import org.eclipse.chemclipse.chromatogram.xxd.calculator.core.chromatogram.ChromatogramCalculator;
import org.eclipse.chemclipse.chromatogram.xxd.calculator.processing.ICalculatorProcessingInfo;
import org.eclipse.chemclipse.chromatogram.xxd.calculator.supplier.amdiscalri.preferences.PreferenceSupplier;
import org.eclipse.chemclipse.chromatogram.xxd.calculator.supplier.amdiscalri.settings.IRetentionIndexFilterSettingsPeak;
import org.eclipse.chemclipse.model.selection.IChromatogramSelection;
import org.eclipse.chemclipse.msd.model.core.selection.IChromatogramSelectionMSD;
import org.eclipse.chemclipse.processing.ui.support.ProcessingInfoViewSupport;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

public class CalculatorRunnable implements IRunnableWithProgress {

	private static final String FILTER_ID_SCANS = "org.eclipse.chemclipse.chromatogram.xxd.calculator.supplier.amdiscalri.scans";
	private static final String FILTER_ID_PEAKS = "org.eclipse.chemclipse.chromatogram.xxd.calculator.supplier.amdiscalri.peaks";
	private IChromatogramSelection chromatogramSelection;

	public CalculatorRunnable(IChromatogramSelection chromatogramSelection) {
		this.chromatogramSelection = chromatogramSelection;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

		try {
			monitor.beginTask("Retention Index Calculator Filter", IProgressMonitor.UNKNOWN);
			ICalculatorProcessingInfo processingInfo = ChromatogramCalculator.applyCalculator(chromatogramSelection, FILTER_ID_SCANS, monitor);
			//
			if(chromatogramSelection instanceof IChromatogramSelectionMSD) {
				IRetentionIndexFilterSettingsPeak peakFilterSettings = PreferenceSupplier.getPeakFilterSettings();
				PeakFilter.applyFilter(((IChromatogramSelectionMSD)chromatogramSelection), peakFilterSettings, FILTER_ID_PEAKS, monitor);
			}
			//
			ProcessingInfoViewSupport.updateProcessingInfo(processingInfo, false);
			updateSelection();
		} finally {
			monitor.done();
		}
	}

	/*
	 * Updates the selection using the GUI thread.
	 */
	private void updateSelection() {

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {

				chromatogramSelection.update(true);
			}
		});
	}
}
