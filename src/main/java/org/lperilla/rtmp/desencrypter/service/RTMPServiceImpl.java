package org.lperilla.rtmp.desencrypter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.text.BasicTextEncryptor;
import org.lperilla.rtmp.desencrypter.bean.ChannelBean;

public class RTMPServiceImpl {

	private static Logger logger = LogManager.getLogger(RTMPServiceImpl.class);

	private static final String START_CHANNEL = "@ channel";

	private static final String END_CHANNEL = "@ /channel";

	private static final String PATTERN_NAME = "name \"(.+?)\"";

	private static final String PATTERN_AVAILABLE = "available \"(.+?)\"";

	private static final String PATTERN_URL = "url \"(.+?)\"";

	private static final String PATTERN_URL_IMAGE = "image \"(.+?)\"";

	private static final String PATTERN_PROGRAMACION = "url_programacion \"(.+?)\"";

	private static final String PATTERN_REFERER = "referer \"(.+?)\"";

	private static final String PATTERN_IS_HOST = "isHost \"(.+?)\"";

	private static final String PATTERN_QUALITY = "quality \"(.+?)\"";

	private static final String PATTERN_VERSION = "version \"(.+?)\"";

	private static Map<Integer, String> PATTERNS = new HashMap<Integer, String>();

	private static BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

	static {
		PATTERNS.put(1, PATTERN_NAME);
		PATTERNS.put(2, PATTERN_AVAILABLE);
		PATTERNS.put(3, PATTERN_URL);
		PATTERNS.put(4, PATTERN_URL_IMAGE);
		PATTERNS.put(5, PATTERN_PROGRAMACION);
		PATTERNS.put(6, PATTERN_REFERER);
		PATTERNS.put(7, PATTERN_IS_HOST);
		PATTERNS.put(8, PATTERN_QUALITY);
		PATTERNS.put(9, PATTERN_VERSION);
		basicTextEncryptor.setPassword("c6ka74t4b2dv");
	}

	public List<ChannelBean> loadChannelFile(File channelFile) throws Exception {
		List<ChannelBean> channels = null;
		if (channelFile != null && channelFile.exists()) {
			FileInputStream fileInputStream = null;
			BufferedReader bufferedReader = null;
			try {
				fileInputStream = new FileInputStream(channelFile);
				bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

				String strLine;

				ChannelBean channelBean = null;
				channels = new LinkedList<ChannelBean>();
				while ((strLine = bufferedReader.readLine()) != null) {
					logger.debug(strLine);
					if (StringUtils.isNotBlank(strLine) && strLine.equalsIgnoreCase(START_CHANNEL)) {
						channelBean = new ChannelBean();
					} else if (channelBean != null && StringUtils.isNotBlank(strLine) && strLine.equalsIgnoreCase(END_CHANNEL)) {
						channels.add(channelBean);
					} else {
						this.loadAtributtes(channelBean, strLine);
					}
				}
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				new Exception(e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				new Exception(e);
			} finally {
				try {
					if (bufferedReader != null)
						bufferedReader.close();

					if (fileInputStream != null)
						fileInputStream.close();

				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}

		}
		return channels;
	}

	private void loadAtributtes(ChannelBean bean, String strLine) {
		if (bean != null && StringUtils.isNotBlank(strLine)) {
			Set<Entry<Integer, String>> values = PATTERNS.entrySet();
			for (Entry<Integer, String> entry : values) {
				Pattern pattern = Pattern.compile(entry.getValue());
				Matcher matcher = pattern.matcher(strLine);
				if (matcher.find(1)) {
					setValuesChannelBean(bean, entry.getKey(), matcher.group(1));
					break;
				}
			}
		}
	}

	private static void setValuesChannelBean(ChannelBean bean, int key, String value) {
		if (bean != null && StringUtils.isNotBlank(value)) {
			switch (key) {
			case 1:
				bean.setName(value);
				break;
			case 2:
				bean.setAvailable(value);
				break;
			case 3:
				bean.setUrl(basicTextEncryptor.decrypt(value));
				break;
			case 4:
				bean.setUrlImage(basicTextEncryptor.decrypt(value));
				break;
			case 5:
				bean.setUrlProgramming(basicTextEncryptor.decrypt(value));
				break;
			case 6:
				bean.setReferer(basicTextEncryptor.decrypt(value));
				break;
			case 7:
				bean.setHost(value);
				break;
			case 8:
				bean.setQuality(value);
				break;
			case 9:
				bean.setVersion(value);
				break;
			default:
				break;
			}
		}
	}

}
