package cn.team.block.assets.core.image.style;

import cn.team.block.assets.core.image.handle.ImageAssetHandleUtils;

import java.awt.image.BufferedImage;

public class ImageAssetZoomHandleStyle extends ImageAssetHandleStyle {

    private Float scale;


    public ImageAssetZoomHandleStyle(String parse) {
        super(parse);
        scale = parseFloat(parse);
    }


    public BufferedImage handle(BufferedImage origin) {
        if (scale == null){
            return origin;
        }
        //处理缩放操作
        return ImageAssetHandleUtils.scale(origin, scale);
    }
}
