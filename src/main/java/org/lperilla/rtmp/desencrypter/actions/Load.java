package org.lperilla.rtmp.desencrypter.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.action.Command;
import org.lperilla.framework.core.exceptions.IllegalUserActionException;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;
import org.lperilla.rtmp.desencrypter.gui.GuiPackage;
import org.lperilla.rtmp.desencrypter.service.RTMPServiceImpl;
import org.lperilla.rtmp.desencrypter.util.FileDialoger;

public class Load implements Command {

	private static Logger logger = LogManager.getLogger(Load.class);

	private static final Set<String> commands = new HashSet<String>();

	private RTMPServiceImpl serviceImpl;

	/**
	 * Contructor de la clase
	 */
	public Load() {
		this.setServiceImpl(new RTMPServiceImpl());
		commands.add(ActionNames.OPEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lperilla.framework.core.action.Command#doAction(java.awt.event.
	 * ActionEvent)
	 */
	public void doAction(ActionEvent e) throws IllegalUserActionException {
		final JFileChooser chooser = FileDialoger.promptToOpenFile(new String[] { ".txt" });
		if (chooser == null) {
			return;
		}

		final File selectedFile = chooser.getSelectedFile();
		if (selectedFile != null) {
			try {
				List<ChannelBean> channels = this.getServiceImpl().loadChannelFile(selectedFile);
				if (channels != null) {
					logger.info("----------------------------------------------------------");
					logger.info("Cantidad de canales mapeados: " + channels.size());
					logger.info("Canales: " + channels);
					logger.info("----------------------------------------------------------");
					GuiPackage.getInstance().loadChannels(channels);
				}
			} catch (Exception ex) {
				logger.error(ex);
				GuiPackage.showErrorMessage(ex.toString(), "Error when load File");
			}
		}
	}

	public Set<String> getActionNames() {
		return commands;
	}

	public RTMPServiceImpl getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(RTMPServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}
}
