package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileReader {

	/**
	 * File Reader(读取文件内容)
	 * InputStreamReader(),FileInputStream(file), BufferedReader(), BufferedReader().readline()
	 * 1. 用 InputStreamReader()方法去解读刚刚装进来的文件数据
	 * 2. new FileInputStream(file)信息已经读进内存当中
	 * 3. 输出，转换成IO可以识别的数据。
	 * 4. 使用调用字节码读取的方法BufferedReader(),
	 * 4. 同时使用BufferedReader()的readline(）方法读取txt文件中的每一行数据
	 * @param filePath
	 */
	public static void fileReader(String filePath) {
		try {
			File file = new File(filePath); // 文件句柄
			if (file.isFile() && file.exists()) {
				InputStreamReader isr = new InputStreamReader( //用 InputStreamReader()方法去解读刚刚装进来的文件数据
						new FileInputStream(file)); // new FileInputStream(file)信息已经读进内存当中
				BufferedReader br = new BufferedReader(isr);// 输出，转换成IO可以识别的数据。
															// 使用调用字节码读取的方法BufferedReader(),
															// 同时使用BufferedReader()的readline(）方法读取txt文件中的每一行数据
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					System.out.println(lineTxt);
				}
				br.close();
			} else {
				System.out.println("File No Exist！");
			}
		} catch (Exception e) {
			System.out.println("File Read Error!");
		}
	}

	public static void main(String[] args) {
		String filePath = "4grams.txt";
		fileReader(filePath);
	}
}
