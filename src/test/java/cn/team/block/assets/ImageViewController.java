package cn.team.block.assets;//
//import cn.integration.core.image.core.Image;
//import cn.integration.core.image.core.ImageEffect;
//import cn.integration.core.image.handle.ImageStreamHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 这个测试图片特效显示效果
// * @author cn
// */
//@RestController
//public class ImageViewController {
//
//
//    @RequestMapping(path = {"/{cipher}"} , method = RequestMethod.GET)
//    public void imageQuery(HttpServletResponse response , @PathVariable("cipher") String cipher) throws Exception {
//        Image image = new Image(cipher);
//        ImageEffect effect = new ImageEffect(image);
//        ImageStreamHandler.writeStreamResponse(response , effect.getBytes());
//    }
//
//}