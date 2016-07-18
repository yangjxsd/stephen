package com.stephen.wenwen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyAndRenameFile {

	public static void main(String[] args) {
		BufferedWriter bw = null;
		try {
			String path = "E:\\sww\\2016涂鸦比赛作品";
			String copyPath = "E:\\sww_copy\\";
			File file = null;
			file = new File(path);
			bw = new BufferedWriter(new FileWriter(new File("E:\\log.txt")));
			if (file.isDirectory()) {
				File[] fileArray = file.listFiles();
				for (File f : fileArray) {
					if (f.isFile()) {
						String oldName = f.getName().substring(0,f.getName().lastIndexOf("."));
						String newName = oldName.split(" ")[0]+ f.getName().substring(f.getName().lastIndexOf("."));
						//nioTransferCopy(f, new File(copyPath + f.getName()));
						f.renameTo(new File(path + File.separator + newName));
						System.out.println(oldName.split(" ")[0] + "" + "");
						bw.write( oldName.split(" ")[0] + "		" + oldName);
						bw.newLine();
						bw.flush();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void nioTransferCopy(File source, File target) {
		try {
			if (!target.exists()) {
				target.createNewFile();
			}
			FileChannel in = null;
			FileChannel out = null;
			FileInputStream inStream = null;
			FileOutputStream outStream = null;
			try {
				inStream = new FileInputStream(source);
				outStream = new FileOutputStream(target);
				in = inStream.getChannel();
				out = outStream.getChannel();
				in.transferTo(0, in.size(), out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				inStream.close();
				in.close();
				outStream.close();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
