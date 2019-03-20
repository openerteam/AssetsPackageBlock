package cn.team.block.assets.core.image.style;


import cn.team.block.assets.core.image.handle.ImageAssetHandleUtils;
import org.apache.commons.lang.StringUtils;

import java.awt.image.BufferedImage;

public class ImageAssetMarkHandleStyle extends ImageAssetHandleStyle {


    private String label;


    public ImageAssetMarkHandleStyle(String parse) {
        super(parse);
        label = parse;
    }


    public BufferedImage handle(BufferedImage origin) {
        if (StringUtils.isEmpty(label)) {
            return origin;
        }
        return ImageAssetHandleUtils.markString(origin, label);
    }
}
