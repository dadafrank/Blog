package dao;

import c3p0.C3P0Utils;
import model.Article;
import model.Other;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class Otherdao {
    private static QueryRunner qr;
    private static int row;
    private static String sql;
    private static Object params;
    private static Connection conn;

    public static Boolean updatepv (Other other) {
        qr = new QueryRunner();
        row = 0;
        sql = "update other set pv = ? where id =1 ";
        params = new Object[] {
                other.getPv()
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



    public  static Boolean updatelove (int num) {
        qr = new QueryRunner();
        row = 0;
        sql = "update other set love = ? where id = 1";

        try {
            conn = C3P0Utils.getConnection();
            row = qr.update(conn,sql,num);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  row>0;
    }
//    love的获取
    public  static Other getLovenum() {
        qr = new QueryRunner();
        sql = "select * from other where id =1";
        Other art=new Other();
        try {
            conn = C3P0Utils.getConnection();
            art = (Other) qr.query(conn,sql,new BeanHandler(Other.class));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return art;
    }

}
