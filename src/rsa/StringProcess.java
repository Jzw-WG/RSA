package rsa;

import java.math.BigInteger;

public class StringProcess {
    /**
	 * 将字符串转化数字数组供RSA加密使用
     * @param str
	 * @return
	 */
	public static BigInteger stringToBigInteger(String str){
        try {
            Integer strLength = str.length();
            byte[] byteArray = new byte[strLength];
            char[] charArray = new char[strLength];
            
            byteArray = str.getBytes("UTF-8");

            for (int i = 0; i < strLength; i++) {
            
            }
    
            return BigInteger.valueOf(0);
        } catch (Exception e) {
            //TODO: handle exception
            return BigInteger.valueOf(0);
        }
        
        
	}
}