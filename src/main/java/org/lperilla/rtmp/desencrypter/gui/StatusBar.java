package org.lperilla.rtmp.desencrypter.gui;

import java.awt.Dimension;

import javax.swing.JLabel;

public class StatusBar extends JLabel {

	private static final long serialVersionUID = -2404274683307016469L;

	public StatusBar() {
		super();
		super.setPreferredSize(new Dimension(100, 16));
		this.setMessage("Ready");
	}

	public void setMessage(String message) {
		this.setText(message);
	}
}
