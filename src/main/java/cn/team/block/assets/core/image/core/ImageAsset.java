package cn.team.block.assets.core.image.core;


import cn.team.block.assets.tools.Cipher;
import cn.team.block.assets.tools.PathConfig;
import org.apache.commons.lang.StringUtils;


public class ImageAsset {

    /**
     * 译文，若无法解码则不能继续使用
     */
    private String decipher;
    private String cipher;
    private String path;
    private String name;
    private String suffix;

    public ImageAsset(String cipher, String path) {
        this.path = path;
        this.cipher = cipher;
        this.decipher = Cipher.decode(cipher);
        if (StringUtils.contains(path, ".")) {
            suffix = StringUtils.substring(path, path.lastIndexOf(".") + 1);
        } else {
            suffix = PathConfig.IMAGE_OUT_SUFFIX;
        }
        if (StringUtils.contains(path, "/")) {
            name = StringUtils.substring(path, path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        } else {
            name = StringUtils.substring(path, 0, path.lastIndexOf("."));
        }
    }

    /**
     * 当前译文是否有效
     *
     * @return
     */
    public boolean isValid() {
        if (decipher == null || StringUtils.isEmpty(decipher)) {
            return false;
        }
        return true;
    }

    /**
     * 获得译文
     *
     * @return
     */
    public String getDecipher() {
        return decipher;
    }

    public String getPath() {
        return path;
    }
    public String getSuffix() {
        return suffix;
    }
    public String getName() {
        return name;
    }


    public String getCipher() {
        return cipher;
    }
}
