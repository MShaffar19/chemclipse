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

import java.util.List;

public interface IGroupHandler {

	List<IPartHandler> getPartHandler();

	void activateParts();

	void updateMenu();

	String getImageHide();

	String getImageShow();

	String getName();

	String getDirectToolItemId();

	String getDirectMenuItemId();

	String getSettingsContributionURI();

	boolean toggleShow();
}
