package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
public class PlayFairs {

    /**
     * 处理明文，重复字母中间插X，长度非偶数添加X
     * Process the plaintext, add X in the middle of repeated letters, 
     * and add X in the end for non-even numbers.
     * @param P Cipher text 密文
     * @return Processed plaintext 处理后的明文
     */
    public static String dealP(String P){
        P=P.toUpperCase();//  Convert plain text to uppercase 将明文转换成大写
        P=P.replaceAll("[^A-Z]", "");// Remove all non-letter characters 去除所有非字母的字符       
        StringBuilder sb=new StringBuilder(P);     
        for(int i=1;i<sb.length();i=i+2){
            // A pair of plaintext letters, if they are duplicated, insert a padding character between the pair of plaintext letters
        	//一对明文字母如果是重复的则在这对明文字母之间插入一个填充字符
            if(sb.charAt(i)==sb.charAt(i-1)){
                sb.insert(i,'X');               
            }
        } 
        // If the length of the processed plaintext is not even, add the letter X to the end
        //如果经过处理后的明文长度非偶数，则在后面加上字母x
        if(sb.length()%2!=0){
            sb.append('X');
        }
        String p=sb.toString();
        System.out.println("Processed plain text："+p); // Processed plaintext
        return p;
    }

    /**
     * 处理密钥，将J转换成I，除去重复字母
     * Process key, convert J to I, remove duplicate letters
     * @param K Keys 密钥
     * @return Modified keyList<Character> 修改后的密钥List<Character>
     */
    public static List<Character> dealK(String K){
        K=K.toUpperCase();//  Convert the key to uppercase 将密钥转换成大写
        K=K.replaceAll("[^A-Z]", "");// Remove all non-letter characters 去除所有非字母的字符    
        K=K.replaceAll("J","I");// convert J to I 将J转换成I
        List<Character> list=new ArrayList<Character>();
        char[] ch=K.toCharArray();
        int len=ch.length;
        for(int i=0;i<len;i++){
            // remove duplicate letters 除去重复出现的字母
            if(!list.contains(ch[i])){
                list.add(ch[i]);
            }
        }
        System.out.println("Processed key："+list); // Processed key 处理后的密钥
        return list;
    }

    /**
     * 将密玥的字母逐个加入5×5的矩阵内，剩下的空间将添加未加入的英文字母
     * 依a-z的顺序加入。（将I和J视作同一字。JOY -> IOY）
     * Add the letters of the key letters one by one to the 5x5 matrix, 
     * and the rest of the space will add unlettered English letters
     * Add in the order of a-z. ( Treat I and J as the same word
     * @param K Keys 密钥
     * @return 5x5 Matrix 5*5矩阵
     */
    public static char[][] matrix(String K){
        List<Character> key=dealK(K);
        // Add letters appearing in K 添加在K中出现的字母
        List<Character> list=new ArrayList<Character>(key);
        // Add the remaining letters in alphabetical order. 添加按字母表顺序排序的剩余的字母
        for(char ch='A';ch<='Z';ch++){
            if(ch!='J'&&!list.contains(ch)){
                list.add(ch);
            }
        }
        char[][] matrix=new char[5][5];
        int count=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                matrix[i][j]=list.get(count++);
            }
        }
        System.out.println("According to the key'"+K+"'Constructed matrix："); // 根据密钥 K 构建的矩阵
        showMatrix(matrix);
        return matrix;
    } 

    /**
     * 打印矩阵
     * Print Matrix
     * @param matrix 矩阵
     */
    public static void showMatrix(char[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 根据playfair算法对明文对进行加密
     * The plaintext pair is encrypted according to the playfair algorithm.
     * @param matrix 矩阵
     * @param ch1 字母1
     * @param ch2 字母2
     * @return Ciphertext pairs 密文对
     */
    public static String encrypt(char[][] matrix,char ch1,char ch2){     
        // Get the position of the plaintext pair in the matrix. 获取明文对在矩阵中的位置
        int r1=0,c1=0,r2=0,c2=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(ch1==matrix[i][j]){
                    r1=i;
                    c1=j;
                }
                if(ch2==matrix[i][j]){
                    r2=i;
                    c2=j;
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        //Conduct a regulatory match to get a ciphertext pair. 进行规制匹配，得到密文对               
        if(r1==r2){
            //The two letters of the letter pair are on the same line and the right letter is cut off. 
        	// 明文字母对的两个字母在同一行，则截取右边的字母
            sb.append(matrix[r1][(c1+1)%5]);
            sb.append(matrix[r1][(c2+1)%5]);
        }else if(c1==c2){
        	//The two letters of the plain letter pair are in the same column, then the lower one is cut off.
            //明文字母对的两个字母在同一列，则截取下方的字母
            sb.append(matrix[(r1+1)%5][c1]);
            sb.append(matrix[(r2+1)%5][c1]);
        }else{
            //Two letters are randomly selected on the diagonal diagonal of the rectangle formed by plain letters. 
        	//Each letter in the plaintext pair will be replaced with the letter in the same column as the other letter.
        	//明文字母所形成的矩形对角线上的两个字母，任意选择两种方向 
        	//明文对中的每一个字母将由与其同行，且与另一个字母同列的字母代替
            sb.append(matrix[r1][c2]);
            sb.append(matrix[r2][c1]);

        }
        sb.append(' ');
        return sb.toString();
    }

    /**
     * Encrypt plaintext
     * 对明文进行加密
     * @param P plaintext 明文
     * @param K Keys 密钥
     * @return Ciphertext 密文
     */
    public static String encrypt(String P,String K){       
        char[] ch=dealP(P).toCharArray();       
        char[][] matrix=matrix(K);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ch.length;i=i+2){
            sb.append(encrypt(matrix,ch[i],ch[i+1]));
        }
        return sb.toString();
    }

    /**
     * 根据playfair算法对密文对进行解密
     * The ciphertext pair is decrypted according to the playfair algorithm.
     * @param matrix
     * @param ch1 字母1
     * @param ch2 字母2
     * @return plaintext 明文对
     */
    public static String decrypt(char[][] matrix,char ch1,char ch2){     
        //Get the position of the ciphertext pair in the matrix. 获取密文对在矩阵中的位置
        int r1=0,c1=0,r2=0,c2=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(ch1==matrix[i][j]){
                    r1=i;
                    c1=j;
                }
                if(ch2==matrix[i][j]){
                    r2=i;
                    c2=j;
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        // Make a regulatory match and get the plaintext pair. 进行规制匹配，得到明文对               
        if(r1==r2){
            //If the two letters of a cipher text pair are on the same line, the left letter is cut off. 
        	// 密文字母对的两个字母在同一行，则截取左边的字母
            sb.append(matrix[r1][(c1-1+5)%5]);
            sb.append(matrix[r1][(c2-1+5)%5]);
        }else if(c1==c2){
        	// The two letters of the cipher text pair are in the same column, then the upper letter is cut off.
            //密文字母对的两个字母在同一列，则截取上方的字母
            sb.append(matrix[(r1-1+5)%5][c1]);
            sb.append(matrix[(r2-1+5)%5][c1]);
        }else{
        	// The two letters on the diagonal diagonal of the rectangle formed by the cipher text are arbitrarily selected in two directions.
            //密文字母所形成的矩形对角线上的两个字母，任意选择两种方向            
            sb.append(matrix[r1][c2]);
            sb.append(matrix[r2][c1]);
            //sb.append(matrix[r2][c1]);
            //sb.append(matrix[r1][c2]);
        }
        sb.append(' ');
        return sb.toString();
    }

    /**
     * Decrypt the ciphertext.
     * 对密文进行解密
     * @param C 密文
     * @param K 密钥
     * @return 明文
     */
    public static String decrypt(String C,String K){
        C=C.toUpperCase();
        C=C.replaceAll("[^A-Z]", "");//Remove all non-letter characters. 去除所有非字母的字符
        char[] ch=C.toCharArray();
        char[][] matrix=matrix(K);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ch.length;i=i+2){
            sb.append(decrypt(matrix,ch[i],ch[i+1]));
        }
        return sb.toString();
    }

}