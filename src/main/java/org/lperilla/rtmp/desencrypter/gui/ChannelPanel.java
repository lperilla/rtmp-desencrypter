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

	private JPanel infoPnl;

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

		this.getInfoPnl().add(titleLabel);
		this.getInfoPnl().add(this.getChannelTxf());
		this.getInfoPnl().add(this.getProgrammingTxf());
		this.getInfoPnl().add(this.getExecuteBtn());

		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		this.add(Box.createHorizontalGlue());

		this.add(new JLabel(Util.getLogoChannel(this.getChannel().getUrlImage()), JLabel.CENTER), BorderLayout.WEST);
		this.add(this.getInfoPnl(), BorderLayout.CENTER);
	}

	public ChannelBean getChannel() {
		return channel;
	}

	public void setChannel(ChannelBean channel) {
		this.channel = channel;
	}

	public JPanel getInfoPnl() {
		if (this.infoPnl == null) {
			this.infoPnl = new JPanel();
			this.infoPnl.setLayout(new BoxLayout(this.infoPnl, BoxLayout.Y_AXIS));
			this.infoPnl.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			this.infoPnl.add(Box.createHorizontalGlue());
		}
		return infoPnl;
	}

	public JButton getExecuteBtn() {
		if (this.executeBtn == null) {
			this.executeBtn = new JButton("Ver streaming");
			this.executeBtn.setActionCommand(ActionNames.EXECUTE_STREAMING);
			this.executeBtn.addActionListener(this);
		}
		return executeBtn;
	}

	public void setExecuteBtn(JButton executeBtn) {
		this.executeBtn = executeBtn;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getExecuteBtn()) {
			GuiPackage.getInstance().setChannelPanel(this);
			logger.info(String.format("executing vlc player: vlc -vvv %s", this.getChannel().getUrl()));
			ActionRouter.getInstance().actionPerformed(new ActionEvent(e, e.getID(), e.getActionCommand()));
		}
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
		this.getInfoPnl().setBackground(color);
		this.getChannelTxf().setBackground(color);
		this.getProgrammingTxf().setBackground(color);
	}
}