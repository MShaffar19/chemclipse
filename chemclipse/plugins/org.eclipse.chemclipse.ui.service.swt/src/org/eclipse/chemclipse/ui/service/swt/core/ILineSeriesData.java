/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.ui.service.swt.core;

import org.eclipse.swt.graphics.Color;
import org.swtchart.ILineSeries.PlotSymbolType;

public interface ILineSeriesData extends ISeriesData {

	boolean isEnableArea();

	void setEnableArea(boolean enableArea);

	PlotSymbolType getSymbolType();

	void setSymbolType(PlotSymbolType symbolType);

	int getSymbolSize();

	void setSymbolSize(int symbolSize);

	Color getLineColor();

	void setLineColor(Color lineColor);

	int getLineWidth();

	void setLineWidth(int lineWidth);

	boolean isEnableStack();

	void setEnableStack(boolean enableStack);

	boolean isEnableStep();

	void setEnableStep(boolean enableStep);
}