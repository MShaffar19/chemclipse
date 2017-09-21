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
package org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.core.preprocessing;

public abstract class AbstaractScaling extends AbstractCentering {

	private int centeringType;

	public AbstaractScaling(int centeringType) {
		this.centeringType = centeringType;
	}

	public int getCenteringType() {

		return centeringType;
	}

	public void setCenteringType(int centeringType) {

		this.centeringType = centeringType;
	}
}
