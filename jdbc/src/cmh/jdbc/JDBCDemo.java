package cmh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        //注册驱动对象
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接对象
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String root = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, root, password);
        //定义sql语句
        String sql = "update stu set math = 100 where name = '马运'";
        //获取执行sql对象
        Statement statement = connection.createStatement();
        //执行sql
        int count = statement.executeUpdate(sql);//返回受影响的行数
        System.out.println(count);
        //释放资源
        connection.close();
        statement.close();
    }
}
