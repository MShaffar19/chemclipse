/*******************************************************************************
 * Copyright (c) 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Jan Holy - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.ui.model;

import org.eclipse.chemclipse.chromatogram.xxd.process.supplier.pca.model.IVariable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AbstractVariableVisualization implements IVariableVisualization {

	private IntegerProperty color;
	private IVariable delegator;
	private StringProperty description;
	private BooleanProperty selected;
	private StringProperty type;
	private StringProperty value;

	public AbstractVariableVisualization(IVariable variableModel) {

		super();
		color = new SimpleIntegerProperty();
		this.delegator = variableModel;
	}

	@Override
	public IntegerProperty colorProperty() {

		return this.color;
	}

	@Override
	public int compareTo(IVariable o) {

		return delegator.compareTo(o);
	}

	@Override
	public StringProperty descriptionProperty() {

		if(description == null) {
			this.description = new SimpleStringProperty(delegator.getDescription());
		}
		return description;
	}

	@Override
	public int getColor() {

		return this.colorProperty().get();
	}

	@Override
	public String getDescription() {

		return delegator.getDescription();
	}

	@Override
	public String getType() {

		return delegator.getType();
	}

	@Override
	public String getValue() {

		return delegator.getValue();
	}

	@Override
	public boolean isSelected() {

		return delegator.isSelected();
	}

	@Override
	public BooleanProperty selectedProperty() {

		if(selected == null) {
			selected = new SimpleBooleanProperty(delegator.isSelected());
		}
		return selected;
	}

	@Override
	public void setColor(final int color) {

		this.colorProperty().set(color);
	}

	@Override
	public void setDescription(String description) {

		delegator.setDescription(description);
		if(this.delegator != null) {
			this.delegator.setValue(description);
		}
	}

	@Override
	public void setSelected(boolean selected) {

		delegator.setSelected(selected);
		if(this.selected != null) {
			this.selected.set(selected);
		}
	}

	@Override
	public void setType(String type) {

		delegator.setType(type);
		if(this.type != null) {
			this.type.set(type);
		}
	}

	@Override
	public void setValue(String value) {

		delegator.setValue(value);
		if(this.value != null) {
			this.value.set(value);
		}
	}

	@Override
	public StringProperty typeProperty() {

		if(type == null) {
			type = new SimpleStringProperty(delegator.getType());
		}
		return type;
	}

	@Override
	public StringProperty valueProperty() {

		if(value == null) {
			value = new SimpleStringProperty(delegator.getType());
		}
		return value;
	}
}
