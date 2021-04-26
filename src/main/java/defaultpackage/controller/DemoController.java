package defaultpackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/index")
    @ResponseBody

    public String index(){
        return "Hello Spring Boot!";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String account, String password) throws SQLException, ClassNotFoundException {

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement state = null;

        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            System.out.println("start to connect with database");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","12345678");
            System.out.println("success to connect with database");

            //写sql     增删查改
            //根据数据库实际的表名写SQL语句
            String sqladd="insert into account (account, password) VALUES ('12abc','123456')";
            String sqldel="insert into account (account, password) VALUES ('12abc','123456')";
            String sqlsea="select * from account";
            String sqlupd="insert into account (account, password) VALUES ('12abc','123456')";
            //得到statement对象执行sql
            state = conn.prepareStatement(sqlsea);
            //得到结果集
            rs = state.executeQuery();
            //处理结果集
            while(rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw(e);
            //e.printStackTrace();
        } finally {

        }

        return "You are login with Account :"+account+" ,and password :"+password+" .Hello ,Thank you ,give me a MI band !!!";
    }
}
