/*******************************************************************************
 * Copyright (c) 2013, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.chromatogram.msd.quantitation.supplier.chemclipse.database;

import org.eclipse.chemclipse.database.exceptions.NoDatabaseAvailableException;
import org.eclipse.chemclipse.database.model.DatabaseProxy;

public class QuantDatabaseProxy extends DatabaseProxy implements IQuantDatabaseProxy {

	public QuantDatabaseProxy(String databaseUrl, String databaseName) {
		super(databaseUrl, databaseName);
	}

	public QuantDatabaseProxy(String databaseUrl, String databaseName, String username, String password) {
		super(databaseUrl, databaseName, username, password);
	}

	@Override
	public IQuantDatabase getDatabase() throws NoDatabaseAvailableException {

		QuantDatabases databases = new QuantDatabases();
		IQuantDatabase database = databases.getDatabase(this);
		return database;
	}
}
