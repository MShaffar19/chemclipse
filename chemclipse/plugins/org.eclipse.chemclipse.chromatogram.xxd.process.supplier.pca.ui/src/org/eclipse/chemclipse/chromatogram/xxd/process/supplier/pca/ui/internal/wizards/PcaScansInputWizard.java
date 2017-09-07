/*******************************************************************************
 * Copyright (c) 2017 Jan Holy.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Jan Holy - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.ui.internal.wizards;

import java.util.List;

import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.core.IDataExtraction;
import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.core.PcaExtractionScans;
import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.core.PcaFiltrationData;
import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.core.PcaScalingData;
import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.model.IDataInputEntry;
import org.eclipse.jface.wizard.Wizard;

public class PcaScansInputWizard extends Wizard implements IPcaInputWizard {

	private DataInputFromPeakFilesPageWizard dataInputFromPeakFilesPage;
	private FiltrationDataWizardPage filtrationDataPage;
	private MainPropertiesScansInputWizardPage mainPropertiesPage;
	private ModificationDataWizardPage modificationDataWizardPage;
	private PcaExtractionScans pcaExtractionData;

	public PcaScansInputWizard() {
		super();
		mainPropertiesPage = new MainPropertiesScansInputWizardPage("MainProperites");
		dataInputFromPeakFilesPage = new DataInputFromPeakFilesPageWizard("DataInputFiles");
		modificationDataWizardPage = new ModificationDataWizardPage("NormalizationData");
		filtrationDataPage = new FiltrationDataWizardPage("FiltrationData");
	}

	@Override
	public void addPages() {

		addPage(mainPropertiesPage);
		addPage(dataInputFromPeakFilesPage);
		addPage(modificationDataWizardPage);
		addPage(filtrationDataPage);
	}

	@Override
	public int getNumerOfComponents() {

		return mainPropertiesPage.getNumerOfComponents();
	}

	@Override
	public IDataExtraction getPcaExtractionData() {

		return pcaExtractionData;
	}

	@Override
	public PcaFiltrationData getPcaFiltrationData() {

		return filtrationDataPage.getPcaFiltrationData();
	}

	@Override
	public PcaScalingData getPcaScalingData() {

		return modificationDataWizardPage.getPcaScalingData();
	}

	@Override
	public boolean performFinish() {

		List<IDataInputEntry> dataInputs = dataInputFromPeakFilesPage.getDataInputEntries();
		int retentionTimeWindow = mainPropertiesPage.getRetentionTimeWindow();
		boolean useDefoultProperties = mainPropertiesPage.isUseDefoultProperties();
		int extractionType = mainPropertiesPage.getExtractionType();
		int maximalNumberScans = mainPropertiesPage.getMaximalNumberScans();
		pcaExtractionData = new PcaExtractionScans(retentionTimeWindow, maximalNumberScans, dataInputs, extractionType, useDefoultProperties);
		return true;
	}
}
