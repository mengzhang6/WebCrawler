package crawler.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlUtils {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1/zhihuhot_full_data";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;

	private MySqlUtils() {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static MySqlUtils call = new MySqlUtils();

	public static MySqlUtils getInstance() {
		return call;
	}

	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void releaseConn() {
		try {
			if (metaData != null) {
				metaData = null;
			}
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> finddatabase(String sql) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			stmt = getConnection().createStatement();
			resultSet = stmt.executeQuery(sql);
			metaData = resultSet.getMetaData();
			int col_len = metaData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 0; i < col_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = resultSet.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConn();
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> finddatabase_list(String sql) {
		List<Map> list = new ArrayList<Map>();

		try {
			stmt = getConnection().createStatement();
			resultSet = stmt.executeQuery(sql);
			metaData = resultSet.getMetaData();
			int col_len = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < col_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = resultSet.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConn();
		}
		return list;
	}

	public void runsql(String sql) {
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConn();
		}
	}

}
