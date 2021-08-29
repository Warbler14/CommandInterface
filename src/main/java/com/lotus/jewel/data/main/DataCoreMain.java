package com.lotus.jewel.data.main;



import java.io.File;

import org.sqlite.JDBC;

import test.SQLitePreparedStatementAdaptorTest;
import test.SqlSessionTest;

public class DataCoreMain {

	//TODO 테이블 생성을 위한 일반 기
	// curd 일반 기능 테스트
	// junit 을 활용한 테스트
	// 이 프로젝트를 라이브러리로 만들고 다른 프로젝트에서 사
	
	
	
	public static void main(String[] args) {
//		Configuration configuration;
//		try {
//			configuration = new PropertiesConfiguration("config/data.properties");
//			String info = configuration.getString("info");
//			System.out.println(info);
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//		}
		
		
		String dbFilePath = "./test.db";
		
		File file = new File(dbFilePath);
		if(file.exists()) {
			file.delete();
		}
		
		String url = JDBC.PREFIX + dbFilePath;
		
		try {
			
			SQLitePreparedStatementAdaptorTest.createTableTest(url);
			SQLitePreparedStatementAdaptorTest.insertTest(url, 1);
			SQLitePreparedStatementAdaptorTest.insertTestBySetList(url, 2);
			SQLitePreparedStatementAdaptorTest.insertTestByLinkendMap(url, 3);
			SQLitePreparedStatementAdaptorTest.updateTestByLinkendMap(url, 3);
			
			SQLitePreparedStatementAdaptorTest.selectTest(url);
			
			SQLitePreparedStatementAdaptorTest.deleteTestByLinkendMap(url, 1);
			
			SqlSessionTest.selectTestAll();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
