package cn.team.block.assets.core.image.style;

import cn.team.block.assets.core.image.handle.ImageAssetHandleUtils;
import org.apache.commons.lang.StringUtils;

import java.awt.image.BufferedImage;

public class ImageAssetCropHandleStyle extends ImageAssetHandleStyle {

    static final String SPLIT = "x";

    private Integer w;

    private Integer h;


    public ImageAssetCropHandleStyle(String parse) {
        super(parse);
        if (!StringUtils.contains(parse, SPLIT)) {
            return;
        }
        String[] splits = parse.split(SPLIT);
        w = parseInteger(splits[0]);
        h = parseInteger(splits[1]);
        h = parseInteger(splits[2]);
    }


    public BufferedImage handle(BufferedImage origin) {
        return origin;
    }
}
