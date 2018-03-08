/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.ux.extension.xxd.ui.internal.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.chemclipse.model.comparator.TargetExtendedComparator;
import org.eclipse.chemclipse.model.identifier.IIdentificationTarget;
import org.eclipse.chemclipse.model.identifier.ILibraryInformation;
import org.eclipse.chemclipse.support.comparator.SortOrder;

public class IdentificationDataSupport {

	private TargetExtendedComparator targetExtendedComparator;

	public IdentificationDataSupport() {
		targetExtendedComparator = new TargetExtendedComparator(SortOrder.DESC);
	}

	public ILibraryInformation getBestLibraryInformation(List<? extends IIdentificationTarget> targets) {

		ILibraryInformation libraryInformation = null;
		IIdentificationTarget identificationTarget = getBestIdentificationTarget(targets);
		if(identificationTarget != null) {
			libraryInformation = identificationTarget.getLibraryInformation();
		}
		return libraryInformation;
	}

	public IIdentificationTarget getBestIdentificationTarget(List<? extends IIdentificationTarget> targets) {

		IIdentificationTarget identificationTarget = null;
		targets = new ArrayList<IIdentificationTarget>(targets);
		Collections.sort(targets, targetExtendedComparator);
		if(targets.size() >= 1) {
			identificationTarget = targets.get(0);
		}
		return identificationTarget;
	}
}
