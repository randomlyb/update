package com.msb.test02;


import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
        public static void main(String[] args) throws SQLException, ClassNotFoundException {


            ArrayList list = new ArrayList();//书的集合
            while (true) {
                System.out.println("欢迎来到【老马书城】");
                System.out.println("1.根据书籍编号查询书籍信息");
                System.out.println("2.查询所有书籍");
                System.out.println("3.下架书籍");
                System.out.println("4.退出应用");
                //根据键盘输入执行相关指令
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入要执行的任务代号");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("请录入你要查看的书籍编号");
                    int bno = sc.nextInt();
                    book b = findbookbybno(bno);
                    if (b == null) {
                        System.out.println("你要查询的书籍不存在");
                    } else {
                        System.out.println("当前查询到一本书《" + b.getName() + "》");
                    }

                }
                if (choice == 2) {
                    ArrayList books = foundbooks();
                    if (books.size() == 0) {
                        System.out.println("没有查询到书籍");
                    }
                    for (int i = 0; i < books.size() - 1; i++) {
                        book b = (book) books.get(i);
                        System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getAuthor() + "\t" + b.getPrice());
                    }

                }
                if (choice == 3) {
                    System.out.println("请录入要删除的书籍编号");
                    int bno = sc.nextInt();
                    int n = deletbookbybno(bno);
                    if (n <= 0) {
                        System.out.println("删除失败");
                    } else {
                        System.out.println("删除成功");
                    }
                }


                if (choice == 4) {
                    System.out.println("【老马书城】>>>>>>4.退出应用");
                    break;
                }
            }
            }
        }

        public static  book findbookbybno (int bno) throws ClassNotFoundException, SQLException {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        /*
        useSSL是否使用ssl认证机制，不使用falsee
        usenicode:是否使用unicode字符集-使用
        charactencoding=utf-8,使用utf-8策略
        servertimezone=asia/shanghai时区参数-东八区
        allow......即允许客户端从服务器获取公钥

        */
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);
            //创建会话
            Statement sta = conn.createStatement();
            //发送mysql,result结果集合
            ResultSet rs = sta.executeQuery("select * from t_book where id= " + bno);
            //处理结果
            book b = null;
            if (rs.next()) {
                //先将结果做接受
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                //将上面的结果分装为book对象
                b = new book();
                b.setId(id);
                b.setName(name);
                b.setAuthor(author);
                b.setPrice(price);
            }
            sta.close();
            conn.close();
            return b;
        }

        public static ArrayList foundbooks() throws SQLException, ClassNotFoundException {
            ArrayList list = new ArrayList();
            //导入数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        /*
        useSSL是否使用ssl认证机制，不使用falsee
        usenicode:是否使用unicode字符集-使用
        charactencoding=utf-8,使用utf-8策略
        servertimezone=asia/shanghai时区参数-东八区
        allow......即允许客户端从服务器获取公钥

        */
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);
            //创建会话
            Statement sta = conn.createStatement();
            //发送mysql,result结果集合
            ResultSet rs = sta.executeQuery("select * from t_book ");
            //处理结果
            book b = null;
            while (rs.next()) {
                //先将结果做接受
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                //将上面的结果分装为book对象
                book b = new book();
                b.setId(id);
                b.setName(name);
                b.setAuthor(author);
                b.setPrice(price);
                list.add(b);
            }
            sta.close();
            conn.close();
            return b;
        }
    public static  int deletbookbybno (int bno) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        /*
        useSSL是否使用ssl认证机制，不使用falsee
        usenicode:是否使用unicode字符集-使用
        charactencoding=utf-8,使用utf-8策略
        servertimezone=asia/shanghai时区参数-东八区
        allow......即允许客户端从服务器获取公钥

        */
        String user = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);
        //创建会话
        Statement sta = conn.createStatement();
        //发送mysql,result结果集合
        int n= sta.executeUpdate("delete elect * from t_book where id= " + bno);
        //处理结果
        book b = null;
        if (rs.next()) {
            //先将结果做接受
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            double price = rs.getDouble("price");
            //将上面的结果分装为book对象
            b = new book();
            b.setId(id);
            b.setName(name);
            b.setAuthor(author);
            b.setPrice(price);
        }
        sta.close();
        conn.close();
        return n;

        }