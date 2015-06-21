package org.lperilla.rtmp.desencrypter.gui;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;

public class GuiPackage {

	private static Logger logger = LogManager.getLogger(GuiPackage.class);

	/** Singleton instance. */
	private static GuiPackage guiPack;

	/** The main JMeter frame. */
	private MainFrame mainFrame;

	private ChannelPanel channelPanel;

	private GuiPackage(MainFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public ChannelPanel getChannelPanel() {
		return channelPanel;
	}

	public void setChannelPanel(ChannelPanel channelPanel) {
		this.channelPanel = channelPanel;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public static GuiPackage getInstance() {
		return guiPack;
	}

	/**
	 * Metodo que permite pintar los canales en la ventana
	 * @param channels listado de canales
	 */
	public void loadChannels(List<ChannelBean> channels) {
		this.getMainFrame().loadChannels(channels);
	}

	public static GuiPackage getInstance(MainFrame mainFrame) {
		if (guiPack == null) {
			guiPack = new GuiPackage(mainFrame);
		}
		return guiPack;
	}

	/**
	 * Metodo que muestra mensaje emergente de error
	 * @param message Texto del mensaje
	 * @param title Titulo del mensaje
	 */
	public static void showErrorMessage(final String message, final String title) {
		showMessage(message, title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Metodo que muestra mensaje emergente de informativo
	 * @param message Texto del mensaje
	 * @param title titulo del mensaje
	 */
	public static void showInfoMessage(final String message, final String title) {
		showMessage(message, title, JOptionPane.INFORMATION_MESSAGE);
	}


	/**
	 * Metodo que muestra mensaje emergente Warning
	 * @param message Texto del mensaje
	 * @param title titulo del mensaje
	 */
	public static void showWarningMessage(final String message, final String title) {
		showMessage(message, title, JOptionPane.WARNING_MESSAGE);
	}


	/**
	 * Metodo que muestra mensaje emergente
	 * @param message Texto del mensaje
	 * @param title titulo del mensaje
	 * @param type tipo del mensaje @see JOptionPane.ERROR_MESSAGE, JOptionPane.WARNING_MESSAGE, JOptionPane.INFORMATION_MESSAGE
	 */
	public static void showMessage(final String message, final String title, final int type) {
		if (guiPack == null) {
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.debug(String.format("Showing message: %s, title: %s, type: %d", message, title, type));
				JOptionPane.showMessageDialog(null, message, title, type);
			}
		});
	}

}
