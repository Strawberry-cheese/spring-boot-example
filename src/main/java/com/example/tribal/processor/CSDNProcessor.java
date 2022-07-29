package com.example.tribal.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import java.util.List;
/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-26 11:16
 **/
public class CSDNProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        String level = page.getRequest().getExtra("level");
        if("parent".equals(level)) {
            List<Selectable> css = page.getHtml().css("li.clearfix").nodes();
            for (Selectable selectable : css) {
                String url =  selectable.xpath("//h2/a/@href").toString();
                // 部分三：从页面发现后续的url地址来抓取
                Request request = new Request();
                request.setUrl(url);
                request.putExtra("level", "child");
                page.addTargetRequest(request);
            }
        }
        else if("child".equals(level)){
            page.putField("title",page.getHtml().xpath("//*[@id=\"articleContentId\"]/text()"));
            page.putField("url",page.getUrl());
            List<String> labalList = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[2]/div/a/text()").all();
            page.putField("label",labalList);

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Request request = new Request();
        request.setUrl("https://blog.csdn.net/?spm=1001.2014.3001.4477");
        request.putExtra("level", "parent");

        Spider.create(new CSDNProcessor())
                //从csdn首页开始抓
                .addRequest(request)
//                .addPipeline(new JsonFilePipeline("E:\\webmagic"))
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
