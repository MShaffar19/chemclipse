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
package org.eclipse.chemclipse.ux.extension.msd.ui.handlers;

import java.io.File;
import java.util.List;

import javax.inject.Named;

import org.eclipse.chemclipse.support.ui.wizards.ChromatogramWizardElements;
import org.eclipse.chemclipse.support.ui.wizards.IChromatogramWizardElements;
import org.eclipse.chemclipse.ux.extension.msd.ui.support.ChromatogramSupport;
import org.eclipse.chemclipse.ux.extension.msd.ui.wizards.ChromatogramInputEntriesWizard;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {

		IChromatogramWizardElements chromatogramWizardElements = new ChromatogramWizardElements();
		ChromatogramInputEntriesWizard inputWizard = new ChromatogramInputEntriesWizard(chromatogramWizardElements);
		WizardDialog wizardDialog = new WizardDialog(shell, inputWizard);
		wizardDialog.create();
		/*
		 * If OK
		 */
		if(wizardDialog.open() == WizardDialog.OK) {
			/*
			 * Get the list of selected chromatograms.
			 */
			List<String> selectedChromatograms = chromatogramWizardElements.getSelectedChromatograms();
			if(selectedChromatograms.size() > 0) {
				/*
				 * If it contains at least 1 element, add it to the input files list.
				 */
				for(String chromatogram : selectedChromatograms) {
					File file = new File(chromatogram);
					ChromatogramSupport.getInstanceEditorSupport().openEditor(file);
				}
			}
		}
	}
}
