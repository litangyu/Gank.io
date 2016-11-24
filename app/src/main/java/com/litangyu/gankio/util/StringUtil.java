/*
 * 描述
 * Created by: litangyu
 * 包含类名列表
 * 版本信息，版本号 v1.0
 * 创建日期 16/9/19
 * 版权声明
*/
package com.litangyu.gankio.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 描述
 * 字符串操作工具包
 * Created by: litangyu<br/>
 * Created on: 16/9/19 上午10:13
 */
public class StringUtil {

    /**
     * 高亮字符串中的一部分
     *
     * @param str      要处理的字符串：把需要高亮的字符用{}包括起来
     * @param inColorResId  高亮字符的颜色的资源ID
     * @param outColorResId 正常字符颜色的资源ID
     * @return
     */
    public static CharSequence highLight(String str, int inColorResId, int outColorResId) {
        return ColorPhrase.from(str).withSeparator("{}").innerColor(ResourceUtil.getColor(inColorResId))
                .outerColor(ResourceUtil.getColor(outColorResId)).format();
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null
     * 或空字符串，返回true
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str))
            return true;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串转整数
     *
     * @param str      字符串
     * @param defValue 缺省值
     * @return 转换成功或默认值
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 对象转整数
     *
     * @param obj 对象
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 字符串转长整型
     *
     * @param str 字符串
     * @return 转换异常返回 0
     */
    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 字符串转double
     *
     * @param str 字符串
     * @return 异常返回0.0D
     */
    public static double toDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0D;
        }
    }

    /**
     * 字符串转布尔值
     *
     * @param str 字符串
     * @return 转换异常返回 false
     */
    public static boolean toBool(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param inputStream 输入流
     * @return 字符串
     */
    public static String toConvertString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line + "<br>");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                inputStreamReader.close();

                bufferedReader.close();

                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /***
     * 截取字符串
     *
     * @param start 从那里开始，0算起
     * @param num   截取多少个
     * @param str   截取的字符串
     * @return 字符串
     */
    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > length) {
            start = length;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > length) {
            end = length;
        }
        return str.substring(start, end);
    }

    /**
     * 返回文件的扩展名
     * @param path 路径
     * @return 扩展名
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(".");
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     * byte[] 转换成Hex
     *
     * @param bytes 字节数组
     * @return hex
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        byte[] bytes1 = bytes;
        int var = bytes.length;
        for (int i = 0; i < var; ++i) {
            byte b = bytes1[i];
            int v = b & 255;
            if (v < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(v));
        }
        return stringBuilder.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * hex 转换 byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexStringToByteArray(String str) {
        int len = str.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            b[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str
                    .charAt(i + 1), 16));
        }
        return b;
    }

    /**
     * 字符串是不是数字
     *
     * @param charSequence
     * @return
     */
    public static boolean isNumber(CharSequence charSequence) {
        try {
            Integer.parseInt(charSequence.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 过滤字符串中的特殊字符
     *
     * @param str
     * @param filter
     * @return
     */
    public static String stringFilter(String str, String filter) {
        String filterStr = null;
        Matcher m;
        try {
            Pattern p = Pattern.compile(filter);
            m = p.matcher(str);
            filterStr = m.replaceAll("");
        } catch (PatternSyntaxException e) {

        }
        return filterStr;
    }

    /**
     * 格式化内存空间大小，不保留末尾的0
     *
     * @param len
     * @return
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化内存空间大小，保留末尾的0，达到长度一致
     *
     * @param len
     * @param keepZero
     * @return
     */
    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float)
                        100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float)
                        10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    /**
     * 返回一个高亮的spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     *
     * @param parameters 参数组
     * @return boolean
     */
    public static boolean equals(String... parameters) {
        String last = null;
        for (int i = 0; i < parameters.length; i++) {
            String str = parameters[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 获取UUID
     *
     * @return 32UUID小写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }

    /**
     * 将流转成字符串
     *
     * @param is 输入流
     * @return
     * @throws Exception
     */
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 将文件转成字符串
     *
     * @param file 文件
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(File file) throws Exception {
        FileInputStream fin = new FileInputStream(file);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

}