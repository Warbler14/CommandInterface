package com.lotus.jewel.data.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;

public class SqlUtil {

	public static List<String> parseSql(final String filePath) throws IOException {
		if(filePath == null) {
			throw new NullPointerException();
		}
		
		
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		
		List<String> sqlList = new ArrayList<String>();
		
		StringBuilder stringBuilder = new StringBuilder();
		String str;
		while ((str = reader.readLine()) != null) {
			
			while(true) {
				str = collect(str, sqlList, stringBuilder);
				if(str == null) {
					break;
				}
			}
			
		}
		reader.close();

		return sqlList;
	}
	
	private static String collect(String str, List<String> sqlList, StringBuilder stringBuilder) {
		String buffer = str.trim().replace("\\r\\n", "").replace("\\n", "");
		int endIndex = buffer.indexOf(";");
		if(endIndex < 0) {
			stringBuilder.append(buffer);
		} else {
			String sql = buffer.substring(0, endIndex+1);
			
			stringBuilder.append(sql);
			sqlList.add(stringBuilder.toString());
			stringBuilder.setLength(0);
			
			if(endIndex+1 < buffer.length()) {
				return str.substring(endIndex+1, str.length());
			}
		}
		return null;
		
	}
}
