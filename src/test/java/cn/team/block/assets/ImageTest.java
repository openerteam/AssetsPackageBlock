package cn.team.block.assets;

import cn.team.block.assets.core.image.core.ImageAsset;
import cn.team.block.assets.core.image.core.ImageAssetEffect;
import cn.team.block.assets.core.image.core.ImageAssetOption;
import cn.team.block.assets.tools.Cipher;
import org.junit.Test;

import java.io.File;


public class ImageTest {

    @Test
    public void test() {
        String cipher = ImageAssetOption.combine(400, null, null, null, true);
        ImageAsset imageAsset = new ImageAsset(cipher, "test.jpg");
        ImageAssetEffect effect = new ImageAssetEffect(imageAsset);
        effect.handle().createBytes();
//        File file = effect.handle().createBytes();
//        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void cipherTest() {
        String cipher = ImageAssetOption.combine(400, null, null, null, true);

        System.out.println(cipher);
        System.out.println(Cipher.decode(cipher));
    }

    @Test
    public void str2() {
        String cipher = ImageAssetOption.combine(200, 1000, "hello", 0.5f,false);

        System.out.println(cipher);
        System.out.println(Cipher.decode(cipher));


    }

}
