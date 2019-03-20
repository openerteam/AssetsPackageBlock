package cn.team.block.assets.core.image.handle;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片处理工具
 */
public class ImageAssetHandleUtils {


    /**
     * 添加水印文字
     *
     * @param origin
     * @param text
     * @return
     */
    public static BufferedImage markString(BufferedImage origin, String text) {
        // 这是画笔，由于不需要进行图片大小、宽高的改变。这里直接在原来的BufferedImage上绘画就行了
        BufferedImage destImage = new BufferedImage(origin.getWidth(), origin.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = destImage.getGraphics();
        // 得到画笔，准备在原图片上添加水印
        // 处理得相对简单一些，正常生产环境下，还可以设置透明图，位置等等
        graphics.drawImage(origin, 0, 0, null);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        graphics.drawString(text, destImage.getWidth() / 2, destImage.getHeight() / 2);
        // 开始处理
        graphics.dispose();
        return destImage;
    }

    /**
     * 缩放图像根据比例
     *
     * @param origin
     * @param ratio
     * @return
     */
    public static BufferedImage scale(BufferedImage origin, float ratio) {
        //根据缩放比例计算实际处理图片大小
        int width = (int) (origin.getWidth() * ratio);
        int height = (int) (origin.getHeight() * ratio);
        Image image = origin.getScaledInstance(width, height, Image.SCALE_FAST);
        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = destImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return destImage;
    }

    /**
     * 按照图片中心模式进行裁剪
     *
     * @param localDestWith
     * @param localDestHeight
     * @param origin
     * @return
     */
    public static BufferedImage cut(int localDestWith, int localDestHeight, BufferedImage origin) {
        BufferedImage destImage = origin.getSubimage((origin.getWidth() - localDestWith) / 2, (origin.getHeight() - localDestHeight) / 2, localDestWith, localDestHeight);
        return destImage;
    }

    /**
     * 适配型-根据宽度适配比例进行缩放高度
     *
     * @param origin
     * @return
     */
    public static BufferedImage handleW(BufferedImage origin, int destWidth) {
        float ratio = (destWidth * 1.0f / origin.getWidth());
        BufferedImage temp = scale(origin, ratio);

        int localDestWith = destWidth;
        int localDestHeight = (int) (origin.getHeight() * ratio);
        // 按照宽或高等比例裁剪
        BufferedImage destImage = cut(localDestWith, localDestHeight, temp);
        return destImage;
    }

    /**
     * 适配型-根据高度适配比例进行缩放宽度
     *
     * @param origin
     * @return
     */
    public static BufferedImage handleH(BufferedImage origin, int destHeight) {
        float ratio = destHeight * 1.0f / origin.getHeight();
        BufferedImage temp = scale(origin, ratio);

        int localDestHeight = destHeight;
        int localDestWith = (int) (origin.getWidth() * ratio);
        BufferedImage destImage = cut(localDestWith, localDestHeight, temp);
        return destImage;
    }
}
