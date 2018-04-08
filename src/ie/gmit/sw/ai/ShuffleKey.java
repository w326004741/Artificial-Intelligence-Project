package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ShuffleKey {
	private static final String initencKey = getParent();
	private String parent;
	private String child;
	private double logProbability;

	// shuffleKey ϴ���㷨
	private String shuffle(char[] key) {
		int index;
		char temp;
		Random random = new Random();
		for (int i = key.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = key[index];
			key[index] = key[i];
			key[i] = temp;
		}
		return String.valueOf(key);
	}

	// Get initial key ��ȡ��ʼ��Կ
	private static String getParent() {
		String randomKey = "QWERTYUIOPLKJHGFDSAZXCVBNM";
		Random rd = new Random();
		int m = 0;
		String n = "";
		for (int i = 0; i < 25; i++) {
			m = rd.nextInt(26);
			n = n + randomKey.charAt(m);
		}
		return n;
	}

	// Split into a 4-gram array ���Ϊ4-gram����
	private static String[] formGrams(String text, int ng) {
		int len = text.length();
		String[] res = new String[len - ng + 1];
		for (int i = 0; i < len - ng + 1; i++) {
			res[i] = text.substring(i, i + ng);
		}
		return res;
	}
	// Traverse the cryptogram 4-gram, if it exists, take count and calculate the logarithmic probability.
	private double getLogProbability() { // ��������4-gram��������ڣ�ȡcount�������������
		double tempSum = 0;
		this.child = shuffle(initencKey.toCharArray()); // Temporarily shuffled keys. �ݴ�ϴ�ƺ����Կ
		// System.out.println("child == " + child);
		String[] fourgrams = formGrams(child, 4);
		for (String key : fourgrams) {
			if (Hash4Gram.getMap().keySet().contains(key)) {
				double tempValue = Math.log10(Hash4Gram.getMap().get(key) / Hash4Gram.getTotal());
				// System.out.println("tempValue ==" + tempValue);
				tempSum += tempValue;
			}
		}
		return tempSum;
	}

	/**
	 * 
	 * 
	 * @param temp 
	 *            Initialization temperature ��ʼ���¶�
	 * @param transitions
	 *            The number of iterations. ��������
	 * @param step
	 *            Temperature drop rate. �¶��½���
	 * @param encMessage
	 *            Ciphertext ����
	 * @return
	 */
	public String CiperBreaker(int temp, int transitions, int step, String encMessage) {
		System.out.println("");
		System.out.println("Simulated Annealing Algorithm Start............"); // ģ�⽵���㷨��ʼ
		logProbability = getLogProbability();
		// System.out.println(logProbability);
		this.parent = child;
		for (int i = temp; i > 0; i -= step) {
			for (int j = transitions; j > 0; j--) {
				double fitness = getLogProbability();
				double delta = fitness - this.logProbability;
				if (delta > 0) {
					this.parent = child;
					this.logProbability = fitness;
				} else if (delta < 0) {
					double p = 1 / (1 + Math.exp(-delta / temp));
					if (Math.random() < p) {
						this.parent = child;
						this.logProbability = fitness;
					}
				}
			}
		}
		// ģ�⽵���㷨���� ��Կ �������ƶ�
		System.out.println(
				"Simulated Annealing Algorithm End----->[Keys=" + parent + "; Log Similarity=" + logProbability + "]");
		String enc = PlayFairs.decrypt(encMessage, parent);
		return enc;
	}

}
