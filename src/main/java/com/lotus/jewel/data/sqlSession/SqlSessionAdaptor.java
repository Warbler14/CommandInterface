package com.lotus.jewel.data.sqlSession;

import org.apache.ibatis.session.SqlSession;

public interface SqlSessionAdaptor {
	
	public SqlSession getSqlSession (String configuration);
	

}
