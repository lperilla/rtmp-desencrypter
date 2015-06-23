package org.lperilla.rtmp.desencrypter.actions;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.action.Command;
import org.lperilla.framework.core.exceptions.IllegalUserActionException;
import org.lperilla.framework.core.utils.QRCodeUtil;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;
import org.lperilla.rtmp.desencrypter.gui.GuiPackage;

import com.google.zxing.WriterException;

public class QRCodeGenerator implements Command {

	private static Logger logger = LogManager.getLogger(Load.class);

	private static final int QR_CODE_SIZE = 150;

	private static final Set<String> commands = new HashSet<String>();

	public QRCodeGenerator() {
		commands.add(ActionNames.QR_CODE_GENERATOR);
	}

	public void doAction(ActionEvent e) throws IllegalUserActionException {
		ChannelBean channel = GuiPackage.getInstance().getChannelPanel().getChannel();
		try {
			ImageIcon icon = new ImageIcon();
			icon.setImage(QRCodeUtil.generateQRCode(channel.getUrl(), 150, QR_CODE_SIZE));
			JOptionPane.showMessageDialog(null, icon, "QR Code: " + channel.getName(), JOptionPane.PLAIN_MESSAGE);
		} catch (WriterException ex) {
			GuiPackage.showErrorMessage(ex.getMessage(), "Error al generar el codigo QR");
			logger.error(ex.getMessage(), e);
		}
	}

	public Set<String> getActionNames() {
		return commands;
	}
}
