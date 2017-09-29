package com.itcast.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

/**
 * Created by 朱梦光 on 2017/9/29.
 */
public class IndexManagerTest {

     @Test
    public void testIndexCreate()throws  Exception{


         //创建和solr服务端连接
         SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");

         //创建solr文档对象
         SolrInputDocument doc = new SolrInputDocument();
         //域要先定义后使用必须要有id主键域
         doc.addField("id","001");
         doc.addField("product_name","王洪川");
         doc.addField("product_price","12.5");

         //将文档加入SolrServer中
         solrServer.add(doc);

         //提交
         solrServer.commit();


    }
    @Test
    public void testIndexDel()throws  Exception{


        //创建和solr服务端连接
        SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");


         //根据主键id进行删除
        //solrServer.deleteById("001");
        //根据查询删除删除所有
        solrServer.deleteByQuery("*:*");
        //提交
        solrServer.commit();


    }







}
