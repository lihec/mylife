package org.tools.life.support;


import java.util.Properties;

public final class CommonConstants {

    private CommonConstants(){}
    
    private static Properties configProperties;

    public static void loadConfigProperties(final Properties props) {
        configProperties = props;
    }

    public static String getProperty(final String key) {
        return CommonConstants.configProperties.getProperty(key);
    }
    
    public static Properties getProperties() {
		return configProperties;
	}
}
