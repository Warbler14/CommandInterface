package test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.sqlite.JDBC;

import com.lotus.jewel.data.exception.DataCoreException;
import com.lotus.jewel.data.sqliteAdaptor.SQLitePreparedStatementAdaptor;

public class SQLitePreparedStatementAdaptorTest extends SQLitePreparedStatementAdaptor{

	
	public static void createTableTest(String url) throws DataCoreException {
		String createSql = "CREATE TABLE test (id INTEGER, name VARCHAR(255), address VARCHAR(255), city VARCHAR(255), state CHAR(2), zip CHAR(10));";
		executeSql(url, createSql);
	}
	
	public static ResultSet select(String url, String sql) {
		return select(url, sql);
	}
	
	public static String selectTest(String url) throws DataCoreException {
		String result = getTable(url, "select * from test");
		//logger.debug(result);
		System.out.println(result);
		return result;
	}
	
	public static void insertTest(String url, int seq) throws DataCoreException {
		String insertSql = "INSERT INTO test(id, name, address, city, state, zip) values ("+seq+", 'testName"+seq+"', 'testAddr"+seq+"', 'testCity"+seq+"', '"+seq+"', '0000');";
		executeSql(url, insertSql);
	}
	
	public static void insertTestBySetList(String url, int seq) throws DataCoreException {
		String insertSql = "INSERT INTO test(id, name, address, city, state, zip) values (?, ?, ?, ?, ?, ?);";
		
		List<Object> setList = new ArrayList<Object>();
		setList.add(seq);
		setList.add("testName" + seq);
		setList.add("testAddr" + seq);
		setList.add("testCity" + seq);
		setList.add(String.valueOf(seq));
		setList.add("0000");
		
		executeSql(url, insertSql, setList);
	}
	
	public static void insertTestByLinkendMap(String url, int seq) throws DataCoreException {
		String tableName = "test";
		LinkedHashMap<String, Object> columnMap = new LinkedHashMap<String, Object>();
		columnMap.put("id", seq);
		columnMap.put("name", "testName" + seq);
		columnMap.put("address", "testName" + seq);
		columnMap.put("city", "testName" + seq);
		columnMap.put("state", String.valueOf(seq));
		columnMap.put("zip", "0000");
		
		executeInsertSql(url, tableName, columnMap);
	}
	
	public static void updateTestByLinkendMap(String url, int seq) throws DataCoreException {
		String tableName = "test";
		LinkedHashMap<String, Object> updateMap = new LinkedHashMap<String, Object>();
		updateMap.put("zip", "@@@@");
		
		LinkedHashMap<String, Object> columnMap = new LinkedHashMap<String, Object>();
		columnMap.put("id", seq);
		
		executeUpdateSql(url, tableName, updateMap, columnMap);
	}
	
	public static void deleteTestByLinkendMap(String url, int seq) throws DataCoreException {
		String tableName = "test";
		LinkedHashMap<String, Object> columnMap = new LinkedHashMap<String, Object>();
		columnMap.put("id", seq);
		columnMap.put("name", "testName" + seq);
		
		executeDeleteSql(url, tableName, columnMap);
	}
	
	//TODO 조회 : 
	//preparedStatement 에 파라메터 분리하여 주입
	//조인 쿼리 테스트
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws DataCoreException {
		String dbFilePath = "./test.db";
		String url = JDBC.PREFIX + dbFilePath;
		
		
//		insertTest(url);
		selectTest(url);
		
	}
}