/*******************************************************************************
 * Copyright (c) 2016, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.process.supplier.workflows.preferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.chemclipse.chromatogram.msd.identifier.massspectrum.IMassSpectrumIdentifierSupport;
import org.eclipse.chemclipse.chromatogram.msd.identifier.massspectrum.MassSpectrumIdentifier;
import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.workflows.Activator;
import org.eclipse.chemclipse.model.identifier.core.ISupplier;
import org.eclipse.chemclipse.support.preferences.IPreferenceSupplier;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class PreferenceSupplier implements IPreferenceSupplier {

	public static final String P_SAMPLEQUANT_FILTER_PATH_CHROMATOGRAM = "samplequantFilterPathChromatogram";
	public static final String DEF_SAMPLEQUANT_FILTER_PATH_CHROMATOGRAM = "";
	public static final String P_SAMPLEQUANT_FILTER_PATH_RTERES = "samplequantFilterPathRteres";
	public static final String DEF_SAMPLEQUANT_FILTER_PATH_RTERES = "";
	public static final String P_SAMPLEQUANT_FILTER_PATH_SUMRPT = "samplequantFilterPathSumRpt";
	public static final String DEF_SAMPLEQUANT_FILTER_PATH_SUMRPT = "";
	public static final String P_SAMPLEQUANT_FILTER_PATH_TARGETS = "samplequantFilterPathTargets";
	public static final String DEF_SAMPLEQUANT_FILTER_PATH_TARGETS = "";
	public static final double SAMPLEQUANT_MATCH_QUALITY_MIN = 0.0d;
	public static final double SAMPLEQUANT_MATCH_QUALITY_MAX = 100.0d;
	public static final double SAMPLEQUANT_MATCH_QUALITY_DEF = 95.0d;
	public static final String P_SAMPLEQUANT_MIN_MATCH_QUALITY = "samplequantMinMatchQuality";
	public static final double DEF_SAMPLEQUANT_MIN_MATCH_QUALITY = SAMPLEQUANT_MATCH_QUALITY_DEF;
	public static final String P_SAMPLEQUANT_SCAN_IDENTIFIER = "samplequantScanIdentifier";
	public static final String DEF_SAMPLEQUANT_SCAN_IDENTIFIER = "";
	public static final String P_SAMPLEQUANT_SEARCH_CASE_SENSITIVE = "samplequantSearchCaseSensitive"; // $NON-NLS-1$
	public static final boolean DEF_SAMPLEQUANT_SEARCH_CASE_SENSITIVE = false; // $NON-NLS-1$
	//
	private static IPreferenceSupplier preferenceSupplier;

	public static IPreferenceSupplier INSTANCE() {

		if(preferenceSupplier == null) {
			preferenceSupplier = new PreferenceSupplier();
		}
		return preferenceSupplier;
	}

	@Override
	public IScopeContext getScopeContext() {

		return InstanceScope.INSTANCE;
	}

	@Override
	public String getPreferenceNode() {

		return Activator.getContext().getBundle().getSymbolicName();
	}

	@Override
	public Map<String, String> getDefaultValues() {

		Map<String, String> defaultValues = new HashMap<String, String>();
		//
		defaultValues.put(P_SAMPLEQUANT_FILTER_PATH_CHROMATOGRAM, DEF_SAMPLEQUANT_FILTER_PATH_CHROMATOGRAM);
		defaultValues.put(P_SAMPLEQUANT_FILTER_PATH_RTERES, DEF_SAMPLEQUANT_FILTER_PATH_RTERES);
		defaultValues.put(P_SAMPLEQUANT_FILTER_PATH_SUMRPT, DEF_SAMPLEQUANT_FILTER_PATH_SUMRPT);
		defaultValues.put(P_SAMPLEQUANT_FILTER_PATH_TARGETS, DEF_SAMPLEQUANT_FILTER_PATH_TARGETS);
		defaultValues.put(P_SAMPLEQUANT_MIN_MATCH_QUALITY, Double.toString(DEF_SAMPLEQUANT_MIN_MATCH_QUALITY));
		defaultValues.put(P_SAMPLEQUANT_SEARCH_CASE_SENSITIVE, Boolean.toString(DEF_SAMPLEQUANT_SEARCH_CASE_SENSITIVE));
		//
		return defaultValues;
	}

	@Override
	public IEclipsePreferences getPreferences() {

		return getScopeContext().getNode(getPreferenceNode());
	}

	public static String[][] getMassSpectrumIdentifier() {

		try {
			IMassSpectrumIdentifierSupport support = MassSpectrumIdentifier.getMassSpectrumIdentifierSupport();
			List<String> ids = support.getAvailableIdentifierIds();
			//
			String[][] elements = new String[ids.size() + 1][2];
			for(int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				ISupplier supplier = support.getIdentifierSupplier(id);
				elements[i][0] = supplier.getIdentifierName();
				elements[i][1] = supplier.getId();
			}
			//
			elements[ids.size()][0] = "";
			elements[ids.size()][1] = "";
			//
			return elements;
		} catch(Exception e) {
			//
			return new String[][]{{"No Mass Spectrum Identifier Available", ""}};
		}
	}
}
