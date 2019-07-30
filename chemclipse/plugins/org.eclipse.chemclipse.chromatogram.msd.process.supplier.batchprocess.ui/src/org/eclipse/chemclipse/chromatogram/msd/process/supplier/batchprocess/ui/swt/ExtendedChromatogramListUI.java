/*******************************************************************************
 * Copyright (c) 2018, 2019 Lablicate GmbH.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.ui.swt;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.model.BatchProcessJob;
import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.ui.Activator;
import org.eclipse.chemclipse.chromatogram.msd.process.supplier.batchprocess.ui.preferences.PreferencePage;
import org.eclipse.chemclipse.converter.model.ChromatogramInputEntry;
import org.eclipse.chemclipse.converter.model.IChromatogramInputEntry;
import org.eclipse.chemclipse.model.handler.IModificationHandler;
import org.eclipse.chemclipse.model.types.DataType;
import org.eclipse.chemclipse.rcp.ui.icons.core.ApplicationImageFactory;
import org.eclipse.chemclipse.rcp.ui.icons.core.IApplicationImage;
import org.eclipse.chemclipse.support.ui.workbench.DisplayUtils;
import org.eclipse.chemclipse.ux.extension.xxd.ui.part.support.SupplierEditorSupport;
import org.eclipse.chemclipse.ux.extension.xxd.ui.wizards.InputEntriesWizard;
import org.eclipse.chemclipse.ux.extension.xxd.ui.wizards.InputWizardSettings;
import org.eclipse.chemclipse.xxd.process.files.ISupplierFileIdentifier;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

public class ExtendedChromatogramListUI extends Composite {

	private ChromatogramListUI chromatogramListUI;
	//
	private IModificationHandler modificationHandler = null;
	private BatchProcessJob batchProcessJob;

	public ExtendedChromatogramListUI(Composite parent, int style) {
		super(parent, style);
		createControl();
	}

	public void update(BatchProcessJob batchProcessJob) {

		this.batchProcessJob = batchProcessJob;
		setProcessFiles();
	}

	public void setModificationHandler(IModificationHandler modificationHandler) {

		this.modificationHandler = modificationHandler;
	}

	private void createControl() {

		setLayout(new FillLayout());
		//
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		//
		createToolbarMain(composite);
		createChromatogramList(composite);
		createButtons(composite);
	}

	private void createToolbarMain(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = SWT.END;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(1, false));
		//
		createSettingsButton(composite);
	}

	private void createSettingsButton(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Open the Settings");
		button.setText("");
		button.setImage(ApplicationImageFactory.getInstance().getImage(IApplicationImage.IMAGE_CONFIGURE, IApplicationImage.SIZE_16x16));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				IPreferencePage preferencePage = new PreferencePage();
				preferencePage.setTitle("Batch Process");
				//
				PreferenceManager preferenceManager = new PreferenceManager();
				preferenceManager.addToRoot(new PreferenceNode("1", preferencePage));
				//
				PreferenceDialog preferenceDialog = new PreferenceDialog(DisplayUtils.getShell(), preferenceManager);
				preferenceDialog.create();
				preferenceDialog.setMessage("Settings");
				if(preferenceDialog.open() == Window.OK) {
					try {
						//
					} catch(Exception e1) {
						MessageDialog.openError(e.widget.getDisplay().getActiveShell(), "Settings", "Something has gone wrong to apply the settings.");
					}
				}
			}
		});
	}

	private void createChromatogramList(Composite parent) {

		chromatogramListUI = new ChromatogramListUI(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		chromatogramListUI.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createButtons(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = SWT.END;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(4, false));
		//
		createMoveUpButton(composite);
		createMoveDownButton(composite);
		createRemoveButton(composite);
		createAddButton(composite);
	}

	private void createMoveUpButton(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move import record(s) up");
		button.setText("");
		button.setImage(ApplicationImageFactory.getInstance().getImage(IApplicationImage.IMAGE_ARROW_UP_2, IApplicationImage.SIZE_16x16));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int index = chromatogramListUI.getTable().getSelectionIndex();
				if(index != -1) {
					//
					setProcessFiles();
					setEditorDirty(true);
				}
			}
		});
	}

	private void createMoveDownButton(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move import record(s) down");
		button.setText("");
		button.setImage(ApplicationImageFactory.getInstance().getImage(IApplicationImage.IMAGE_ARROW_DOWN_2, IApplicationImage.SIZE_16x16));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int index = chromatogramListUI.getTable().getSelectionIndex();
				if(index != -1) {
					//
					setProcessFiles();
					setEditorDirty(true);
				}
			}
		});
	}

	private void createRemoveButton(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Remove the import record(s)");
		button.setText("");
		button.setImage(ApplicationImageFactory.getInstance().getImage(IApplicationImage.IMAGE_REMOVE, IApplicationImage.SIZE_16x16));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if(batchProcessJob != null) {
					TableItem[] tableItems = chromatogramListUI.getTable().getSelection();
					for(TableItem tableItem : tableItems) {
						Object object = tableItem.getData();
						if(object instanceof IChromatogramInputEntry) {
							IChromatogramInputEntry entry = (IChromatogramInputEntry)object;
							batchProcessJob.getChromatogramInputEntries().remove(entry);
						}
					}
					setEditorDirty(true);
				}
				setProcessFiles();
			}
		});
	}

	private void createAddButton(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Add new chromatogram(s)");
		button.setText("");
		button.setImage(ApplicationImageFactory.getInstance().getImage(IApplicationImage.IMAGE_ADD, IApplicationImage.SIZE_16x16));
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if(batchProcessJob != null) {
					// new SupplierEditorSupport
					Collection<ISupplierFileIdentifier> list = new ArrayList<ISupplierFileIdentifier>();
					list.add(new SupplierEditorSupport(DataType.MSD));
					list.add(new SupplierEditorSupport(DataType.CSD));
					list.add(new SupplierEditorSupport(DataType.WSD));
					InputWizardSettings inputWizardSettings = new InputWizardSettings(Activator.getDefault().getPreferenceStore(), list);
					inputWizardSettings.setTitle("Chromatogram");
					inputWizardSettings.setDescription("Select chromatogram(s) to analyze.");
					Set<File> files = InputEntriesWizard.openWizard(getShell(), inputWizardSettings).keySet();
					for(File file : files) {
						if(batchProcessJob != null) {
							batchProcessJob.getChromatogramInputEntries().add(new ChromatogramInputEntry(file.getAbsolutePath()));
						}
					}
					if(!files.isEmpty()) {
						setEditorDirty(true);
						setProcessFiles();
					}
				}
			}
		});
	}

	private void setProcessFiles() {

		if(batchProcessJob != null) {
			chromatogramListUI.setInput(batchProcessJob.getChromatogramInputEntries());
		} else {
			chromatogramListUI.setInput(null);
		}
	}

	private void setEditorDirty(boolean dirty) {

		if(modificationHandler != null) {
			modificationHandler.setDirty(dirty);
		}
	}
}
