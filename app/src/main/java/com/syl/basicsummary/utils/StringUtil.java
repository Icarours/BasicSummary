package com.syl.basicsummary.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by j3767 on 2017/2/1.
 *
 * @Describe
 * @Called
 */

public class StringUtil {
    /**
     * 字节流转化为字符流
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        try {
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    // BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    while ((line = reader.readLine()) != null) {
                        // sb.append(line);
                        sb.append(line).append("\n");
                    }
                } finally {
                    is.close();
                }
                return sb.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解析字节流
     * @param in
     * @return
     * @throws IOException
     */
    public static String decodeStream(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len = in.read(buf);
        while (len > 0) {
            baos.write(buf, 0, len);
        }

        String value = baos.toString();

        return value;
    }
}
