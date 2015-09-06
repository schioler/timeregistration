package dk.bitmovers.timeregistration.client.gui.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Util {

	private Util() {
		
	}

	public static Locale establishLocale(String language, String country) {
		Locale retVal = null;
		if (StringUtils.isNotBlank(language)) {
			if (StringUtils.isNotBlank(country)) {
				retVal = new Locale(language, country);
			} else {
				retVal = new Locale(language);
			}
		} else {
			retVal = Locale.getDefault();
		}
		return retVal;
	}

}
