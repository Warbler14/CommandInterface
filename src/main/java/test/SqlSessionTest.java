package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lotus.jewel.data.sqlSession.SqlSessionAdaptor;
import com.lotus.jewel.data.sqlSession.SqlSessionFactoryAdaptor;

import test.model.TestModel;

public class SqlSessionTest {

	//TODO 조회 : 조회 파라메터 추가건, 수정, 추가, 삭제 기능 구현 !

	public static void selectTestAll() {
		
		SqlSessionAdaptor sqlSessionAdaptor = new SqlSessionFactoryAdaptor();
		SqlSession session = sqlSessionAdaptor.getSqlSession("ibatis/configuration.xml");

		List<TestModel> resultList = session.selectList("test.mapper.testMapper.selectAll");
		
		for (TestModel testModel : resultList) {
			System.out.println(testModel.toString());
		}
		

		if (session != null) {
			session.close();
		}

	}
	
	public static void main(String[] args) {
		selectTestAll();
		
	}
}
