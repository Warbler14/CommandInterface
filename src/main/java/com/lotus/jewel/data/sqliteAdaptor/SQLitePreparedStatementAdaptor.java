package com.lotus.jewel.data.sqliteAdaptor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lotus.jewel.data.exception.DataCoreException;




public class SQLitePreparedStatementAdaptor {
	
	
	//TODO 범용성을 갖도록 수정, 테이블 생성, 테스트 쿼리 실행 정도로.., 테이블 여러개 만들 수 있도록
	/*
	 * 만들것들
	 * 
	 * 1. 데이터베이스와 테이블 생성을 위한 유틸리티 만들기
	 * 
	 * 
	 * 
	 * 
	 * */

	public static void executeSql(String url, final String sql) throws DataCoreException {
		executeSql(url, sql, null);
	}
	
	public static void executeSql(String url, final String sql, final List<Object> setList) throws DataCoreException{
		try (Connection conn = DriverManager.getConnection(url)) {
			if(conn == null) {
				return;
			}
			
//			DatabaseMetaData meta = conn.getMetaData();
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatementSetter(preparedStatement, setList);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			

		} catch (SQLException e) {
			throw new DataCoreException(e);
		}

	}
	
	public static void executeInsertSql(String url, final String tableName, LinkedHashMap<String, Object> columnMap) 
			throws DataCoreException{
		if(tableName == null || tableName.length() == 0) {
			return;
		}
		
		if(columnMap == null || columnMap.size() == 0) {
			return;
		}
		
		try (Connection conn = DriverManager.getConnection(url)) {
			StringBuilder sqlBuild = new StringBuilder("INSERT INTO ");
			List<Object> setList = new ArrayList<Object>();
			
			sqlBuild.append(tableName).append("(");
			for (Map.Entry<String, Object> column: columnMap.entrySet()) {
				sqlBuild.append(column.getKey()).append(",");
				setList.add(column.getValue());
			}
			sqlBuild.deleteCharAt(sqlBuild.lastIndexOf(","));
			sqlBuild.append(") VALUES (")
			.append(new String(new char[columnMap.size()-1]).replace("\0", "?,"))
			.append("?);");
			
			String insertSql = sqlBuild.toString();
			executeSql(url, insertSql, setList);
			
		} catch (SQLException e) {
			throw new DataCoreException(e);
		}
	}
	
	public static void executeUpdateSql(String url, final String tableName
			, LinkedHashMap<String, Object> updateMap
			, LinkedHashMap<String, Object> columnMap) throws DataCoreException{
		if(tableName == null || tableName.length() == 0) {
			return;
		}
		
		if(updateMap == null || updateMap.size() == 0) {
			return;
		}
		
		try (Connection conn = DriverManager.getConnection(url)) {
			StringBuilder sqlBuild = new StringBuilder("UPDATE ");
			List<Object> setList = new ArrayList<Object>();
			
			sqlBuild.append(tableName);
			sqlBuild.append(" SET ");
			
			for (Map.Entry<String, Object> column: updateMap.entrySet()) {
				sqlBuild.append(column.getKey()).append(" = ?");
				setList.add(column.getValue());
			}
			
			sqlBuild = setSql(tableName, columnMap, setList, sqlBuild);
			
			String sql = sqlBuild.toString();
			executeSql(url, sql, setList);
			
		} catch (SQLException e) {
			throw new DataCoreException(e);
		}
	}
	
	public static void executeDeleteSql(String url, final String tableName, LinkedHashMap<String, Object> columnMap) 
			throws DataCoreException{
		if(tableName == null || tableName.length() == 0) {
			return;
		}
		
		try (Connection conn = DriverManager.getConnection(url)) {
			StringBuilder sqlBuild = new StringBuilder("DELETE FROM ");
			List<Object> setList = new ArrayList<Object>();
			
			sqlBuild.append(tableName);
			
			sqlBuild = setSql(tableName, columnMap, setList, sqlBuild);

			String sql = sqlBuild.toString();
			executeSql(url, sql, setList);
			
		} catch (SQLException e) {
			throw new DataCoreException(e);
		}
	}
	
	private static StringBuilder setSql(final String tableName
			, LinkedHashMap<String, Object> columnMap
			, List<Object> setList
			, StringBuilder sqlBuild) throws DataCoreException{
		if(columnMap == null || columnMap.size() == 0) {
			throw new DataCoreException("Effect all data from " + tableName);
			
		} else {
			sqlBuild.append(" WHERE ");
			long index = 0;
			for (Map.Entry<String, Object> column: columnMap.entrySet()) {
				if(index++ > 0) {
					sqlBuild.append(" AND ");
				}
				sqlBuild.append(column.getKey()).append(" = ?");
				setList.add(column.getValue());
			}
			
		}
		
		sqlBuild.append(";");
		
		return sqlBuild;
	}
	
	private static void preparedStatementSetter(PreparedStatement preparedStatement, final List<Object> setList) throws SQLException{
		if(preparedStatement == null) {
			return;
		}
		
		if(setList == null) {
			return;
		}
		
		for (int idx = 0, count = 1;  idx < setList.size(); idx++) {
			Object setValue = setList.get(idx);
			
		
			if(setValue instanceof Boolean) {
				preparedStatement.setBoolean(count++, (Boolean)setValue);
			} else if(setValue instanceof Byte) {
				preparedStatement.setByte(count++, (Byte)setValue);
			} else if(setValue instanceof Short) {
				preparedStatement.setShort(count++, (Short)setValue);
			} else if(setValue instanceof Integer) {
				preparedStatement.setInt(count++, (Integer)setValue);
			} else if(setValue instanceof Long) {
				preparedStatement.setLong(count++, (Long)setValue);
			} else if(setValue instanceof Float) {
				preparedStatement.setFloat(count++, (Float)setValue);
			} else if(setValue instanceof Double) {
				preparedStatement.setDouble(count++, (Double)setValue);
			} else if(setValue instanceof BigDecimal) {
				preparedStatement.setBigDecimal(count++, (BigDecimal)setValue);
			} else if(setValue instanceof String) {
				preparedStatement.setString(count++, (String)setValue);
			} else if(setValue instanceof Byte[]) {
				preparedStatement.setBytes(count++, (byte[])setValue);
			} else if(setValue instanceof Date) {
				preparedStatement.setDate(count++, (Date)setValue);
			} else if(setValue instanceof Time) {
				preparedStatement.setTime(count++, (Time)setValue);
			} else if(setValue instanceof Timestamp) {
				preparedStatement.setTimestamp(count++, (Timestamp)setValue);
			} else {
				preparedStatement.setObject(count++, setValue);
			}
			/*
			void setNull(int parameterIndex, int sqlType) throws SQLException;\
		    void setAsciiStream(int parameterIndex, java.io.InputStream x, int length)
		            throws SQLException;\
		    void setBinaryStream(int parameterIndex, java.io.InputStream x,
		                         int length) throws SQLException;\
		    void clearParameters() throws SQLException;\
		    void setObject(int parameterIndex, Object x, int targetSqlType)
		      throws SQLException;\
		    void setObject(int parameterIndex, Object x) throws SQLException;\
			 */
		}
		
	}
	
    
    
	
	
	
	
	
	public static ResultSet select(String url, String sql) throws DataCoreException{
		if(sql == null || sql.isEmpty()) {
			throw new NullPointerException();
		}
		
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				Statement st = conn.createStatement();
				return st.executeQuery(sql);
			}
		} catch (SQLException e) {
			throw new DataCoreException(e);
			
		}
		return null;
	}
    
	
	public static String getTable(String url, String sql) throws DataCoreException{
		if(sql == null || sql.isEmpty()) {
			throw new NullPointerException();
		}
		
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				return outputResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DataCoreException(e);
			
		}
		return null;
	}
	
	private static String outputResultSet(ResultSet rs) throws SQLException {
		StringBuilder output = new StringBuilder(System.lineSeparator());
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		List<String[]> buffer = new ArrayList<String[]>();
		List<Integer> columnSizeList = new ArrayList<Integer>();
		int numberOfColumns = rsMetaData.getColumnCount();
		String [] nameRows = new String[numberOfColumns];
		
		for (int idx = 0; idx < numberOfColumns; idx++) {
			String columnName = rsMetaData.getColumnName(idx+1);
			
			String column = " " + columnName + " ";
			columnSizeList.add(column.length());
			
			nameRows[idx] = column;
		}
		buffer.add(nameRows);
		
		while (rs.next()) {
			String [] rows = new String[numberOfColumns];
			for (int idx = 0; idx < numberOfColumns; idx++) {
				String value = rs.getString(idx+1);
				rows[idx] = value;
				int columnMax = columnSizeList.get(idx);
				if(columnMax < value.length()) {
					columnSizeList.set(idx, value.length());
				}
			}
			buffer.add(rows);
		}
		
		for(int idx = 0 ; idx < buffer.size(); idx++) {
			String[] values = buffer.get(idx);
			for (int idy = 0; idy < values.length; idy++) {
				int maxSize = columnSizeList.get(idy);
				String value = values[idy];
				output.append(String.format(" %" +maxSize+ "s ", value));
			}
			output.append("\n");
		}

		
		return output.toString();

	}
	
}
