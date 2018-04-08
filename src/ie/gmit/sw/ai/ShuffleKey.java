package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ShuffleKey {
	private static final String initencKey = getParent();
	private String parent;
	private String child;
	private double logProbability;

	// shuffleKey 洗牌算法
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

	// Get initial key 获取初始密钥
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

	// Split into a 4-gram array 拆分为4-gram数组
	private static String[] formGrams(String text, int ng) {
		int len = text.length();
		String[] res = new String[len - ng + 1];
		for (int i = 0; i < len - ng + 1; i++) {
			res[i] = text.substring(i, i + ng);
		}
		return res;
	}
	// Traverse the cryptogram 4-gram, if it exists, take count and calculate the logarithmic probability.
	private double getLogProbability() { // 遍历密文4-gram，如果存在，取count并计算对数概率
		double tempSum = 0;
		this.child = shuffle(initencKey.toCharArray()); // Temporarily shuffled keys. 暂存洗牌后的密钥
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
	 *            Initialization temperature 初始化温度
	 * @param transitions
	 *            The number of iterations. 迭代次数
	 * @param step
	 *            Temperature drop rate. 温度下降率
	 * @param encMessage
	 *            Ciphertext 密文
	 * @return
	 */
	public String CiperBreaker(int temp, int transitions, int step, String encMessage) {
		System.out.println("");
		System.out.println("Simulated Annealing Algorithm Start............"); // 模拟降火算法开始
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
		// 模拟降火算法结束 密钥 对数相似度
		System.out.println(
				"Simulated Annealing Algorithm End----->[Keys=" + parent + "; Log Similarity=" + logProbability + "]");
		String enc = PlayFairs.decrypt(encMessage, parent);
		return enc;
	}

}
