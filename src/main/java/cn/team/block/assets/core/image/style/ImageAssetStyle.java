package cn.team.block.assets.core.image.style;

import org.apache.commons.lang.StringUtils;

/**
 * Created by chenli on 2018/6/21 0021.
 */
public enum ImageAssetStyle {
    /**
     * 原始
     */
    w,
    /**
     * 缩放
     */
    h,
    r,
    z,
    /**
     * 裁剪
     */
    c,
    /**
     * 标记/水印
     */
    m,
    b;

    public static ImageAssetStyle value(String v) {
        ImageAssetStyle[] values = values();
        for (ImageAssetStyle style : values) {
            if (StringUtils.contains(style.name(), v)) {
                return style;
            }
        }
        return null;
    }


}
