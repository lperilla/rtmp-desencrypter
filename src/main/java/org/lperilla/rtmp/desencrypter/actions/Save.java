/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.lperilla.rtmp.desencrypter.actions;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.action.Command;
import org.lperilla.framework.core.exceptions.IllegalUserActionException;

/**
 * Save the current test plan; implements: Save Save TestPlan As Save
 * (Selection) As
 */
public class Save implements Command {

	private static final Set<String> commands = new HashSet<String>();

	private static Logger logger = LogManager.getLogger(Save.class);

	/**
	 * Constructor for the Save object.
	 */
	public Save() {
		commands.add(ActionNames.SAVE); // Save
	}

	public void doAction(ActionEvent e) throws IllegalUserActionException {
		logger.debug("Ejecutando");
	}

	/**
	 * Gets the ActionNames attribute of the Save object.
	 *
	 * @return the ActionNames value
	 */
	public Set<String> getActionNames() {
		return commands;
	}

}
