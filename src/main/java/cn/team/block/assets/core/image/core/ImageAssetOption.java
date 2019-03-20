package cn.team.block.assets.core.image.core;


import cn.team.block.assets.core.image.style.*;
import cn.team.block.assets.tools.Cipher;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * 目前图片处理方式支持：
 * 1. w 针对宽度缩放
 * 2. h 针对高度缩放
 * 3. r 针对高度和宽度缩放
 * 4. z 针对比例缩放
 * 5. c 针对方式剪切
 * 6. m 针对文字加水印
 */
public class ImageAssetOption {

    static final String STYLE_SPLIT = "&";

    static final String VALUE_SPLIT = "=";

    //缩放方式
    private ArrayList<ImageAssetHandleStyle> styles = new ArrayList<ImageAssetHandleStyle>();


    public ImageAssetOption(String decipher) {
        String[] splits = decipher.split(STYLE_SPLIT);
        for (String split : splits) {
            if (!StringUtils.contains(split, VALUE_SPLIT)) {
                continue;
            }
            ImageAssetHandleStyle style = parseStyle(split);
            if (style == null) {
                continue;
            }
            styles.add(style);
        }
    }


    private ImageAssetHandleStyle parseStyle(String split) {
        String st = split.split(VALUE_SPLIT)[0];
        String va = split.split(VALUE_SPLIT)[1];
        ImageAssetHandleStyle style = null;
        ImageAssetStyle as = ImageAssetStyle.value(st);
        if (as == null) {
            return null;
        }
        switch (as) {
            case w:
                style = new ImageAssetRectangleHandleStyle(0,va);
                break;
            case h:
                style = new ImageAssetRectangleHandleStyle(1,va);
                break;
            case r:
                style = new ImageAssetRectangleHandleStyle(2,va);
                break;
            case c:
                style = new ImageAssetCropHandleStyle(va);
                break;
            case z:
                style = new ImageAssetZoomHandleStyle(va);
                break;
            case m:
                style = new ImageAssetMarkHandleStyle(va);
                break;
            case b:
                style = new ImageAssetBlurHandleStyle(va);
                break;
        }

        return style;
    }


    public ArrayList<ImageAssetHandleStyle> getStyles() {
        return styles;
    }


    /**
     * 图片相关参数组合
     *
     * @return
     */
    public static String combine(Integer width, Integer height, String mark, Float scale, Boolean blur) {
        StringBuffer cipher = new StringBuffer();
        if (width != null && height != null) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.r.name() + VALUE_SPLIT + width + ImageAssetRectangleHandleStyle.SPLIT + height);
        } else {
            if (width != null && width > 0) {
                cipher.append(STYLE_SPLIT + ImageAssetStyle.w.name() + VALUE_SPLIT + width);
            }
            if (height != null && height > 0) {
                cipher.append(STYLE_SPLIT + ImageAssetStyle.h.name() + VALUE_SPLIT + height);
            }
        }

        if (scale != null && scale > 0) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.z.name() + VALUE_SPLIT + scale);
        }

        if (!StringUtils.isEmpty(mark)) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.m.name() + VALUE_SPLIT + mark);
        }
        if (blur != null) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.b.name() + VALUE_SPLIT +(blur?1:0));
        }

        if (cipher.length() > 0) {
            return Cipher.encode(cipher.substring(1));
        }
        return Cipher.encode(cipher.toString());
    }

    /**
     * 图片相关参数组合
     *
     * @return
     */
    public static String combineCrop(Integer width, Integer height, String mark) {
        StringBuffer cipher = new StringBuffer();
        if (width != null && height != null) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.c.name() + VALUE_SPLIT + width + ImageAssetRectangleHandleStyle.SPLIT + height);
        }

        if (!StringUtils.isEmpty(mark)) {
            cipher.append(STYLE_SPLIT + ImageAssetStyle.m.name() + VALUE_SPLIT + width);
        }

        if (cipher.length() > 0) {
            return Cipher.encode(cipher.substring(1));
        }
        return Cipher.encode(cipher.toString());
    }
}
