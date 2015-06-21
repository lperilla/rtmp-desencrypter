package org.lperilla.rtmp.desencrypter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;

public class MainFrame extends JFrame {

	private static Logger logger = LogManager.getLogger(MainFrame.class);

	private static final long serialVersionUID = -6019135801173116793L;

	private JPanel channelsContentPanel;

	private JScrollPane channelsScrollPane;

	public MainFrame() throws HeadlessException {
		this.init();
	}

	private void init() {
		logger.info("-------------Iniciando Aplicación-------------");
		this.getContentPane().setLayout(new BorderLayout());

		this.setJMenuBar(new MainMenuBar());

		JPanel all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

		this.getContentPane().setLayout(new BorderLayout());

		this.getChannelsScrollPane().setViewportView(this.getChannelsContentPanel());
		all.add(this.getChannelsScrollPane());

		StatusBar bar = new StatusBar();
		bar.setMessage("Ready...");
		this.getContentPane().add(all, BorderLayout.CENTER);
		this.getContentPane().add(bar, BorderLayout.SOUTH);

		this.setTitle("RTMP Desencrypter v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel getChannelsContentPanel() {
		if (this.channelsContentPanel == null) {
			this.channelsContentPanel = new JPanel();
			this.channelsContentPanel.setLayout(new BoxLayout(channelsContentPanel, BoxLayout.Y_AXIS));
		}
		return channelsContentPanel;
	}

	public void setChannelsContentPanel(JPanel content) {
		this.channelsContentPanel = content;
	}

	public JScrollPane getChannelsScrollPane() {
		if (this.channelsScrollPane == null) {
			this.channelsScrollPane = new JScrollPane();
			this.channelsScrollPane.setAutoscrolls(true);
			this.channelsScrollPane.setMinimumSize(new Dimension(200, 0));
		}
		return channelsScrollPane;
	}

	public void setChannelsScrollPane(JScrollPane channelsScrollPane) {
		this.channelsScrollPane = channelsScrollPane;
	}

	/**
	 * Metodo que pinta el listado de canales
	 * 
	 * @param channels
	 *            , lista de canales
	 */
	public void loadChannels(final List<ChannelBean> channels) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int index = 0;
				getChannelsContentPanel().removeAll();
				for (ChannelBean channelBean : channels) {
					ChannelPanel channelPanel = new ChannelPanel(channelBean);
					channelPanel.setBackground((index % 2 == 0) ? Color.decode("#CCCCCC") : Color.decode("#EEEEEE"));
					getChannelsContentPanel().add(channelPanel);
					index++;
				}

				StringBuffer message = new StringBuffer();
				message.append("El fichero se ha cargado correctamente!\n");
				message.append(String.format("Se han detectado %d canales.", channels.size()));
				GuiPackage.showInfoMessage(message.toString(), "Fichero cargado exitosamente!");

				getChannelsContentPanel().revalidate();
			}
		});
	}
}
