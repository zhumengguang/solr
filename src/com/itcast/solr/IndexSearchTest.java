package com.itcast.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by 朱梦光 on 2017/9/29.
 */
public class IndexSearchTest {


    @Test
    public void testIndxSearch1()throws Exception{
        //连接solr服务端
        SolrServer solrServer  = new HttpSolrServer("http://localhost:8080/solr/");

        //创建solr的条件查询对象
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");


        //查询并获取响应对象
        QueryResponse queryResponse = solrServer.query(solrQuery);
        //从查询响应中获取查询结果集对象
        SolrDocumentList result = queryResponse.getResults();
        //打印一共查询的记录总数
        System.out.println("=====count======"+result.getNumFound());
        for (SolrDocument doc :result) {


            System.out.println("===="+doc.get("id"));
            System.out.println("===="+doc.get("product_catalog_name"));
            System.out.println("===="+doc.get("product_price"));
            System.out.println("===="+doc.get("product_name"));

        }





    }
    @Test
    public void testIndxSearch2()throws Exception{
        //连接solr服务端
        SolrServer solrServer  = new HttpSolrServer("http://localhost:8080/solr/");

        //创建solr的条件查询对象
        SolrQuery solrQuery = new SolrQuery();
        //查询关键字
        solrQuery.setQuery("台灯");

        //设置默认搜索域
        solrQuery.set("df", "product_keywords");
        //设置过滤查询
        solrQuery.addFilterQuery("product_price:[1 TO 100]");
        //设置排序这里是降序
        solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        //设置起始条数
        solrQuery.setStart(0);
        //设置查询条数
        solrQuery.setRows(10);

        //设置高亮显示,默认是关闭的要手动开启
        solrQuery.setHighlight(true);
        //设置高亮显示的域
        solrQuery.addHighlightField("product_name");
        //设置高亮前缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        //设置高亮后缀
        solrQuery.setHighlightSimplePost("</span>");


        //查询并获取响应对象
        QueryResponse queryResponse = solrServer.query(solrQuery);
        //从查询响应中获取查询结果集对象
        SolrDocumentList result = queryResponse.getResults();
        //打印一共查询的记录总数
        System.out.println("=====count======"+result.getNumFound());
        for (SolrDocument doc :result) {


            System.out.println("===="+doc.get("id"));
            System.out.println("===="+doc.get("product_catalog_name"));
            System.out.println("===="+doc.get("product_price"));
            //获取高亮
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list  = highlighting.get(doc.get("id")).get("product_name");
            if(list!=null&&list.size()>0){

               String hlName =  list.get(0);
                System.out.println("=====high lighting===="+hlName);
            }

            System.out.println("===="+doc.get("product_name"));

        }





    }

















}
