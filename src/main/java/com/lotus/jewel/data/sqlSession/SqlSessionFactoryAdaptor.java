package com.lotus.jewel.data.sqlSession;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlSessionFactoryAdaptor implements SqlSessionAdaptor{

	@Override
	public SqlSession getSqlSession(String configuration) {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(configuration);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);
		
		return factory.openSession(true);
	}
	
}