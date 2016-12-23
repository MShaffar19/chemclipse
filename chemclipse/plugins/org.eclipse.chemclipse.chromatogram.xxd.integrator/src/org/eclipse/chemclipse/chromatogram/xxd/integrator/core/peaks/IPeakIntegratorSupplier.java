/*******************************************************************************
 * Copyright (c) 2008, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.integrator.core.peaks;

/**
 * @author eselmeister
 */
public interface IPeakIntegratorSupplier {

	/**
	 * The id of the extension point: e.g.
	 * (org.eclipse.chemclipse.chromatogram.xxd.integrator.supplier.trapezoid)
	 * 
	 * @return String
	 */
	String getId();

	/**
	 * A short description of the functionality of the extension point.
	 * 
	 * @return String
	 */
	String getDescription();

	/**
	 * The integrator name that can be shown in a list box dialogue.
	 * 
	 * @return String
	 */
	String getIntegratorName();
}
