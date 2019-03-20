package cn.team.block.assets.core.image.style;

import cn.team.block.assets.core.image.handle.ImageAssetHandleUtils;
import org.apache.commons.lang.StringUtils;

import java.awt.image.BufferedImage;

public class ImageAssetRectangleHandleStyle extends ImageAssetHandleStyle {

    public  static final String SPLIT = "x";

    private Integer w;

    private Integer h;

    private int style = 0;

    public ImageAssetRectangleHandleStyle(int style, String parse) {
        super(parse);
        this.style = style;
        if (style == 0){
            w = parseInteger(parse);
        } else if (style == 1){
            h = parseInteger(parse);
        } else {
            if (StringUtils.contains(parse, SPLIT)){
                String[] splits = parse.split(SPLIT);
                if (splits.length > 0){
                    w =  parseInteger(splits[0]);
                }
                if (splits.length > 1){
                    h = parseInteger(splits[1]);
                }
            }
        }
    }


    public BufferedImage handle(BufferedImage origin) {
        BufferedImage destImage = null;
        // 如果条件成立，说明是按照比例裁剪
        // 如果发现输入的目标高宽大于图片的原始高宽，则按照ratio==1处理
        if (w != null && h != null) {
            // 按照输入的宽、高进行裁剪
            float wratio = (w.intValue() * 1.0f / origin.getWidth());
            float hratio = (h.intValue() * 1.0f / origin.getHeight());
            if (wratio > hratio) {
                destImage = ImageAssetHandleUtils.scale(origin, wratio);
            } else {
                destImage = ImageAssetHandleUtils.scale(origin, hratio);
            }
        } else if (w != null) {
            destImage = ImageAssetHandleUtils.handleW(origin, w);
        } else if (h != null) {
            destImage = ImageAssetHandleUtils.handleH(origin, h);
        }
        return destImage;
    }
}
