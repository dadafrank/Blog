package dao;

import model.Message;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import c3p0.C3P0Utils;

public class Messagedao {
    private static QueryRunner qr;
    private static int row;
    private static String sql;
    private static Object params;
    private static Connection conn;



//    插入留言
    public static Boolean insert(Message message) {
        QueryRunner qr = new QueryRunner();
        row = 0;
        sql = "INSERT INTO message (author, email, content, time) VALUES (?, ?, ?, ?)";
//        params = new Object[]{message.getAuthor(), message.getEmail(), message.getContent(), message.getTime()};
        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(C3P0Utils.getConnection(), sql, message.getAuthor(), message.getEmail(), message.getContent(), message.getTime());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row>0;
    }



//    删除留言
    public static Boolean delete(int id) {
        qr = new QueryRunner();
        row = 0;
        sql = "delete from message where id = ?";
        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn,sql,id);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row>0;
    }


//读取留言
    public static List<Message> getMessages() {
        qr = new QueryRunner();
        sql = "select * from message order by time desc";
        List<Message> beans = null;

        try {
            conn = C3P0Utils.getConnection();
            beans = qr.query(conn, sql, new BeanListHandler<>(Message.class));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beans;
    }
//    主页读取留言
    public static List<Message> getMessageslimit(int limition) {
        qr = new QueryRunner();
        sql = "select * from message order by time desc limit ?";
//  或者      sql = "select count(?) from message order by time desc";
        List<Message> beans = null;

        try {
            conn = C3P0Utils.getConnection();
            beans = qr.query(conn, sql, new BeanListHandler<>(Message.class),limition);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beans;

    }
}
