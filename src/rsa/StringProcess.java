package rsa;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class StringProcess {
    public final Integer BIT_NUM = 8;
    /**
	 * 将字符串转化数字数组供RSA加密使用
     * @param str
	 * @return
	 */
	public static BigInteger stringToBigInteger(String str){
        BigInteger result = BigInteger.valueOf(0);
        try {
            Integer strLength = str.length();
            byte[] byteArray = new byte[strLength];
            char[] charArray = new char[strLength];

            byteArray = str.getBytes("UTF-8");
            String binaryStr = "";
            String oneStr = "";
            for (int i = 0; i < strLength; i++) {
                oneStr = StringUtils.leftPad("0", 8, String.format("%08s", Integer.toBinaryString(byteArray[i]&0xff)));
                binaryStr = binaryStr + oneStr;
            }
            
            Integer integerStr = Integer.parseInt(binaryStr);
            result = BigInteger.valueOf((long) integerStr);
            return result;
        } catch (Exception e) {
            //TODO: handle exception
            return result;
        }
        
        
	}
}