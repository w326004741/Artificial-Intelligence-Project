package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
public class PlayFairs {

    /**
     * �������ģ��ظ���ĸ�м��X�����ȷ�ż�����X
     * Process the plaintext, add X in the middle of repeated letters, 
     * and add X in the end for non-even numbers.
     * @param P Cipher text ����
     * @return Processed plaintext ����������
     */
    public static String dealP(String P){
        P=P.toUpperCase();//  Convert plain text to uppercase ������ת���ɴ�д
        P=P.replaceAll("[^A-Z]", "");// Remove all non-letter characters ȥ�����з���ĸ���ַ�       
        StringBuilder sb=new StringBuilder(P);     
        for(int i=1;i<sb.length();i=i+2){
            // A pair of plaintext letters, if they are duplicated, insert a padding character between the pair of plaintext letters
        	//һ��������ĸ������ظ����������������ĸ֮�����һ������ַ�
            if(sb.charAt(i)==sb.charAt(i-1)){
                sb.insert(i,'X');               
            }
        } 
        // If the length of the processed plaintext is not even, add the letter X to the end
        //����������������ĳ��ȷ�ż�������ں��������ĸx
        if(sb.length()%2!=0){
            sb.append('X');
        }
        String p=sb.toString();
        System.out.println("Processed plain text��"+p); // Processed plaintext
        return p;
    }

    /**
     * ������Կ����Jת����I����ȥ�ظ���ĸ
     * Process key, convert J to I, remove duplicate letters
     * @param K Keys ��Կ
     * @return Modified keyList<Character> �޸ĺ����ԿList<Character>
     */
    public static List<Character> dealK(String K){
        K=K.toUpperCase();//  Convert the key to uppercase ����Կת���ɴ�д
        K=K.replaceAll("[^A-Z]", "");// Remove all non-letter characters ȥ�����з���ĸ���ַ�    
        K=K.replaceAll("J","I");// convert J to I ��Jת����I
        List<Character> list=new ArrayList<Character>();
        char[] ch=K.toCharArray();
        int len=ch.length;
        for(int i=0;i<len;i++){
            // remove duplicate letters ��ȥ�ظ����ֵ���ĸ
            if(!list.contains(ch[i])){
                list.add(ch[i]);
            }
        }
        System.out.println("Processed key��"+list); // Processed key ��������Կ
        return list;
    }

    /**
     * ���ܫh����ĸ�������5��5�ľ����ڣ�ʣ�µĿռ佫���δ�����Ӣ����ĸ
     * ��a-z��˳����롣����I��J����ͬһ�֡�JOY -> IOY��
     * Add the letters of the key letters one by one to the 5x5 matrix, 
     * and the rest of the space will add unlettered English letters
     * Add in the order of a-z. ( Treat I and J as the same word
     * @param K Keys ��Կ
     * @return 5x5 Matrix 5*5����
     */
    public static char[][] matrix(String K){
        List<Character> key=dealK(K);
        // Add letters appearing in K �����K�г��ֵ���ĸ
        List<Character> list=new ArrayList<Character>(key);
        // Add the remaining letters in alphabetical order. ��Ӱ���ĸ��˳�������ʣ�����ĸ
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
        System.out.println("According to the key'"+K+"'Constructed matrix��"); // ������Կ K �����ľ���
        showMatrix(matrix);
        return matrix;
    } 

    /**
     * ��ӡ����
     * Print Matrix
     * @param matrix ����
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
     * ����playfair�㷨�����ĶԽ��м���
     * The plaintext pair is encrypted according to the playfair algorithm.
     * @param matrix ����
     * @param ch1 ��ĸ1
     * @param ch2 ��ĸ2
     * @return Ciphertext pairs ���Ķ�
     */
    public static String encrypt(char[][] matrix,char ch1,char ch2){     
        // Get the position of the plaintext pair in the matrix. ��ȡ���Ķ��ھ����е�λ��
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
        //Conduct a regulatory match to get a ciphertext pair. ���й���ƥ�䣬�õ����Ķ�               
        if(r1==r2){
            //The two letters of the letter pair are on the same line and the right letter is cut off. 
        	// ������ĸ�Ե�������ĸ��ͬһ�У����ȡ�ұߵ���ĸ
            sb.append(matrix[r1][(c1+1)%5]);
            sb.append(matrix[r1][(c2+1)%5]);
        }else if(c1==c2){
        	//The two letters of the plain letter pair are in the same column, then the lower one is cut off.
            //������ĸ�Ե�������ĸ��ͬһ�У����ȡ�·�����ĸ
            sb.append(matrix[(r1+1)%5][c1]);
            sb.append(matrix[(r2+1)%5][c1]);
        }else{
            //Two letters are randomly selected on the diagonal diagonal of the rectangle formed by plain letters. 
        	//Each letter in the plaintext pair will be replaced with the letter in the same column as the other letter.
        	//������ĸ���γɵľ��ζԽ����ϵ�������ĸ������ѡ�����ַ��� 
        	//���Ķ��е�ÿһ����ĸ��������ͬ�У�������һ����ĸͬ�е���ĸ����
            sb.append(matrix[r1][c2]);
            sb.append(matrix[r2][c1]);

        }
        sb.append(' ');
        return sb.toString();
    }

    /**
     * Encrypt plaintext
     * �����Ľ��м���
     * @param P plaintext ����
     * @param K Keys ��Կ
     * @return Ciphertext ����
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
     * ����playfair�㷨�����ĶԽ��н���
     * The ciphertext pair is decrypted according to the playfair algorithm.
     * @param matrix
     * @param ch1 ��ĸ1
     * @param ch2 ��ĸ2
     * @return plaintext ���Ķ�
     */
    public static String decrypt(char[][] matrix,char ch1,char ch2){     
        //Get the position of the ciphertext pair in the matrix. ��ȡ���Ķ��ھ����е�λ��
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
        // Make a regulatory match and get the plaintext pair. ���й���ƥ�䣬�õ����Ķ�               
        if(r1==r2){
            //If the two letters of a cipher text pair are on the same line, the left letter is cut off. 
        	// ������ĸ�Ե�������ĸ��ͬһ�У����ȡ��ߵ���ĸ
            sb.append(matrix[r1][(c1-1+5)%5]);
            sb.append(matrix[r1][(c2-1+5)%5]);
        }else if(c1==c2){
        	// The two letters of the cipher text pair are in the same column, then the upper letter is cut off.
            //������ĸ�Ե�������ĸ��ͬһ�У����ȡ�Ϸ�����ĸ
            sb.append(matrix[(r1-1+5)%5][c1]);
            sb.append(matrix[(r2-1+5)%5][c1]);
        }else{
        	// The two letters on the diagonal diagonal of the rectangle formed by the cipher text are arbitrarily selected in two directions.
            //������ĸ���γɵľ��ζԽ����ϵ�������ĸ������ѡ�����ַ���            
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
     * �����Ľ��н���
     * @param C ����
     * @param K ��Կ
     * @return ����
     */
    public static String decrypt(String C,String K){
        C=C.toUpperCase();
        C=C.replaceAll("[^A-Z]", "");//Remove all non-letter characters. ȥ�����з���ĸ���ַ�
        char[] ch=C.toCharArray();
        char[][] matrix=matrix(K);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ch.length;i=i+2){
            sb.append(decrypt(matrix,ch[i],ch[i+1]));
        }
        return sb.toString();
    }

}