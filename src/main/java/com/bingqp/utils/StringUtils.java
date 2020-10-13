package com.bingqp.utils;

/*
 * Copyright 2005 Shanghai CJTech Co. Ltd.
 * All right reserved.
 * Created on 2005-6-20
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 奥萨
 */
public class StringUtils {

    /**
     * url数据转map
     *
     * @param paramStr
     * @return
     */
    public static Map<String, String> paramToMap(String paramStr) {
        Map<String, String> resMap = stringToMap(paramStr, "&", "=");
        return resMap;
    }

    /**
     * string数据转list
     *
     * @param paramStr
     * @return
     */
    public static List paramToList(String paramStr, String symbol) {
        if (paramStr == null) return null;
        return Arrays.asList(paramToArrays(paramStr, symbol));
    }

    /**
     * string数据转arrays
     *
     * @param paramStr
     * @return
     */
    public static String[] paramToArrays(String paramStr, String symbol) {
        String[] arr = paramStr.split(symbol);
        return arr;
    }

    /**
     * 字符串分隔为Map
     *
     * @param paramStr
     * @param splitStr
     * @param paraStr
     * @return
     */
    public static Map<String, String> stringToMap(String paramStr, String splitStr, String paraStr) {
        String[] params = paramStr.split(splitStr);
        Map<String, String> resMap = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split(paraStr);
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }


    /**
     * 替换函数
     */
    public static void replaceAll() {
        String odpsPartition = "ds=${yesterday},type=${twohourbefore}";
        odpsPartition = odpsPartition.replaceAll("\\$\\{today\\}", DateUtils.getOneDateString(0, "yyyyMMdd"));
        System.out.println(odpsPartition);
    }

    public static final String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }


    /**
     * @return 获得优惠
     */
    public static String getFavour(String s) {
        if (s == null || s.equals("")) return "";
        StringTokenizer st = new StringTokenizer(s, "$");
        int cnt = 0;
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken().trim();
            int i = tmp.indexOf(",");
            if (i != -1) {
                try {
                    cnt = cnt + Integer.parseInt(tmp.substring(i + 1));
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return String.valueOf(cnt);
    }

    /**
     * 将首写字母大写
     *
     * @param str
     * @return
     */
    public static String getFirstUpper(String str) {
        String tmp = "";
        str = trim(str);
        if (!str.equals("")) {
            if (str.length() > 1)
                tmp = toUpperCase(str.substring(0, 1)) + str.substring(1, str.length());
            else
                tmp = toUpperCase(str);
        }

        return tmp;
    }

    /**
     * 将首写字母小写
     *
     * @param str
     * @return
     */
    public static String getFirstLower(String str) {
        String tmp = "";
        str = trim(str);
        if (!str.equals("")) {
            if (str.length() > 1)
                tmp = toLowerCase(str.substring(0, 1)) + str.substring(1, str.length());
            else
                tmp = toLowerCase(str);
        }

        return tmp;
    }

    /**
     * 转换成大写
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        return trim(str).toUpperCase();
    }

    /**
     * 转换成小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        return trim(str).toLowerCase();
    }

    public static String trim(String s) {
        return getNotNullString(s);
    }

    /*
     * 若String为null或"",则转换为{}
     */
    public static String blankToBracket(String str) {
        if (str == null || str.equals(""))
            return "{}";
        return str;
    }

    /*
     * 若String为null或"",则转换为{}
     */
    public static String blankToZero(String str) {
        if (str == null || str.equals(""))
            return "0";
        return str;
    }

    /**
     * 获得一个前边补n个var的src字段
     *
     * @param src
     * @param n
     * @param var
     * @return
     */
    public static String getTrimString(String src, int n, String var) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(var);
        }
        buf.append(src);

        return buf.toString();
    }

    /**
     * 获得一个修饰后的定长字串<br>
     * 注：长度=conzt字串的长度
     *
     * @param conzt 常量字串
     * @param var   变量字串
     * @return String
     */
    public static String getTrimString(String conzt, String var) {
        String ret = conzt + var;
        return ret.substring(var.length());
    }

    /**
     * 获得一个长度为n的字段<br>
     * 规则：<br>
     * 1、src的长度<n，则在src后补空格<br>
     * 2、src==null,则返回n个空格<br>
     * 3、src.length>=n,则返回字串长度=src.length
     *
     * @param src
     * @param n
     * @return
     */
    public static String getTrimRight(String src, int n) {
        StringBuffer buf = new StringBuffer();

        int lngth = src != null ? src.length() : 0;
        buf.append(src);
        for (int i = 0; i < n - lngth; i++) {
            buf.append(" ");
        }

        return buf.toString();
    }

    /**
     * 获得一个修饰后的定长字串<br>
     * 注：长度=conzt字串的长度
     *
     * @param conzt 常量字串
     * @param var   变量字串
     * @return String
     */
    public static String getTrimString(String conzt, int var) {
        String ret = conzt + var;
        return ret.substring(ret.length() - conzt.length());
    }

    /**
     * 获取非空字串<br>
     * 满足条件：如果字串s为null，返回默认字串sdefault，否则返回字串s
     *
     * @param s
     * @param sdefault 默认值
     * @return
     */
    public static String getNotNullString(String s, String sdefault) {
        return s != null ? s.trim() : sdefault;
    }

    /**
     * 获取非空字串
     * 满足条件：如果字串s为null，返回空字串，否则返回字串s
     *
     * @param s
     * @return
     */
    public static String getNotNullString(String s) {
        return s != null ? s.trim() : "";
    }

    /**
     * 返回数字型的String。将""转换为0
     *
     * @param s
     * @return
     */
    public static String getDoubleString(String s) {
        if (s == null)
            s = "0";
        if (s.trim().equals(""))
            s = "0";
        return s;
    }

    /**
     * 将数值类型转换成字串<br>
     * 满足条件：如果数据值iData等于默认值iNull，返回空串，否则返回将iData作为字串返回
     *
     * @param iData
     * @param iNull
     * @return
     */
    public static String getInt2String(int iData, int iNull) {
        return iData != iNull ? String.valueOf(iData) : "";
    }

    /**
     * 将数值类型转换成字串<br>
     * 满足条件：如果数据值lData等于默认值lNull，返回空串，否则返回将lData作为字串返回
     *
     * @param lData
     * @param lNull
     * @return
     */
    public static String getLong2String(long lData, long lNull) {
        return lData != lNull ? String.valueOf(lData) : "";
    }

    /**
     * 将数值类型转换成字串<br>
     * 满足条件：如果数据值fData等于默认值fNull，返回空串，否则返回将fData作为字串返回
     *
     * @param fData
     * @param fNull
     * @return
     */
    public static String getFloat2String(float fData, float fNull) {
        return fData != fNull ? String.valueOf(fData) : "";
    }

    /**
     * 将数值类型转换成字串<br>
     * 满足条件：如果数据值dData等于默认值dNull，返回空串，否则返回将dData作为字串返回
     *
     * @param dData
     * @param dNull
     * @return
     */
    public static String getDouble2String(double dData, double dNull) {
        return dData != dNull ? String.valueOf(dData) : "";
    }

    public static int getString2Int(String str) {
        int t = -1;
        try {
            t = Integer.parseInt(str);
        } catch (Exception e) {
            t = -1;
        }
        return t;

    }

    /*将double类型去掉小数点后面的0（在.0和.00的情况下）
     * dData 被转换的double
     */
    public static String doubleRemove0(double dData) {
        String tmp = Double.toString(dData);
        if (tmp.length() >= 3) {
            String a = tmp.substring(tmp.length() - 2, tmp.length());
            if (".0".equals(a))
                tmp = tmp.substring(0, tmp.length() - 2);
        }
        if ("0".equals(tmp))
            tmp = "";

        return tmp;
    }


    /**
     * 将数组中的数据转换成sql的语句,比如 field in ('a ','b ')
     *
     * @param feildname 字段名称
     * @param arrays    数组('a','b',...)
     * @param fieldtype 数据类型("CHAR")
     * @param fieldlen  字段长度
     * @return
     */
    public static String getSqlIn(String feildname, String[] arrays, String fieldtype, int fieldlen) {
        String tmp = "";
        if (feildname != null) {
            for (int i = 0; i < arrays.length; i++) {
                if (fieldtype.equals("CHAR")) {
                    if (i == 0)
                        tmp = tmp + "'" + getTrimRight(arrays[i], fieldlen) + "'";
                    else
                        tmp = tmp + ",'" + getTrimRight(arrays[i], fieldlen) + "'";
                } else {
                    if (i == 0)
                        tmp = tmp + arrays[i];
                    else
                        tmp = tmp + "," + arrays[i];
                }
            }
        }

        tmp = " " + feildname + " in (" + tmp + ")";
        return tmp;
    }

    /**
     * 将字符串转换成日期YYYY-MM-DD HH24:MI:SS
     *
     * @param str 日期字符串YYYYMMDDHH24MISS
     * @return
     */
    public static String toFormatDate(String str) {
        String sRet;
        if (str != null) {
            str = str.trim();
            if (str.trim().length() == 8)
                sRet = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
            else if (str.trim().length() == 14)
                sRet = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
            else
                sRet = str;
        } else {
            sRet = " ";
        }
        return sRet;
    }

    /**
     * 返回前面带0的数字
     *
     * @param i
     * @param length
     * @return
     */
    public static String getFormatInt(int i, int length) {
        String sRet = Integer.toString(i);
        String sI = "";
        sI = Integer.toString(i);
        if (sI.length() < length) {
            for (int k = 0; k < length - sI.length(); k++) {
                sRet = "0" + sRet;
            }
        }

        return sRet;
    }

    /**
     * 将分隔符拆分到数组
     *
     * @param str      被拆分字符串
     * @param splitstr 分隔符
     * @return
     */
    public static String[] splitStr(String str, String splitstr) {
        String[] tmp = new String[0];
        str.replaceAll(splitstr, " " + splitstr);
        if (!StringUtils.getNotNullString(str).equals("")) {
            StringTokenizer tst = new StringTokenizer(str, splitstr);
            tmp = new String[tst.countTokens()];
            int i = 0;
            while (tst.hasMoreTokens()) {
                tmp[i] = StringUtils.getNotNullString(tst.nextToken());
                i++;
            }
        }
        return tmp;
    }

    /**
     * 字符串替换，(因为String.replaceAll方法有些字符会不能替换).不适合大数据量的替换
     *
     * @param strSource
     * @param strFrom
     * @param strTo
     * @return
     */
    public static String replace(String strSource, String strFrom, String strTo) {
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;

        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;

        return strDest;
    }

    /**
     * yq added
     * 左边添加0。比如工单ID的生成规则是：日期＋sequence
     * 200605 ＋ 00000000＋ 1
     *
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        return padding(pads, padChar).concat(str);
    }


    private static String padding(int repeat, char padChar)
            throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException(
                    "Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    /**
     * 功能：根据限制长度截取字符串（字符串中包括汉字，一个汉字等于两个字符）
     *
     * @param strParameter 要截取的字符串
     * @param limitLength  截取的长度
     * @return 截取后的字符串
     */
    public static String getStrByLen(String strParameter, int limitLength) {
        String return_str = strParameter;//返回的字符串
        int temp_int = 0;//将汉字转换成两个字符后的字符串长度
        int cut_int = 0;//对原始字符串截取的长度
        byte[] b = strParameter.getBytes();//将字符串转换成字符数组

        for (int i = 0; i < b.length; i++) {
            if (b[i] >= 0) {
                temp_int = temp_int + 1;
            } else {
                temp_int = temp_int + 2;//一个汉字等于两个字符
                i++;
            }
            cut_int++;

            if (temp_int >= limitLength) {
                if (temp_int % 2 != 0 && b[temp_int - 1] < 0) {
                    cut_int--;
                }
                return_str = return_str.substring(0, cut_int);
                break;
            }
        }
        return return_str;

    }

    /**
     * 正则表达式型的替换
     *
     * @param str
     * @param map
     * @return
     */
    public static String replacePlaceholder(String str, Map<String, String> map) {
        Matcher m = Pattern.compile("\\$\\{(\\w+)}").matcher(str);
        while (m.find()) {
            String placeholder = m.group(0);
            String group = m.group(1);
            String value = map.get(m.group(1));
            if (value != null) {
                str = str.replace(placeholder, map.get(m.group(1)));
            }
        }
        return str;
    }

    public static void main(String[] args) {
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(
//                        new TypeToken<Map<String, Object>>() {
//                        }.getType(),
//                        new JsonDeserializer<Map<String, Object>>() {
//                            @Override
//                            public Map<String, Object> deserialize(
//                                    JsonElement json, Type typeOfT,
//                                    JsonDeserializationContext context) throws JsonParseException {
//
//                                Map<String, Object> treeMap = new HashMap<String, Object>();
//                                JsonObject jsonObject = json.getAsJsonObject();
//                                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
//                                for (Map.Entry<String, JsonElement> entry : entrySet) {
//                                    treeMap.put(entry.getKey(), entry.getValue());
//                                }
//                                return treeMap;
//                            }
//                        }).create();
    String newJson="{\"owner\":\"蒋x裕\",\"offline_app_config_json\":[\"5e8da255d5eca910400d64d0\"],\"offline_package_id\":\"5e8211e0d5eca97e9f412b5c\",\"cluster_online_index_conf_json\":{},\"need_alert\":true,\"hippo_config_json\":{\"role_default\":{\"priority\":{\"major_priority\":64,\"minor_priority\":0},\"processInfos\":[{\"envs\":[[\"DEFAULT_DOWNLOAD_MODE\",\"dp2\"],[\"TRUNCATE_THREAD_COUNT\",\"2\"],[\"HADOOP_HOME\",\"$HIPPO_APP_INST_ROOT/usr/local/hadoop/hadoop\"],[\"HADOOP_HDFS_HOME\",\"$HIPPO_APP_INST_ROOT/usr/local/hadoop/hadoop\"],[\"JAVA_HOME\",\"$HIPPO_APP_INST_ROOT/usr/local/java/jdk\"],[\"INDEXLIB_PLUGIN_PATH\",\"$HIPPO_APP_INST_ROOT/usr/local/lib64\"],[\"LD_LIBRARY_PATH\",\"$HIPPO_APP_INST_ROOT/usr/local/java/jdk/jre/lib/amd64/server:$HIPPO_APP_INST_ROOT/usr/local/lib64:$HIPPO_APP_INST_ROOT/usr/local/lib:$HIPPO_APP_INST_ROOT/usr/lib64/nvidia/:$HIPPO_APP_INST_ROOT/usr/local/cuda/lib64\"]],\"stopTimeout\":1,\"args\":[[\"-l\",\"$HIPPO_APP_INST_ROOT/usr/local/etc/bs/bs_alog.conf\"]]}]},\"role_customize\":[{\"_bs_role\":\"processor\",\"slotResources\":[{\"slotResources\":[{\"amount\":0,\"name\":\"T4\"},{\"amount\":600,\"name\":\"cpu\"},{\"amount\":5000,\"name\":\"mem\"},{\"amount\":15000,\"name\":\"disk_size_9999\"}]}]},{\"_bs_role\":\"job\",\"slotResources\":[{\"slotResources\":[{\"amount\":0,\"name\":\"T4\"},{\"amount\":600,\"name\":\"cpu\"},{\"amount\":5000,\"name\":\"mem\"},{\"amount\":15000,\"name\":\"disk_size_9999\"}]}]},{\"_bs_role\":\"builder\",\"slotResources\":[{\"slotResources\":[{\"amount\":0,\"name\":\"T4\"},{\"amount\":300,\"name\":\"cpu\"},{\"amount\":10000,\"name\":\"mem\"},{\"amount\":15000,\"name\":\"disk_size_9999\"}]}]},{\"_bs_role\":\"merger.*.full\",\"slotResources\":[{\"slotResources\":[{\"amount\":0,\"name\":\"T4\"},{\"amount\":600,\"name\":\"cpu\"},{\"amount\":30000,\"name\":\"mem\"},{\"amount\":15000,\"name\":\"disk_size_9999\"}]}]},{\"_bs_role\":\"merger.*\",\"slotResources\":[{\"slotResources\":[{\"amount\":0,\"name\":\"T4\"},{\"amount\":600,\"name\":\"cpu\"},{\"amount\":10000,\"name\":\"mem\"},{\"amount\":15000,\"name\":\"disk_size_9999\"}]}]}]},\"cluster_index_change_ratio_limit\":{},\"enable_o2o_inc\":false,\"offline_app_name\":\"rtp\",\"madrox_deployment_id\":[],\"plugin_conf_json\":{},\"need_sync\":true,\"table_name\":\"jcy12\",\"is_official\":true,\"send_phone\":false,\"offline_deployment\":[{\"deployment_name\":\"jcy\",\"type\":\"zk\",\"zk_server\":\"1\",\"zk_path\":\"2\",\"dump_deadline\":-1,\"inc_delay_threshold\":600,\"build_time\":-1,\"offline_env_id\":\"5d809ea4d5eca9bf3f63a691\",\"topic_swift_root\":\"\",\"topic_name\":\"4\"},{\"deployment_name\":\"qw\",\"type\":\"zk\",\"dump_deadline\":-1,\"inc_delay_threshold\":600,\"build_time\":-1,\"offline_env_id\":\"5d678611d5eca9a73d91901d\",\"topic_swift_root\":\"\",\"topic_name\":\"\"}],\"change_ratio_limit\":10,\"has_priority\":false,\"table_type\":\"rtp_content\"}";
        JSONObject jsonObject = JSON.parseObject(newJson);
        System.out.println(jsonObject);

    }

    /**
     * 验证字母打头，数字、字母下划线的命名规则
     *
     * @param str
     * @return
     */
    public static Boolean isValidVar(String str) {
        Boolean bl = false;
        Pattern pt = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_:-]{0,128}$");
        Matcher mt = pt.matcher(str);
        if (mt.matches()) {
            bl = true;
        }
        return bl;
    }

    /**
     * 去掉工号开头0
     *
     * @param str
     * @return
     */
    public static String subUserId(String str) {
        if (str != null) {
            return str.trim().replaceAll("^0*(\\d+)$", "$1");
        }
        return "";
    }

    /**
     * @param jsonStr
     * @param type    1为下划线转驼峰
     * @return
     */
    public static String jsonKeyEdit(String jsonStr, int type) {

        //转换后的字符串
        String str = jsonStr;

        //满足json字符串key的正则
        String regx = "\"\\w+\":";

        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while (matcher.find()) {
            //取代下划线命名的key为驼峰命名的key
            //具体代码请移步上一篇文章 http://blog.csdn.net/qq_25386583/article/details/77001126
            if (type == 1) {
                str = str.replaceFirst(matcher.group(), camelCase(matcher.group()));
            } else {
                str = str.replaceFirst(matcher.group(), humpToLine(matcher.group()));
            }
        }

        return str;
    }


    /**
     * _aa__Bc_C_c_  ==>  aaBcCc
     *
     * @param str
     * @return 驼峰命名字符串
     */
    public static String camelCase(String str) {
        String camelCase = "";
        String[] arr = str.split("_");
        List<String> list = new ArrayList<String>();

        //将数组中非空字符串添加至list
        for (String a : arr) {
            if (a.length() > 0) {
                list.add(a);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {    //后面单词首字母大写
                char c = list.get(i).charAt(0);
                String s = String.valueOf(c).toUpperCase() + list.get(i).substring(1).toLowerCase();
                camelCase += s;
            } else {  //首个单词小写
                camelCase += list.get(i).toLowerCase();
            }
        }
        return camelCase;
    }


    /**
     * @param jsonStr
     * @param type    1为下划线转驼峰
     * @return
     */
    public static String jsonKeyEditNumber(String jsonStr, int type) {

        //转换后的字符串
        String str = jsonStr;

        //满足json字符串key的正则
        String regx = "\"(.*?)\":\"/[1-9][0-9]*/\\\"";

        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while (matcher.find()) {
            //取代下划线命名的key为驼峰命名的key
            //具体代码请移步上一篇文章 http://blog.csdn.net/qq_25386583/article/details/77001126
            if (type == 1) {
                str = str.replaceFirst(matcher.group(), camelCase(matcher.group()));
            } else {
                str = str.replaceFirst(matcher.group(), humpToLine(matcher.group()));
            }
        }

        return str;
    }

    /**
     * _aa__Bc_C_c_  ==>  aaBcCc
     *
     * @param str
     * @return 该数字格式
     */
    public static String toRevise(String str) {
        String camelCase = "";
        String[] arr = str.split("_");
        List<String> list = new ArrayList<String>();

        //将数组中非空字符串添加至list
        for (String a : arr) {
            if (a.length() > 0) {
                list.add(a);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {    //后面单词首字母大写
                String s1 = list.get(i);
//                String s = String.valueOf(c).toUpperCase() + list.get(i).substring(1).toLowerCase();
//                camelCase += s;
            } else {  //首个单词小写
                camelCase += list.get(i).toLowerCase();
            }
        }
        return camelCase;
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
