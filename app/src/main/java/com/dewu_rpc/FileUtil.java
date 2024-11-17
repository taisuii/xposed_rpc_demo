package com.dewu_rpc;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static String readString(String path) {
        return new String(readBytes(path));
    }

    public static byte[] readBytes(String path) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1048576];
        try {
            try {
                fileInputStream = new FileInputStream(path);
                while (true) {
                    int len = fileInputStream.read(bytes);
                    if (len == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bytes, 0, len);
                }
                fileInputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeString(String path, String content) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(content.getBytes());
                fileOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            close(fileOutputStream);
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }
}