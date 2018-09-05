package dao;

import c3p0.C3P0Utils;
import model.Article;
import model.Message;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Articledao {
    private static QueryRunner qr;
    private static int row;
    private static String sql;
    private static Object[] params;
    private static Connection conn;


//    插入文章
    public static Boolean insert(Article article) {
        qr = new QueryRunner();
        row =0;
        sql="insert into article (name,author,disc,content,time,url) values(?,?,?,?,?,?)";
//        params = new Object[] {
//                article.getName(),article.getAuthor(),article.getDisc(),article.getContent(),article.getTime(),article.getUrl()
//        };

        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn,sql,article.getName(),article.getAuthor(),article.getDisc(),article.getContent(),article.getTime(),article.getUrl());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row>0;
    }


//    修改文章
    public  static  Boolean update(Article article) {
        qr = new QueryRunner();
        row = 0;
        sql= "update article set name = ?,author = ?,disc = ?,content = ?,url = ? where id=?";
        params = new Object[] {
                article.getName(),article.getAuthor(),article.getDisc(),article.getContent(),article.getUrl(),article.getId()
        };

        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn,sql,params);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return row>0;
    }



//    删除文章

    public static Boolean delete(int art_id) {
        qr = new QueryRunner();
        row = 0;
        sql="delete from article where id=?";
        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn,sql,art_id);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row>0;
    }




//    根据id查找文章
    public  static Article getArticle(int id) {
        qr = new QueryRunner();
        sql = "select * from article where id=?";
        params = new Object[] {id};
        Article art=new Article();
        try {
            conn = C3P0Utils.getConnection();
            art = (Article) qr.query(conn,sql,new BeanHandler(Article.class),params);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return art;
    }
//      读取文章
    public static List<Article> getlatestArticle(int limit) {
        qr = new QueryRunner();
        sql = "select * from article order by time desc where ?";
        params = new Object[]{limit};
        List<Article> beanList = null;
        try {
            conn = C3P0Utils.getConnection();
            beanList = (List<Article>) qr.query(conn, sql, new BeanListHandler(Article.class), params);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beanList;
    }


    //    根据文章标题查找文章
    public static List<Article> searchArticle(String name) {
        qr = new QueryRunner();
        sql = "SELECT * FROM article where name like ?";
        List<Article> beans = null;
        System.out.println(name);
        try {
            conn = C3P0Utils.getConnection();
            beans = qr.query(conn, sql, new BeanListHandler<>(Article.class),"%"+name+"%");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beans;
    }
//读取文章
    public static List<Article> getArticle() {
        qr = new QueryRunner();
        sql = "select * from article order by time desc";
        List<Article> allArticles = null;
        try {
            conn = C3P0Utils.getConnection();
            allArticles = qr.query(conn,sql, new BeanListHandler<>(Article.class));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allArticles;
    }

}
