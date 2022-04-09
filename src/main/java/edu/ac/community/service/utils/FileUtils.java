package edu.ac.community.service.utils;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtils {

    private static final String windowsPath = "E:/community-resources/";

    private static final String macPath = "/Users/thanatos/community-resources/";


    private static final String windowsConfigPath = "file:E:\\sport-resources\\";

    private static final String macConfigPath = "file:/Users/thanatos/sport-resources/";

    public static final String imagePath = "img";

    public static final String avatarPath = "avatar";


    public static String getSystemPath(){
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")){
            return windowsPath;
        }else  if (os.startsWith("Mac")){
            return macPath;
        }else {
            throw new RuntimeException("操作系统不支持");
        }
    }

    public static String getSystemConfigPath(){
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")){
            return windowsConfigPath;
        }else  if (os.startsWith("Mac")){
            return macConfigPath;
        }else {
            throw new RuntimeException("操作系统不支持");
        }
    }

    public static File getDir(FileType type) {
        String path = null;

        if (type == FileType.AVATAR){
            path = avatarPath;
        }else if (type == FileType.IMAGE){
            path = imagePath;
        }else {
            throw new RuntimeException("类型错误");
        }

        File dir = new File(getSystemPath(), path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

}
