package org.lperilla.rtmp.desencrypter.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.StringUtils;
import org.lperilla.rtmp.desencrypter.gui.GuiPackage;

/**
 * Class implementing a file open dialogue
 */
public final class FileDialoger {
	/**
	 * The last directory visited by the user while choosing Files.
	 */
	private static String lastJFCDirectory = null;

	private static JFileChooser fileChooser = new JFileChooser();

	/**
	 * Prevent instantiation of utility class.
	 */
	private FileDialoger() {
	}

	/**
	 * Prompts the user to choose a file from their filesystems for our own
	 * devious uses. This method maintains the last directory the user visited
	 * before dismissing the dialog. This does NOT imply they actually chose a
	 * file from that directory, only that they closed the dialog there. It is
	 * the caller's responsibility to check to see if the selected file is
	 * non-null.
	 *
	 * @return the JFileChooser that interacted with the user, after they are
	 *         finished using it - null if no file was chosen
	 */
	public static JFileChooser promptToOpenFile() {
		return promptToOpenFile((String) null);
	}

	/**
	 * Prompts the user to choose a file from their filesystems for our own
	 * devious uses. This method maintains the last directory the user visited
	 * before dismissing the dialog. This does NOT imply they actually chose a
	 * file from that directory, only that they closed the dialog there. It is
	 * the caller's responsibility to check to see if the selected file is
	 * non-null.
	 * 
	 * @param existingFileName
	 *            The name of a file with path. If the filename points to an
	 *            existing file, the directory in which it lies, will be used as
	 *            the starting point for the returned JFileChooser.
	 *
	 * @return the JFileChooser that interacted with the user, after they are
	 *         finished using it - null if no file was chosen
	 */
	public static JFileChooser promptToOpenFile(String existingFileName) {
		return promptToOpenFile(new String[0], existingFileName);
	}

	/**
	 * Prompts the user to choose a file from their filesystems for our own
	 * devious uses. This method maintains the last directory the user visited
	 * before dismissing the dialog. This does NOT imply they actually chose a
	 * file from that directory, only that they closed the dialog there. It is
	 * the caller's responsibility to check to see if the selected file is
	 * non-null.
	 * 
	 * @param exts
	 *            The list of allowed file extensions. If empty, any file
	 *            extension is allowed
	 *
	 * @return the JFileChooser that interacted with the user, after they are
	 *         finished using it - null if no file was chosen
	 */
	public static JFileChooser promptToOpenFile(String[] exts) {
		return promptToOpenFile(exts, null);
	}

	/**
	 * Prompts the user to choose a file from their filesystems for our own
	 * devious uses. This method maintains the last directory the user visited
	 * before dismissing the dialog. This does NOT imply they actually chose a
	 * file from that directory, only that they closed the dialog there. It is
	 * the caller's responsibility to check to see if the selected file is
	 * non-null.
	 * 
	 * @param exts
	 *            The list of allowed file extensions. If empty, any file
	 *            extension is allowed
	 * @param existingFileName
	 *            The name of a file with path. If the filename points to an
	 *            existing file, the directory in which it lies, will be used as
	 *            the starting point for the returned JFileChooser.
	 *
	 * @return the JFileChooser that interacted with the user, after they are
	 *         finished using it - null if no file was chosen
	 */
	public static JFileChooser promptToOpenFile(String[] exts, String existingFileName) {
		if (!StringUtils.isEmpty(existingFileName)) {
			File existingFileStart = new File(existingFileName);
			if (existingFileStart.exists() && existingFileStart.canRead()) {
				fileChooser.setCurrentDirectory(new File(existingFileName));
			}
		} else if (lastJFCDirectory == null) {
			String start = System.getProperty("user.dir", ""); //$NON-NLS-1$//$NON-NLS-2$

			if (start.length() > 0) {
				fileChooser.setCurrentDirectory(new File(start));
			}
		}
		clearFileFilters();
		if (exts != null && exts.length > 0) {
			RTMPFileFilter currentFilter = new RTMPFileFilter(exts);
			fileChooser.addChoosableFileFilter(currentFilter);
			fileChooser.setAcceptAllFileFilterUsed(true);
			fileChooser.setFileFilter(currentFilter);
		}
		if (lastJFCDirectory == null) {
			lastJFCDirectory = System.getProperty("user.dir", ""); //$NON-NLS-1$//$NON-NLS-2$
		}
		fileChooser.setCurrentDirectory(new File(lastJFCDirectory));
		int retVal = fileChooser.showOpenDialog(GuiPackage.getInstance().getMainFrame());
		lastJFCDirectory = fileChooser.getCurrentDirectory().getAbsolutePath();

		if (retVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser;
		}
		return null;
	}

	private static void clearFileFilters() {
		FileFilter[] filters = fileChooser.getChoosableFileFilters();
		for (FileFilter filter : filters) {
			fileChooser.removeChoosableFileFilter(filter);
		}
	}

	/**
	 * Prompts the user to choose a file from their filesystems for our own
	 * devious uses. This method maintains the last directory the user visited
	 * before dismissing the dialog. This does NOT imply they actually chose a
	 * file from that directory, only that they closed the dialog there. It is
	 * the caller's responsibility to check to see if the selected file is
	 * non-null.
	 * 
	 * @param filename
	 *            The name of a file with path. If the filename points to an
	 *            existing file, the directory in which it lies, will be used as
	 *            the starting point for the returned JFileChooser.
	 *
	 * @return the JFileChooser that interacted with the user, after they are
	 *         finished using it - null if no file was chosen
	 * @see #promptToOpenFile()
	 */
	public static JFileChooser promptToSaveFile(String filename) {
		return promptToSaveFile(filename, null);
	}

	/**
	 * Get a JFileChooser with a new FileFilter.
	 *
	 * @param filename
	 *            file name
	 * @param extensions
	 *            list of extensions
	 * @return the FileChooser - null if no file was chosen
	 */
	public static JFileChooser promptToSaveFile(String filename, String[] extensions) {
		if (lastJFCDirectory == null) {
			String start = System.getProperty("user.dir", "");//$NON-NLS-1$//$NON-NLS-2$
			if (start.length() > 0) {
				fileChooser = new JFileChooser(new File(start));
			}
			lastJFCDirectory = fileChooser.getCurrentDirectory().getAbsolutePath();
		}
		String ext = ".jmx";//$NON-NLS-1$
		if (filename != null) {
			fileChooser.setDialogTitle(filename);
			fileChooser.setSelectedFile(filename.lastIndexOf(File.separator) > 0 ? new File(filename) : new File(lastJFCDirectory, filename));
			int i = -1;
			if ((i = filename.lastIndexOf('.')) > -1) {//$NON-NLS-1$
				ext = filename.substring(i);
			}
		}
		clearFileFilters();
		if (extensions != null) {
			fileChooser.addChoosableFileFilter(new RTMPFileFilter(extensions));
		} else {
			fileChooser.addChoosableFileFilter(new RTMPFileFilter(new String[] { ext }));
		}

		int retVal = fileChooser.showSaveDialog(GuiPackage.getInstance().getMainFrame());
		fileChooser.setDialogTitle(null);
		lastJFCDirectory = fileChooser.getCurrentDirectory().getAbsolutePath();
		if (retVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser;
		}
		return null;
	}

	/**
	 * 
	 * @return The last directory visited by the user while choosing Files
	 */
	public static String getLastJFCDirectory() {
		return lastJFCDirectory;
	}

	/**
	 * 
	 * @param lastJFCDirectory
	 *            The last directory visited by the user while choosing Files
	 */
	public static void setLastJFCDirectory(String lastJFCDirectory) {
		FileDialoger.lastJFCDirectory = lastJFCDirectory;
	}
}
