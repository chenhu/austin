package org.opendaylight.scence.tools;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {
    private static Properties properties;
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    private AppConfig()
    {
        if (properties == null)
        {
            properties = new Properties();
        }
        try
        {
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        }
        catch (IOException e)
        {
            LOG.error("load config.properties file error! the file is not exists!");
            LOG.error(e.getMessage());
        }
    }

    private static class AppConfigHelper
    {
        private static final AppConfig instance = new AppConfig();
    }

    public static AppConfig getInstance()
    {
        return AppConfigHelper.instance;
    }

    protected Object readResolve()
    {
        return getInstance();
    }
    
    public String getUsername () {
        return properties.getProperty("username");
    }
    public String getPassword () {
        return properties.getProperty("password");
    }
}
