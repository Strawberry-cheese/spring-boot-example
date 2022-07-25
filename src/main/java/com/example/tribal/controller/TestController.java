package com.example.tribal.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.tribal.entity.City;
import com.example.tribal.service.CityService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 09:52
 **/
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private CityService cityService;


    @Autowired
    @Qualifier("restHighLevelClient")
    RestHighLevelClient client;


    @GetMapping
    public String sayHello() throws Exception {

        //1、构建 创建索引的请求
        CreateIndexRequest request = new CreateIndexRequest("xk_index");//索引名
        //2、客户端执行请求,获取响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("创建成功，创建的索引名为：" + response.index());
        System.out.println("1");

        return "say hello";
    }


    /**
     * 获取索引
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getIndex")
    public boolean getIndex() throws Exception {
        //1、构建 获取索引的请求
        GetIndexRequest request = new GetIndexRequest("xk_index");
        //2、客户端判断该索引是否存在
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("该索引是否存在：" + exists);
        return exists;
    }


    /**
     * 删除索引
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/deleteIndex")
    public boolean deleteIndex() throws Exception {
        //1、构建 删除索引请求
        org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest request = new DeleteIndexRequest("xk_index");
        //2、客户段执行删除的请求
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("是否删除成功：" + response.isAcknowledged());
        return response.isAcknowledged();
    }


    /**
     * 创建文档
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/createDocument")
    public String createDocument() throws Exception {
        City city = new City();
        city.setCityId("TA");
        city.setCityName("泰安");
        city.setRegionId(1);
        city.setRegionName("华北");
        //1、构建请求
        IndexRequest request = new IndexRequest("city_index");

        //2、设置规则  PUT /user_index/user/_doc/1
        request.id("1");//设置id
        request.timeout(TimeValue.timeValueSeconds(1));//设置超时时间

        //3、将数据放入到请求中,以JSON的格式存放
        request.source(JSONObject.toJSONString(city), XContentType.JSON);

        //4、客户端发送请求,获取响应结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //5、打印
        System.out.println("响应结果：" + response.toString());

        return response.toString();

    }

    /**
     * 获取文档
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getDocument")
    public String getDocument() throws Exception {
        //获取id为1的文档的信息
        GetRequest request = new GetRequest("city_index", "1");
        GetResponse response = null;

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println("文档是否存在：" + exists);
        //如果存在，获取文档信息
        if (exists) {
            response = client.get(request, RequestOptions.DEFAULT);
            System.out.println("文档内容为：" + response.getSourceAsString());
        }
        return response.getSourceAsString();
    }


    /**
     * 更新文档
     */

    @GetMapping("/updateDocument")
    public String updateDocument() throws IOException {
        //更新id为1的文档的信息
        UpdateRequest request = new UpdateRequest("user_index", "1");

        City city = new City();
        city.setCityName("青岛");
        request.doc(JSONObject.toJSONString(city), XContentType.JSON);

        //客户端执行更新请求
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("更新状态：" + response.status());
        return response.status().toString();
    }


    /**
     * 删除文档
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/deleteDocument")
    public String deleteDocument() throws IOException {
        //构建删除请求
        DeleteRequest request = new DeleteRequest("user_index", "1");
        //客户端执行删除请求，并获取响应结果
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        //打印
        System.out.println("删除状态：" + response.status());
        return response.status().toString();
    }


    /**
     * 批量插入数据
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/createBulkDocument")
    public boolean createBulkDocument() throws IOException {
        //构建批量插入的请求
        BulkRequest request = new BulkRequest();
        //设置超时时间
        request.timeout("10s");

        List<City> cityList = cityService.getCityDMList();


        //批量插入请求设置
        for (int i = 0; i < cityList.size(); i++) {
            request.add(
                    new IndexRequest("city_index")//设置索引
                            .id(String.valueOf(i + 1))//设置文档的id，如果没有指定，会随机生成，自己测试
                            .source(JSONObject.toJSONString(cityList.get(i)), XContentType.JSON)//设置要添加的资源，类型为JSON
            );
        }
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("批量插入是否失败：" + response.hasFailures());


        return response.hasFailures();
    }


    /**
     * 根据key 查询城市信息
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/query")
    public String query() throws IOException {
        //1、构建搜索请求
        SearchRequest request = new SearchRequest("city_index");

        //2、设置搜索条件，使用该构建器进行查询
        SearchSourceBuilder builder = new SearchSourceBuilder();//生成构建器

        //查询条件我们可以用工具类QueryBuilders来构建
        //QueryBuilders.termQuery()：精确匹配
        //QueryBuilders.matchAllQuery()：全文匹配

        //构建精确匹配查询条件
        //构建精确匹配查询条件
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("cityName.keyword", "北京");
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "张");
        builder.query(termQueryBuilder);

        //3、将搜索条件放入搜索请求中
        request.source(builder);
        //4、客户端执行搜索请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //5、打印测试
        SearchHit[] hits = response.getHits().getHits();
        System.out.println("共查询到" + hits.length + "条数据");
        System.out.println("查询结果：");
        for (int i = 0; i < hits.length; i++) {
            System.out.println(hits[i].getSourceAsString());
        }
        return hits.length + "";
    }

}
