/*******************************************************************************
 * Copyright (c) 2013, 2017 Lablicate GmbH.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.ux.extension.wsd.ui.support;

import java.io.File;

import org.eclipse.chemclipse.model.core.IChromatogram;
import org.eclipse.chemclipse.model.core.IChromatogramOverview;
import org.eclipse.chemclipse.support.events.IChemClipseEvents;
import org.eclipse.chemclipse.support.ui.addons.ModelSupportAddon;
import org.eclipse.chemclipse.ux.extension.ui.provider.AbstractSupplierFileEditorSupport;
import org.eclipse.chemclipse.ux.extension.ui.provider.IChromatogramEditorSupport;
import org.eclipse.chemclipse.ux.extension.wsd.ui.editors.ChromatogramEditorWSD;
import org.eclipse.chemclipse.ux.extension.wsd.ui.internal.support.ChromatogramIdentifier;
import org.eclipse.chemclipse.wsd.converter.chromatogram.ChromatogramConverterWSD;
import org.eclipse.e4.core.services.events.IEventBroker;

public class ChromatogramEditorSupport extends AbstractSupplierFileEditorSupport implements IChromatogramEditorSupport {

	public ChromatogramEditorSupport() {
		super(ChromatogramConverterWSD.getChromatogramConverterSupport().getSupplier());
	}

	@Override
	public String getType() {

		return TYPE_WSD;
	}

	@Override
	public void openEditor(File file) {

		/*
		 * Check that the selected file or directory is a valid chromatogram.
		 */
		if(ChromatogramIdentifier.isChromatogram(file) || ChromatogramIdentifier.isChromatogramDirectory(file)) {
			openEditor(file, null, ChromatogramEditorWSD.ID, ChromatogramEditorWSD.CONTRIBUTION_URI, ChromatogramEditorWSD.ICON_URI, ChromatogramEditorWSD.TOOLTIP);
		}
	}

	@Override
	public void openEditor(IChromatogram chromatogram) {

		openEditor(null, chromatogram, ChromatogramEditorWSD.ID, ChromatogramEditorWSD.CONTRIBUTION_URI, ChromatogramEditorWSD.ICON_URI, ChromatogramEditorWSD.TOOLTIP);
	}

	@Override
	public void openOverview(final File file) {

		/*
		 * Check that the selected file or directory is a valid chromatogram.
		 */
		if(ChromatogramIdentifier.isChromatogram(file) || ChromatogramIdentifier.isChromatogramDirectory(file)) {
			/*
			 * Push an event
			 * IChromatogramEvents.PROPERTY_CHROMATOGRAM_OVERVIEW_FILE
			 */
			IEventBroker eventBroker = ModelSupportAddon.getEventBroker();
			eventBroker.send(IChemClipseEvents.TOPIC_CHROMATOGRAM_WSD_UPDATE_RAWFILE, file);
		}
	}

	@Override
	public void openOverview(IChromatogramOverview chromatogramOverview) {

		IEventBroker eventBroker = ModelSupportAddon.getEventBroker();
		eventBroker.send(IChemClipseEvents.TOPIC_CHROMATOGRAM_WSD_UPDATE_OVERVIEW, chromatogramOverview);
	}
}
