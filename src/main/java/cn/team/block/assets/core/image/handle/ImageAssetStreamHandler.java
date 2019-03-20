package cn.team.block.assets.core.image.handle;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2016/12/16.
 */
public class ImageAssetStreamHandler {


    /**
     * 这个私有方法用于在磁盘上查询原始文件
     * */
    public static byte[] queryOriginalPicture(File imFile) throws IOException{
        if (imFile == null){
            return null;
        }
        // 如果不存在这个文件，就不需要处理咯
        // 生产环境下要显示一张默认的404图片
        if(!imFile.exists()) {
            return null;
        }
        InputStream in = new FileInputStream(imFile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 8192;
        int realLen;
        byte[] contents = new byte[8192];
        while((realLen = in.read(contents, 0, len)) != -1) {
            out.write(contents, 0, realLen);
        }
        in.close();
        byte[] imageBytes = out.toByteArray();
        out.close();

        return imageBytes;
    }


    /**
     * 输出图片流到response
     * @param response HttpServletResponse
     * @param result 写入的byte信息
     */
    public static void writeStreamResponse(HttpServletResponse response, byte[] result) {
        response.setContentType("image/png;charset=utf-8");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

}
