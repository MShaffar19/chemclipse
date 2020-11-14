/*******************************************************************************
 * Copyright (c) 2020 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.ux.extension.xxd.ui.toolbar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.chemclipse.rcp.ui.icons.core.IApplicationImage;
import org.eclipse.chemclipse.ux.extension.ui.support.PartSupport;
import org.eclipse.chemclipse.ux.extension.xxd.ui.preferences.PreferenceConstants;

public class GroupHandlerQuantitation extends AbstractGroupHandler {

	private static final String NAME = "Quantitation";
	private static final String SETTINGS_CONTRIBUTION_URI = "bundleclass://org.eclipse.chemclipse.ux.extension.xxd.ui/org.eclipse.chemclipse.ux.extension.xxd.ui.toolbar.SettingsHandlerQuantitation";
	private static final String IMAGE_HIDE = IApplicationImage.IMAGE_QUANTITATION_ACTIVE;
	private static final String IMAGE_SHOW = IApplicationImage.IMAGE_QUANTITATION_DEFAULT;
	//
	private static boolean partsAreActivated = false;

	public static void activateReferencedParts() {

		IGroupHandler groupHandler = new GroupHandlerScans();
		groupHandler.activateParts();
	}

	@Override
	public List<IPartHandler> getPartHandler() {

		List<IPartHandler> partHandler = new ArrayList<>();
		//
		partHandler.add(new PartHandler("Peak Quantitation List", PartSupport.PARTDESCRIPTOR_PEAK_QUANTITATION_LIST, PreferenceConstants.P_STACK_POSITION_PEAK_QUANTITATION_LIST));
		partHandler.add(new PartHandler("Quantitation", PartSupport.PARTDESCRIPTOR_QUANTITATION, PreferenceConstants.P_STACK_POSITION_QUANTITATION));
		partHandler.add(new PartHandler("Integration", PartSupport.PARTDESCRIPTOR_INTEGRATION_AREA, PreferenceConstants.P_STACK_POSITION_INTEGRATION_AREA));
		//
		return partHandler;
	}

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public String getSettingsContributionURI() {

		return SETTINGS_CONTRIBUTION_URI;
	}

	@Override
	public String getImageHide() {

		return IMAGE_HIDE;
	}

	@Override
	public String getImageShow() {

		return IMAGE_SHOW;
	}

	@Override
	public boolean toggleShow() {

		partsAreActivated = !partsAreActivated;
		return partsAreActivated;
	}
}
