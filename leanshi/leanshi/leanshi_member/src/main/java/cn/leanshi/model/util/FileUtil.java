package cn.leanshi.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUtil {

    /**
     * 返回uuid命名的文件名称
     *
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return prefix + suffix;
    }

    /**
     * 转换file类型
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
