package org.lperilla.rtmp.desencrypter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.framework.core.action.ActionRouter;
import org.lperilla.framework.core.components.JLabeledTextField;
import org.lperilla.rtmp.desencrypter.actions.ActionNames;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;
import org.lperilla.rtmp.desencrypter.util.Util;

public class ChannelPanel extends JPanel implements ActionListener {

	private static Logger logger = LogManager.getLogger(ChannelPanel.class);

	private static final long serialVersionUID = 5642767837284393135L;

	private ChannelBean channel;

	private JButton executeBtn;

	private JButton qrcodeBtn;

	private JPanel infoPanel;

	private JPanel buttonPanel;

	private JLabeledTextField channelTxf;

	private JLabeledTextField programmingTxf;

	/**
	 * Constructor de la clase
	 * 
	 * @param channel
	 */
	public ChannelPanel(ChannelBean channel) {
		this.setChannel(channel);
		this.init();
	}

	private void init() {
		logger.info(String.format("Creating Channel Panel for %s", this.getChannel().getName()));
		logger.info(String.format("Url Streaming: %s", this.getChannel().getUrl()));
		logger.info(String.format("Url Logo Chanel: %s", this.getChannel().getUrlImage()));
		this.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel(this.getChannel().getName());
		Font curFont = titleLabel.getFont();
		titleLabel.setFont(curFont.deriveFont((float) curFont.getSize() + 4));
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.getChannelTxf().setText(this.getChannel().getUrl());
		this.getProgrammingTxf().setText(this.getChannel().getUrlProgramming());

		// Panel de contenido
		this.getInfoPanel().add(titleLabel);
		this.getInfoPanel().add(this.getChannelTxf());
		this.getInfoPanel().add(this.getProgrammingTxf());

		// Panel de Botones
		this.getButtonPanel().add(this.getExecuteBtn());
		this.getButtonPanel().add(this.getQrcodeBtn());

		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		this.add(Box.createHorizontalGlue());

		this.add(new JLabel(Util.getLogoChannel(this.getChannel().getUrlImage()), JLabel.CENTER), BorderLayout.WEST);
		this.add(this.getInfoPanel(), BorderLayout.CENTER);
		this.add(this.getButtonPanel(), BorderLayout.SOUTH);

	}

	public ChannelBean getChannel() {
		return channel;
	}

	public void setChannel(ChannelBean channel) {
		this.channel = channel;
	}

	public JPanel getInfoPanel() {
		if (this.infoPanel == null) {
			this.infoPanel = new JPanel();
			this.infoPanel.setLayout(new BoxLayout(this.infoPanel, BoxLayout.Y_AXIS));
			this.infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			this.infoPanel.add(Box.createHorizontalGlue());
		}
		return infoPanel;
	}

	public JPanel getButtonPanel() {
		if (this.buttonPanel == null) {
			this.buttonPanel = new JPanel();
			this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.X_AXIS));
			this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			this.buttonPanel.add(Box.createHorizontalGlue());
		}
		return buttonPanel;
	}

	public JButton getExecuteBtn() {
		if (this.executeBtn == null) {
			this.executeBtn = new JButton("Ver streaming");
			this.executeBtn.addActionListener(this);
			this.executeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.executeBtn.setActionCommand(ActionNames.EXECUTE_STREAMING);
		}
		return executeBtn;
	}

	public JButton getQrcodeBtn() {
		if (this.qrcodeBtn == null) {
			this.qrcodeBtn = new JButton("QR Code");
			this.qrcodeBtn.addActionListener(this);
			this.qrcodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.qrcodeBtn.setActionCommand(ActionNames.QR_CODE_GENERATOR);
		}
		return qrcodeBtn;
	}

	public JLabeledTextField getChannelTxf() {
		if (this.channelTxf == null) {
			this.channelTxf = new JLabeledTextField("Url:", 68);
			this.channelTxf.getTextField().setEditable(false);
			this.channelTxf.getTextField().setBorder(BorderFactory.createLineBorder(this.getBackground()));
			this.channelTxf.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		return this.channelTxf;
	}

	public JLabeledTextField getProgrammingTxf() {
		if (this.programmingTxf == null) {
			this.programmingTxf = new JLabeledTextField("Url Programación:", 60);
			this.programmingTxf.getTextField().setEditable(false);
			this.programmingTxf.getTextField().setBorder(BorderFactory.createLineBorder(this.getBackground()));
			this.programmingTxf.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		return this.programmingTxf;
	}

	public void setBackground(Color color) {
		super.setBackground(color);
		this.getChannelTxf().setBackground(color);
		this.getProgrammingTxf().setBackground(color);
		this.getInfoPanel().setBackground(color);
		this.getButtonPanel().setBackground(color);
	}

	public void actionPerformed(ActionEvent e) {
		GuiPackage.getInstance().setChannelPanel(this);
		ActionRouter.getInstance().actionPerformed(new ActionEvent(e, e.getID(), e.getActionCommand()));
	}

}