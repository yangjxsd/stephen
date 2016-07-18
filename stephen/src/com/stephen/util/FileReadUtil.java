package com.stephen.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadUtil {

	
	public static String readFromFile(String fileName){
		StringBuilder content = new StringBuilder();
		BufferedReader br = null;
		try{
			File file = new File(fileName);
			br = new BufferedReader(new FileReader(file));
			String line ;
			while( (line = br.readLine())!=null){
				content.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content.toString();
	}
}
