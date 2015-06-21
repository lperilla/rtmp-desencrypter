package org.lperilla.rtmp.desencrypter.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {

	private static Logger logger = LogManager.getLogger(Util.class);

	public static ImageIcon getLogoChannel(String urlImage) {
		ImageIcon logoChannel = new ImageIcon(ClassLoader.getSystemResource("images/television.png"));

		URL url = null;
		InputStream input = null;
		if (StringUtils.isNotBlank(urlImage)) {
			try {
				url = new URL(urlImage);
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				HttpURLConnection.setFollowRedirects(false);
				huc.setConnectTimeout(15 * 1000);
				huc.setRequestMethod("GET");
				huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
				huc.connect();
				input = huc.getInputStream();

			} catch (MalformedURLException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		Image image = null;

		if (input != null) {
			try {
				image = ImageIO.read(input);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		if (image != null) {
			image = image.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
			logoChannel = new ImageIcon(image);
		}

		return logoChannel;
	}

}
