package test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.lotus.jewel.data.util.SqlUtil;

public class SqlFileParseTest {

	public static void main(String[] args) {
		
		
		
		try {
			URL fileUrl = ClassLoader.getSystemResource("sql/test.sql");
			System.out.println( "fileUrl : " + fileUrl );
			
			File fNm = new File( fileUrl.getFile() );
			List<String> sqlList = SqlUtil.parseSql(fNm.getAbsolutePath());
			
			for (String sql : sqlList) {
				System.out.println( sql );
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
