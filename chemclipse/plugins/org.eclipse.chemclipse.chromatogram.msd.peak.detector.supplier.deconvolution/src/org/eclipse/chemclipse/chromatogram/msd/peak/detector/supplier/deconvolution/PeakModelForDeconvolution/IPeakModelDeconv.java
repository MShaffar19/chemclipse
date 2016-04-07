/*******************************************************************************
 * Copyright (c) 2016 Lablicate UG (haftungsbeschränkt).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Florian Ernst - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.peak.detector.supplier.deconvolution.PeakModelForDeconvolution;

import java.util.List;

public interface IPeakModelDeconv {

	double getModelIon();

	int getModelRetentionTime();

	float getModelAbundance();

	int getModelScanMax();

	List<IPeakModelDeconvIon> getAllIonsInModel();

	IPeakModelDeconvIon getIonInModel(int scan);

	int sizeOfIonsWithoutModelIon();

	void addIonToDeconvModel(IPeakModelDeconvIon deconIon);

	void deletePositionInIonsList(int pos);
}
