package cn.team.block.assets.tools;

import cn.team.block.assets.core.image.core.ImageAssetEffect;

import java.io.*;
import java.util.Properties;

public class PathConfig {

    private static PathConfig config;

    private String cacheDir;
    private String uploadDir;
    private Properties properties;

    public static final String IMAGE_OUT_SUFFIX = "png";


    public PathConfig() {
        String path = ImageAssetEffect.class.getClassLoader().getResource("application.properties").getPath();
        System.out.println("path:" + path);
        InputStream in = null;
        properties = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            properties.load(in);

            cacheDir = properties.getProperty("asset.image.cacheDir");
            uploadDir = properties.getProperty("asset.image.uploadDir");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }


    public static synchronized PathConfig getConfig() {
        if (config == null) {

            config = new PathConfig();
        }
        return config;
    }

    public String getCacheDir() {
        return cacheDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }
}
