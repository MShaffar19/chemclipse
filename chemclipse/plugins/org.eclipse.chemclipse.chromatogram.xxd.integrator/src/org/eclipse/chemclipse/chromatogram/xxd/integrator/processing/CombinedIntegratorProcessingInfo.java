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
 * Alexander Kerner - implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.integrator.processing;

import org.eclipse.chemclipse.chromatogram.xxd.integrator.result.ICombinedIntegrationResult;
import org.eclipse.chemclipse.processing.core.AbstractProcessingInfo;
import org.eclipse.chemclipse.processing.core.exceptions.TypeCastException;

public class CombinedIntegratorProcessingInfo extends AbstractProcessingInfo implements ICombinedIntegratorProcessingInfo {

	@Override
	public ICombinedIntegrationResult getCombinedIntegrationResult() throws TypeCastException {

		Object object = getProcessingResult();
		if(object instanceof ICombinedIntegrationResult) {
			return (ICombinedIntegrationResult)object;
		} else {
			throw createTypeCastException("Combined Integrator", object.getClass(), ICombinedIntegrationResult.class);
		}
	}

	@Override
	public void setCombinedIntegrationResult(ICombinedIntegrationResult chromatogramIntegrationResult) {

		setProcessingResult(chromatogramIntegrationResult);
	}
}
