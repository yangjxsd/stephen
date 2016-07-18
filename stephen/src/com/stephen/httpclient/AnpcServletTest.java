package com.stephen.httpclient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

import com.stephen.util.FileReadUtil;


public class AnpcServletTest {

	public static void main(String[] args) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://localhost:8080/IntergrateMgtInterface/ReceiveIcbcServlet");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000); // 连接时间为5秒
			conn.setReadTimeout(300000); // 返回数据时间为5分钟

			
			String content = FileReadUtil.readFromFile("C:\\Users\\YANGJINXING\\Desktop\\NJData\\ValueTable\\20150415\\363011bd-cd96-4117-880a-927f096ea5bb.xml");
			byte[] b = GzipData(content.getBytes());
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(("sendData=" + new String(b,"GBK")));
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuilder val = new StringBuilder(); 
			while( (line = br.readLine())!=null){
				val.append(line);
			}
			System.out.println(val);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
	}
	
	
	  public static byte[] GzipData(byte[] data)
	  {
	    byte[] b = (byte[])null;
	    try {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      GZIPOutputStream gzip = new GZIPOutputStream(bos);
	      gzip.write(data);
	      gzip.finish();
	      gzip.close();
	      b = bos.toByteArray();
	      bos.close();
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return b;
	  }
	
	
	
}
