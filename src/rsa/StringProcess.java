package rsa;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class StringProcess {
    public static final Integer BIT_NUM = 8;
    /**
	 * 将字符串转化整数供RSA加密使用
     * @param str
	 * @return
	 */
	public static BigInteger stringToBigInteger(String str){
        BigInteger result = BigInteger.valueOf(0);
        try {
            Integer strLength = str.length();
            byte[] byteArray = new byte[strLength];

            byteArray = str.getBytes("UTF-8");
            String binaryStr = "";
            String oneStr = "";
            for (int i = 0; i < strLength; i++) {
                oneStr = StringUtils.leftPad(Integer.toBinaryString(byteArray[i]&0xff), 8, "0");
                binaryStr = binaryStr + oneStr;
            }
            BigInteger bigIntegerStr = new BigInteger(binaryStr, 2);
            result = bigIntegerStr;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }        
    }
    
    /**
	 * 将RSA解密后的整数转化字符串
     * @param bigInteger
	 * @return
	 */
	public static String bigIntegerToString(BigInteger bigInteger){
        String result = "";
        String binaryStr = bigInteger.toString(2);
        Integer binaryStrLength = binaryStr.length();
        if (binaryStrLength%BIT_NUM != 0) {
            binaryStrLength = binaryStrLength + (BIT_NUM - binaryStrLength%BIT_NUM);
        }
        binaryStr = StringUtils.leftPad(binaryStr, binaryStrLength, "0");
        Integer strLength = binaryStrLength/BIT_NUM;
        byte[] byteArray = new byte[strLength];
        String oneStr = "";
        for (int i = 0; i < strLength; i++) {
            oneStr = binaryStr.substring(i*BIT_NUM, i*BIT_NUM + BIT_NUM);
            byteArray[i] = Byte.parseByte(oneStr, 2);
        }
        result = new String(byteArray);
        return result;
    }
}