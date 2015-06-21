package org.lperilla.rtmp.desencrypter.actions;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.action.Command;
import org.lperilla.framework.core.exceptions.IllegalUserActionException;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;
import org.lperilla.rtmp.desencrypter.gui.GuiPackage;
import org.lperilla.rtmp.desencrypter.util.ExecuteVLC;

public class ExecuteStreaming implements Command {

	private static Logger logger = LogManager.getLogger(ExecuteStreaming.class);

	private static final Set<String> commands = new HashSet<String>();

	static {
		commands.add(ActionNames.EXECUTE_STREAMING);
	}

	public void doAction(ActionEvent e) throws IllegalUserActionException {
		ChannelBean channel = GuiPackage.getInstance().getChannelPanel().getChannel();
		logger.info("-----------------------------------------------");
		logger.info("Command: " + e.getActionCommand());
		logger.info("Channel: " + channel.getName());
		logger.info("Url: " + channel.getUrl());
		logger.info("-----------------------------------------------");
		System.setProperty("http.agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.2; Nexus 4 Build/KOT49H)");
		new ExecuteVLC(channel.getUrl());
	}

	public Set<String> getActionNames() {
		return commands;
	}

}
