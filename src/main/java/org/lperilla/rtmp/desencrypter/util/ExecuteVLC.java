package org.lperilla.rtmp.desencrypter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecuteVLC implements Runnable {

	private static Logger logger = LogManager.getLogger(ExecuteVLC.class);

	private Thread thread;

	private String url;

	public ExecuteVLC(String url) {
		this.thread = new Thread(this);
		this.url = url;
		this.thread.start();
	}

	class StreamGobbler extends Thread {
		private InputStream is;
		
		private boolean discard;

		StreamGobbler(InputStream is, boolean discard) {
			this.is = is;
			this.discard = discard;
		}

		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null)
					if (!discard)
						logger.info(line);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void run() {
		Runtime runtime = Runtime.getRuntime();
		try {
			String vlc = RTMPProperties.getInstance().getProperty("vlc.application", "vlc");
			String[] args = { vlc, "-vvv", url };
			Process proc = runtime.exec(args);
			
			logger.info("Is Alive" + proc.isAlive());

			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), false);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), false);
			errorGobbler.start();
			outputGobbler.start();
			logger.info("\n" + proc.waitFor());
		}
		catch (IOException ioe) {
			logger.error(ioe);
		}
		catch (InterruptedException ie) {
			logger.error(ie);
		}
	}
}