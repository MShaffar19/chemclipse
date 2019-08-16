/*******************************************************************************
 * Copyright (c) 2012, 2018 Lablicate GmbH.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.csd.converter.supplier.xy.model;

import org.eclipse.chemclipse.csd.model.core.IScanCSD;

public interface IVendorScan extends IScanCSD {

	/**
	 * Stores the total signal.
	 * 
	 * @param totalSignal
	 */
	void setTotalSignal(float totalSignal);
}