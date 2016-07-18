package com.stephen.string;

import org.junit.Test;

public class SplitTest {

	
	@Test
	public void split(){
		String str = "1;2;3;";
		String[] array = str.split(";");
		for(String s : array){
			System.out.println(s);
		}
	}
}
