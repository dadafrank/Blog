package dao;

import c3p0.C3P0Utils;
import com.mchange.v2.c3p0.cfg.C3P0Config;
import model.Art_mess;
import model.Message;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Art_messdao {
    private static QueryRunner qr;
    private static int row;
    private static String sql;
    private static Object[] params;
    private static Connection conn;


    //    插入留言
//    public static Boolean insert(Art_mess art_mess) {
//        qr = new QueryRunner();
//        row=0;
//        sql = "inset into art_mess (art_id,author,email,content,time) values(?,?,?,?,?)";
//        params = new Object[] {
//                art_mess.getArt_id(),art_mess.getAuthor(),art_mess.getEmail(),art_mess.getContent(),art_mess.getTime()
//        };
//
//        try {
//                conn = C3P0Utils.getConnection();
//            row = qr.update(conn,sql,params);
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return row>0;
//    }
    public static Boolean insert(Art_mess art_mess) {
        QueryRunner qr = new QueryRunner();
        row = 0;
        sql = "INSERT INTO art_mess (art_id,author, email, content, time) VALUES (?,?, ?, ?, ?)";

        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn, sql, art_mess.getArt_id(), art_mess.getAuthor(), art_mess.getEmail(), art_mess.getContent(), art_mess.getTime());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row > 0;
    }


    //    删除留言
    public static Boolean delete(int art_id,int id) {
        qr = new QueryRunner();
        row = 0;
        sql = "delete from art_mess where art_id = ? and id = ?";

        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(C3P0Utils.getConnection(), sql, art_id,id);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row > 0;
    }

    //    读取留言
    public static List<Art_mess> getArt_mess(int art_id) {
        qr = new QueryRunner();
        sql = "select * from art_mess where art_id = ? order by time desc ";
        List<Art_mess> allArt_mess = null;
        try {
            conn = C3P0Utils.getConnection();
            allArt_mess = qr.query(conn,sql, new BeanListHandler<>(Art_mess.class),art_id);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allArt_mess;
    }

}
