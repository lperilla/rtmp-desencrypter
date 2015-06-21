package org.lperilla.rtmp.desencrypter.actions;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import org.lperilla.framework.core.action.Command;
import org.lperilla.framework.core.exceptions.IllegalUserActionException;

public class Exit implements Command {

	private static final Set<String> commands = new HashSet<String>();
		
    static {
        commands.add(ActionNames.EXIT);
    }	
	
	public void doAction(ActionEvent e) throws IllegalUserActionException {
		System.exit(0);
	}

	public Set<String> getActionNames() {
		return commands;
	}

}
