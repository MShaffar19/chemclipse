/*******************************************************************************
 * Copyright (c) 2012, 2016 Dr. Philip Wenig.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.chemclipse.rcp.app.ui.handlers;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;

@SuppressWarnings("restriction")
public class PreferencesHandler {

	@Execute
	void execute(ECommandService commandService, EHandlerService handlerService, Logger logger) {

		ParameterizedCommand command = commandService.createCommand("org.eclipse.ui.window.preferences", null);
		if(handlerService.canExecute(command)) {
			handlerService.executeHandler(command);
		} else {
			logger.warn("Can't handle to open the preference dialog.");
		}
	}
}
