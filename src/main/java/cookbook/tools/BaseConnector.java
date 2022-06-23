package cookbook.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseConnector {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/cookbook_group1?useSSL=false";
	private static String user = "root";
	private static String password = "123456";

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	static Connection startConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public int executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = startConnection();
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			ResultSet num = pstmt.executeQuery();
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			try {
				BaseConnector.closeAll(conn, pstmt, null);
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
		return 0;
	}

}
