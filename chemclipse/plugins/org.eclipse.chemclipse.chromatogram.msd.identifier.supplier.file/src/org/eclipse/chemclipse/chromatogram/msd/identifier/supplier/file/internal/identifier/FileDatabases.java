/*******************************************************************************
 * Copyright (c) 2016 Lablicate UG (haftungsbeschränkt).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.identifier.supplier.file.internal.identifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.chemclipse.chromatogram.msd.identifier.supplier.file.preferences.PreferenceSupplier;
import org.eclipse.chemclipse.logging.core.Logger;
import org.eclipse.chemclipse.model.comparator.SortOrder;
import org.eclipse.chemclipse.model.identifier.IIdentificationTarget;
import org.eclipse.chemclipse.model.identifier.ILibraryInformation;
import org.eclipse.chemclipse.msd.converter.massspectrum.MassSpectrumConverter;
import org.eclipse.chemclipse.msd.converter.processing.massspectrum.IMassSpectrumImportConverterProcessingInfo;
import org.eclipse.chemclipse.msd.model.core.IMassSpectra;
import org.eclipse.chemclipse.msd.model.core.IRegularLibraryMassSpectrum;
import org.eclipse.chemclipse.msd.model.core.IScanMSD;
import org.eclipse.chemclipse.msd.model.core.comparator.IonAbundanceComparator;
import org.eclipse.chemclipse.processing.core.exceptions.TypeCastException;
import org.eclipse.core.runtime.IProgressMonitor;

public class FileDatabases {

	private static final Logger logger = Logger.getLogger(FileDatabases.class);
	/*
	 * Don't reload the database on each request, only if it is necessary.
	 */
	private static Map<String, Long> fileSizes;
	private static Set<String> fileNames;
	private static Map<String, IMassSpectra> massSpectraDatabases;
	private static Map<String, Map<String, IScanMSD>> allDatabaseNames = null;
	private static Map<String, Map<String, IScanMSD>> allDatabaseCasNumbers = null;
	//
	private IonAbundanceComparator ionAbundanceComparator;

	public FileDatabases() {
		/*
		 * Initialize the static maps once.
		 */
		ionAbundanceComparator = new IonAbundanceComparator(SortOrder.DESC);
		initializeDatabaseMaps();
	}

	/**
	 * Used to sort the ion list.
	 * 
	 * @return {@link IonAbundanceComparator}
	 */
	public IonAbundanceComparator getIonAbundanceComparator() {

		return ionAbundanceComparator;
	}

	public Map<String, IMassSpectra> getDatabases(List<String> databaseList, IProgressMonitor monitor) throws FileNotFoundException {

		List<String> databaseNames = new ArrayList<String>();
		for(String database : databaseList) {
			try {
				File file = new File(database);
				String databaseName = file.getName();
				databaseNames.add(databaseName);
				if(file.exists()) {
					/*
					 * Make further checks.
					 */
					if(massSpectraDatabases.get(databaseName) == null) {
						loadMassSpectraFromFile(file, monitor);
					} else {
						/*
						 * Has the content been edited?
						 */
						if(file.length() != fileSizes.get(databaseName) || !fileNames.contains(databaseName)) {
							loadMassSpectraFromFile(file, monitor);
						}
					}
				}
			} catch(TypeCastException e) {
				logger.warn(e);
			}
		}
		/*
		 * Remove unused databases and info maps.
		 */
		Set<String> databaseKeys = massSpectraDatabases.keySet();
		for(String databaseKey : databaseKeys) {
			if(!databaseNames.contains(databaseKey)) {
				massSpectraDatabases.remove(databaseKey);
				allDatabaseNames.remove(databaseKey);
				allDatabaseCasNumbers.remove(databaseKey);
			}
		}
		/*
		 * Post-check
		 */
		if(massSpectraDatabases.size() == 0) {
			throw new FileNotFoundException();
		}
		//
		return massSpectraDatabases;
	}

	/**
	 * 
	 * @param identificationTarget
	 * @param monitor
	 * @return
	 */
	public List<IScanMSD> getDatabaseMassSpectra(IIdentificationTarget identificationTarget, IProgressMonitor monitor) {

		List<IScanMSD> massSpectra = new ArrayList<IScanMSD>();
		if(identificationTarget != null) {
			//
			try {
				/*
				 * Only evaluate the target if it contains the signature
				 * of this plugin.
				 */
				ILibraryInformation libraryInformation = identificationTarget.getLibraryInformation();
				String databaseName = libraryInformation.getDatabase();
				//
				Map<String, Map<String, IScanMSD>> allDatabaseNames = getDatabaseNamesMap(monitor);
				Map<String, Map<String, IScanMSD>> allDatabaseCasNumbers = getDatabaseCasNamesMap(monitor);
				Map<String, IScanMSD> databaseNames = allDatabaseNames.get(databaseName);
				Map<String, IScanMSD> databaseCasNumbers = allDatabaseCasNumbers.get(databaseName);
				/*
				 * Find reference by name.
				 */
				IScanMSD reference = null;
				if(databaseNames != null) {
					String name = libraryInformation.getName();
					reference = databaseNames.get(name);
					if(reference != null) {
						massSpectra.add(reference);
					}
				}
				/*
				 * Find reference by CAS# if not found earlier.
				 */
				if(reference == null) {
					if(databaseCasNumbers != null) {
						String casNumber = libraryInformation.getCasNumber();
						reference = databaseCasNumbers.get(casNumber);
						if(reference != null) {
							massSpectra.add(reference);
						}
					}
				}
			} catch(FileNotFoundException e) {
				logger.warn(e);
			}
		}
		return massSpectra;
	}

	private Map<String, Map<String, IScanMSD>> getDatabaseNamesMap(IProgressMonitor monitor) throws FileNotFoundException {

		getDatabases(PreferenceSupplier.getMassSpectraFiles(), monitor);
		return allDatabaseNames;
	}

	private Map<String, Map<String, IScanMSD>> getDatabaseCasNamesMap(IProgressMonitor monitor) throws FileNotFoundException {

		getDatabases(PreferenceSupplier.getMassSpectraFiles(), monitor);
		return allDatabaseCasNumbers;
	}

	private void loadMassSpectraFromFile(File file, IProgressMonitor monitor) throws TypeCastException {

		IMassSpectrumImportConverterProcessingInfo infoConvert = MassSpectrumConverter.convert(file, monitor);
		IMassSpectra massSpectraDatabase = infoConvert.getMassSpectra();
		/*
		 * Add the datababse to databases.
		 */
		String databaseName = file.getName();
		massSpectraDatabases.put(databaseName, massSpectraDatabase);
		fileNames.add(databaseName);
		fileSizes.put(databaseName, file.length());
		/*
		 * Initialize the reference maps.
		 */
		Map<String, IScanMSD> databaseNames = allDatabaseNames.get(databaseName);
		if(databaseNames == null) {
			databaseNames = new HashMap<String, IScanMSD>();
			allDatabaseNames.put(databaseName, databaseNames);
		}
		//
		Map<String, IScanMSD> databaseCasNumbers = allDatabaseCasNumbers.get(databaseName);
		if(databaseCasNumbers == null) {
			databaseCasNumbers = new HashMap<String, IScanMSD>();
			allDatabaseCasNumbers.put(databaseName, databaseCasNumbers);
		}
		//
		for(IScanMSD reference : massSpectraDatabase.getList()) {
			//
			if(reference instanceof IRegularLibraryMassSpectrum) {
				IRegularLibraryMassSpectrum libraryMassSpectrum = (IRegularLibraryMassSpectrum)reference;
				ILibraryInformation libraryInformation = libraryMassSpectrum.getLibraryInformation();
				databaseNames.put(libraryInformation.getName(), reference);
				databaseCasNumbers.put(libraryInformation.getCasNumber(), reference);
			}
		}
	}

	private void initializeDatabaseMaps() {

		if(fileSizes == null) {
			fileSizes = new HashMap<String, Long>();
		}
		//
		if(fileNames == null) {
			fileNames = new HashSet<String>();
		}
		//
		if(massSpectraDatabases == null) {
			massSpectraDatabases = new HashMap<String, IMassSpectra>();
		}
		//
		if(allDatabaseNames == null) {
			allDatabaseNames = new HashMap<String, Map<String, IScanMSD>>();
		}
		//
		if(allDatabaseCasNumbers == null) {
			allDatabaseCasNumbers = new HashMap<String, Map<String, IScanMSD>>();
		}
	}
}
