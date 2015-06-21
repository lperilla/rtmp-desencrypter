package org.lperilla.rtmp.desencrypter.gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public final class KeyStrokes {

	private KeyStrokes() {
	}

	private static final int CONTROL_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

	public static final KeyStroke COPY = KeyStroke.getKeyStroke(KeyEvent.VK_C, CONTROL_MASK);

	public static final KeyStroke ESC = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

	public static final KeyStroke HELP = KeyStroke.getKeyStroke(KeyEvent.VK_H, CONTROL_MASK);

	public static final KeyStroke OPEN = KeyStroke.getKeyStroke(KeyEvent.VK_O, CONTROL_MASK);

	//public static final KeyStroke EXIT = KeyStroke.getKeyStroke(KeyEvent.VK_Q, CONTROL_MASK);

	public static final KeyStroke SAVE = KeyStroke.getKeyStroke(KeyEvent.VK_S, CONTROL_MASK);

	public static boolean matches(KeyEvent e, KeyStroke k) {
		final int modifiersEx = e.getModifiersEx() | e.getModifiers();
		return e.getKeyCode() == k.getKeyCode() && modifiersEx == k.getModifiers();
	}
}
