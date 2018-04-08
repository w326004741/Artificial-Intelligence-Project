package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CipherBreaker {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ShuffleKey cb = new ShuffleKey();
		System.out.println("=============================Using Simulated Annealing to Break a Playfair Cipher=============================");
		System.out.println("///                                                                                                        ///");
		System.out.println("=======================================G00329371 Weichen Wang==================================================");
		System.out.println("Please enter the initial temperature:"); // temp �������ʼ�¶�
		int temp = sc.nextInt();
		System.out.println("Please Enter the number of iterations transitions:"); // transitions ���������ת��������
		int transitions = sc.nextInt();
		System.out.println("Please enter the temperature drop rate step:"); // step �������¶��½��ʲ���
		int step = sc.nextInt();
		System.err.println("Please enter the cipher text file path:"); // cipher text file path �����������ļ�·��
		// FileReader
		String encMessage = sc.next();
		File file = new File(encMessage);
		InputStream in;
		StringBuilder sb = null;
		try {
			in = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));  
	        sb = new StringBuilder();  
	        String line = null;
	        while((line = br.readLine()) != null){
	        	sb.append(line);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		System.out.println(sb.toString());
		String message = cb.CiperBreaker(temp, transitions, step, sb.toString());
		System.out.println("Plain text----->"+message.replace(" ", ""));
	}
	
}
