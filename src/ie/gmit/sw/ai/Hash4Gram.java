package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Hash4Gram {
	
	/**
	 * Store 4-gram file content. 存放4-gram文件内容
	 * key is gram, value is count. 
	 */
	private static final HashMap<String,Double> map = new HashMap<String,Double>(); 
	
	/**
	 * Calculate the total number of counts for the entire file. 计算整个文件的count总数
	 */
	private static double total;
	static{
		File file = new File("./4grams.txt"); // File Reader 读取文件
		InputStream in;
		try {
			in = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));  
	        String line = null;
	        while((line = br.readLine()) != null){
	           String[] str = line.split(" ");// Read each line with "" split.  读取每一行以" "拆分
	           total = total + Double.parseDouble(str[1]);  // Calculate the total number. 计算总数
	           map.put(str[0], Double.parseDouble(str[1])); 
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	public static double getTotal() {
		return total;
	}
	public static void setTotal(double total) {
		Hash4Gram.total = total;
	}
	public static HashMap<String, Double> getMap() {
		return map;
	}
	
	
}
