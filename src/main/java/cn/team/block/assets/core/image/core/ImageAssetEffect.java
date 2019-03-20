package cn.team.block.assets.core.image.core;


import cn.team.block.assets.core.image.handle.*;
import cn.team.block.assets.core.image.style.ImageAssetHandleStyle;
import cn.team.block.assets.tools.Cipher;
import cn.team.block.assets.tools.PathConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 图片效果
 */
public class ImageAssetEffect {

    /**
     * 需要处理的图片
     */
    private ImageAsset imageAsset;

    private ImageAssetOption option;

    private BufferedImage outBufferedImage;

    private BufferedImage bufferedImage;

    public ImageAssetEffect(ImageAsset imageAsset) {
        this.imageAsset = imageAsset;
        option = new ImageAssetOption(imageAsset.getDecipher());
    }


    /**
     * 初始化图片实体
     */
    private void initBufferedImage() {
        try {
            File imFile = new File(PathConfig.getConfig().getUploadDir(), imageAsset.getPath());
            if (!imFile.exists()) {
                return;
            }
            byte[] fileBytes = ImageAssetStreamHandler.queryOriginalPicture(imFile);
            if (fileBytes == null) {
                return;
            }
            bufferedImage = ImageIO.read(new ByteArrayInputStream(fileBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行效果
     * 1. 若执行效果中存在缩放，则忽略宽高缩放条件
     * 2. 若执行中无缩放条件，则采用宽高进行裁剪
     * 3. 若存在水印文字，则加上水印效果
     */
    public ImageAssetEffect handle() {
        File file = new File(PathConfig.getConfig().getCacheDir(), imageAsset.getName()+ "_"+imageAsset.getCipher() + "." + imageAsset.getSuffix());
        if (file.exists()){
            return this;
        }
        initBufferedImage();
        if (bufferedImage == null) {
            return null;
        }
        outBufferedImage = bufferedImage;
        List<ImageAssetHandleStyle> styles = option.getStyles();
        if (styles.size() == 0) {
            return this;
        }

        for (ImageAssetHandleStyle style : styles) {
            outBufferedImage = style.handle(outBufferedImage);
        }

        return this;
    }


    /**
     * 图片生成并返回图片对象
     *
     * @return
     */
    public File createFile() {
        File file = new File(PathConfig.getConfig().getCacheDir(), imageAsset.getName()+ "_"+imageAsset.getCipher() + "." + imageAsset.getSuffix());
        if (file.exists()) {
            return file;
        }
        if (outBufferedImage == null) {
            System.out.println("创建图片失败");
            return null;
        }
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream out = new FileOutputStream(file);
            javax.imageio.ImageIO.write(outBufferedImage, imageAsset.getSuffix(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //存在的情况
        return file;
    }

    /**
     * 图片生成并返回bytes
     *
     * @return
     */
    public byte[] createBytes() {
        File file = createFile();
        //存在的情况
        try {
            return ImageAssetStreamHandler.queryOriginalPicture(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
