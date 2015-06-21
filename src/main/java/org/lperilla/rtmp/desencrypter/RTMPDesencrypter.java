package org.lperilla.rtmp.desencrypter;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.utils.ComponentUtil;
import org.lperilla.rtmp.desencrypter.gui.GuiPackage;
import org.lperilla.rtmp.desencrypter.gui.MainFrame;

public class RTMPDesencrypter {

	private static Logger logger = LogManager.getLogger(RTMPDesencrypter.class);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			logger.warn(e.getMessage());
		} catch (InstantiationException e) {
			logger.warn(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.warn(e.getMessage());
		} catch (UnsupportedLookAndFeelException e) {
			logger.warn(e.getMessage());
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame frame = new MainFrame();
				ComponentUtil.centerComponentInWindow(frame, 50);
				GuiPackage.getInstance(frame);
				frame.setVisible(true);
			}
		});
	}

}
