package cmh.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //导包
        //定义配置文件
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc/src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //System.out.println(System.getProperty("user.dir"));
    }
}
