package org.lperilla.rtmp.desencrypter.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.lperilla.framework.core.action.ActionRouter;
import org.lperilla.rtmp.desencrypter.actions.ActionNames;

public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = -8023671550874945754L;

	/**
	 * Menu archivos
	 */
	private JMenu fileMenu;

	/**
	 * Item del menu archivos, Salir
	 */
	private JMenuItem fileExit;

	/**
	 * Item del menu archivos, abrir fichero
	 */
	private JMenuItem fileLoad;

	/**
	 * Item del menu archivos, exportar canales a xml
	 */
	private JMenuItem fileSave;

	private JMenu helpMenu;

	public MainMenuBar() {
		this.init();
	}

	private void init() {
		this.getFileMenu().add(this.getFileLoad());
		this.getFileMenu().add(this.getFileSave());
		this.getFileMenu().add(this.getFileExit());
		this.add(this.getFileMenu());
		this.add(this.getHelpMenu());
	}

	public JMenu getFileMenu() {
		if (this.fileMenu == null) {
			this.fileMenu = new JMenu("File");
		}
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public JMenuItem getFileExit() {
		if (this.fileExit == null) {
			this.fileExit = new JMenuItem("Exit");
			this.fileExit.setActionCommand(ActionNames.EXIT);
			this.fileExit.addActionListener(ActionRouter.getInstance());
		}
		return fileExit;
	}

	public JMenuItem getFileLoad() {
		if (this.fileLoad == null) {
			this.fileLoad = new JMenuItem("Abrir", 'O');
			this.fileLoad.setAccelerator(KeyStrokes.OPEN);
			this.fileLoad.setActionCommand(ActionNames.OPEN);
			this.fileLoad.addActionListener(ActionRouter.getInstance());
		}
		return fileLoad;
	}

	public JMenuItem getFileSave() {
		if (this.fileSave == null) {
			this.fileSave = new JMenuItem("Guardar", 'S');
			this.fileSave.setAccelerator(KeyStrokes.SAVE);
			this.fileSave.setActionCommand(ActionNames.SAVE);
			this.fileSave.addActionListener(ActionRouter.getInstance());
		}
		return fileSave;
	}

	public JMenu getHelpMenu() {
		if (this.helpMenu == null){
			this.helpMenu = new JMenu("Help");
		}
		return helpMenu;
	}

}
