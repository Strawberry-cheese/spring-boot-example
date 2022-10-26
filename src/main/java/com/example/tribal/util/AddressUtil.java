package com.example.tribal.util;


import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/8/16 下午4:44
 */


public class AddressUtil {
    /**
     * 当前记录地址的本地DB
     */
    private static final String TEMP_FILE_DIR = "/home/admin/app/";

    /**
     * 根据IP地址查询登录来源
     *
     * @param ip
     * @return
     */
    public static String getCityInfo(String ip) {
        try {
            // 获取当前记录地址位置的文件
            String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.xdb")).getPath();
            File file = new File(dbPath);
            //如果当前文件不存在，则从缓存中复制一份
            if (!file.exists()) {
                dbPath = TEMP_FILE_DIR + "ip.db";
                System.out.println(MessageFormat.format("当前目录为:[{0}]", dbPath));
                file = new File(dbPath);
                FileUtils.copyInputStreamToFile(Objects.requireNonNull(AddressUtil.class.getClassLoader().getResourceAsStream("classpath:ip2region/ip2region.xdb")), file);
            }
            //创建查询对象
            Searcher searcher = Searcher.newWithFileOnly(dbPath);
            //开始查询
            return searcher.searchByStr(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认返回空字符串
        return "";
    }

    public static void main1(String[] args) {
        System.out.println(getCityInfo("60.208.23.13"));
    }


    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("主机名：" + localHost.getHostName());
            System.out.println("本地ip地址：" + localHost.getHostAddress());
            System.out.println(getV4IP());
            System.out.println(getCityInfo(get("http://checkip.amazonaws.com/")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String getV4IP() {
        String ip = "";
        String chinaz = "ht" + "tp" + ":/" + "/i" + "p.ch" + "in" + "az." + "co" + "m";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
        }
        return ip;
    }


    /**
     * IP 地址校验的正则表达式
     */
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    /**
     * 获取 IP 地址的服务列表
     */
    private static final String[] IPV4_SERVICES = {
            "http://checkip.amazonaws.com/",
            "https://ipv4.icanhazip.com/",
            "http://bot.whatismyipaddress.com/"
            // and so on ...
    };

    public static String get() throws ExecutionException, InterruptedException {
        List<Callable<String>> callables = new ArrayList<>();
        for (String ipService : IPV4_SERVICES) {
            callables.add(() -> get(ipService));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // 返回第一个成功获取的 IP
            return executorService.invokeAny(callables);
        } finally {
            executorService.shutdown();
        }
    }

    private static String get(String url) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String ip = in.readLine();
            if (IPV4_PATTERN.matcher(ip).matches()) {
                return ip;
            } else {
                throw new IOException("invalid IPv4 address: " + ip);
            }
        }
    }


}
