package defaultpackage.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class SqlDemoController {

    @Value("${demo.identity.test}")
    private String test;

    @RequestMapping("/connect")
    @ResponseBody
    public String connect() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement state = null;

        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            System.out.println("start to connect with database");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DemoDB","root","12345678");

        } catch (ClassNotFoundException | SQLException e) {
            throw(e);
        } finally {

        }
        return "success to connect MySQL";
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody JSONObject jsonObject) throws JSONException, SQLException,
            ClassNotFoundException {
        //response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // * 表示允许任何域名跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");

        String email =  jsonObject.get("email").toString();
        String name = jsonObject.get("name").toString();
        String phone = jsonObject.get("phone").toString();
        System.out.println("add user name :"+name+", emnail :"+email+", phone :"+phone+", *:"+test);

        //写入db
        Connection conn = null;
        PreparedStatement state = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            System.out.println("start to connect with database"+new Date(new java.util.Date().getTime()));
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DemoDB",
                    "root","12345678");
            //String sqladd="insert into user_table (user_name, user_email, user_phone, create_date, update_date, avg_date) VALUES (?, ?, ?, ?, ?, ?)";
            String sqladd="insert into user_table (user_name,user_email,user_phone) VALUES (email,email,phone)";
            state = conn.prepareStatement(sqladd);
            System.out.println("**********name :"+name);
            //state.setString(1,name);
            //state.setString(2,email);
            //state.setString(3,phone);
//            Date dNow = new Date(new java.util.Date().getTime());
//            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//            state.setDate(4,dNow);
//            state.setDate(5,dNow);
//            state.setFloat(6,93);
            //得到结果集
            int result = state.executeUpdate(sqladd);

        } catch (ClassNotFoundException | SQLException e) {
            throw(e);
        } finally {

        }


        return "success to add user :"+name;
    }

}
