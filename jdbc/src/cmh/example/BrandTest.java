package cmh.example;

import cmh.pojo.Brand;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
* 品牌测试增删改查
* */
public class BrandTest {
    /*
    * 查询所有
    * */
    @Test
    public void testSelectAll() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        String sql = "Select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        Brand brands = null;
        List<Brand> brandList = new ArrayList<>();
        //遍历结果集
        while(resultSet.next()){
            //获取数据
            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            //封装Brand对象
            brands = new Brand();
            brands.setId(id);
            brands.setBrandName(brandName);
            brands.setCompanyName(companyName);
            brands.setOrdered(ordered);
            brands.setDescription(description);
            brands.setStatus(status);
            //将brand对象添加到集合中去
            brandList.add(brands);
        }
        //打印brand对象
        System.out.println(brandList);


    }
    /*
    * 添加商品列表
    * */
    @Test
    public void testAdd() throws Exception {
        String brandName = "蔡徐坤";
        String companyName = "蔡徐坤传媒";
        int ordered = 3;
        String description = "只因你太美";
        int status = 1;
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        //sql语句
        String sql = "insert into tb_brand (brand_name,company_name,ordered,description,status)values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
       //设置参数
        preparedStatement.setString(1,brandName);
        preparedStatement.setString(2,companyName);
        preparedStatement.setInt(3,ordered);
        preparedStatement.setString(4,description);
        preparedStatement.setInt(5,status);

        int count = preparedStatement.executeUpdate();
        //运行结果
        System.out.println(count > 0);
        connection.close();
        preparedStatement.close();
    }
    @Test
    public void testUpdate() throws Exception {
        String brandName = "蔡徐坤";
        String companyName = "蔡徐坤传媒";
        int ordered = 300;
        String description = "只因你太美,baby";
        int status = 1;
        int id = 4;

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        //sql语句
        String sql = "update tb_brand\n"+
                    "   set brand_name = ?,\n"+
                    "   company_name = ?,\n"+
                    "   ordered = ?,\n"+
                    "   description = ?,\n"+
                    "   status = ? \n"+
                    "where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        preparedStatement.setInt(6, id);

        int count = preparedStatement.executeUpdate();
        //运行结果
        System.out.println(count > 0);
        connection.close();
        preparedStatement.close();
    }
    @Test
    public void testDeleteById() throws Exception {
        int id = 4;

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        //sql语句
        String sql = "delete from tb_brand where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setInt(1, id);

        int count = preparedStatement.executeUpdate();
        //运行结果
        System.out.println(count > 0);
        connection.close();
        preparedStatement.close();
    }
}
