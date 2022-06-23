package cookbook.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAO {
    // --- Parameter
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.50.99:3306/recipe";
    private static final String user = "root";
    private static final String psw = "1q2w3e4r";

    // --- Private Member
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    // --- Auto-Run
    static {
        try {
            // 加载驱动
            Class.forName(driver);
            // 连接数据库
            con = DriverManager.getConnection(URL, user, psw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Public Function
    // 将图片放进数据库
    // id->Rid，pic是文件路径
    public static void uploadImageByRecipeID(int recipeID, String picPath) {
        // "?"是参数，相当于Qt中QString中的"%1"
        String sql = "update recipe set picture=?, picture_type=? where id = ?";
        String type = picPath.substring(picPath.lastIndexOf((int) '.'));
        try {
            pst = con.prepareStatement(sql);
            // 打开文件
            FileInputStream fis = new FileInputStream(picPath);
            // 将第一个问号替换为id
            pst.setBlob(1, fis, fis.available());
            // 替换第二个问号
            pst.setString(2, type);
            // 替换第三个问号
            pst.setString(3, recipeID + "");
            // 执行SQL
            pst.executeUpdate();
            // 关闭资源
            fis.close();
            pst.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 将图片从数据库中取出
    // id->Rid，path是存图路径
    public static boolean downloadImageToPathByRecipeID(int id, String path) {
        String sql = "SELECT * FROM recipe WHERE id=?";
        boolean got = false;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, id + "");
            rs = pst.executeQuery();
            if (rs.next()) { // 找到记录
                InputStream is = rs.getBinaryStream("picture");
                byte buf[] = new byte[is.available()];
                is.read(buf);
                String type = rs.getString("picture_type");
                FileOutputStream fos = new FileOutputStream(path + id + type);
                fos.write(buf);
                // 刷新缓冲区
                fos.flush();
                // 关闭流
                fos.close();
                is.close();
            } else { // 没找到
                System.out.println("Not such id!");
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return got;
    }

    public static void main(String args[]) {
        uploadImageByRecipeID(1, "src/cookbook.icon/GongBaoJiDing.jpg");
        downloadImageToPathByRecipeID(1, "src/storage/");
    }

}