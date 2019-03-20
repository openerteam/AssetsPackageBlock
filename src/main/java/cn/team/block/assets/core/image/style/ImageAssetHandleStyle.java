package cn.team.block.assets.core.image.style;

import java.awt.image.BufferedImage;

public abstract class ImageAssetHandleStyle {

    public ImageAssetHandleStyle(String parse) {
    }

    public Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Float parseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract BufferedImage handle(BufferedImage origin);

}
