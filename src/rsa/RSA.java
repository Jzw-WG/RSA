package rsa;
 
import java.math.BigInteger;
import java.util.Random;
 
//RSA算法
public class RSA {
	/**
	 * 随机产生两个大素数: res[0]、res[1]
	 * @return
	 */
	public BigInteger[] getPrimeNumber(){
		BigInteger p=null;
		BigInteger q=null;
		BigInteger[] res=new BigInteger[2];
		Random random = new Random();
	    p=BigInteger.probablePrime(64, random);//为了效率，此处设为64
	    q=BigInteger.probablePrime(64, random);
		res[0]=p;
		res[1]=q;
		return res;
	}
	/**
	 * 随机选取e
	 * 0<e<sn && e和 sn互素
	 * @param sn
	 * @return
	 */
	public BigInteger getE(BigInteger sn){
		BigInteger e = null;
		//说明:此处把产生的e位数-2，是防止 nextProbablePrime()方法产生的素数大于sn
		int length = sn.toString().length()-2;// length为随机数位数
		e=new BigInteger(sn.toString().subSequence(0, length-2).toString()).nextProbablePrime();
		return e;
	}
	/**
	 * 选取d
	 * d同时与n和sn互素
	 * @param n
	 * @param sn
	 * @return
	 */
	public BigInteger getD(BigInteger sn,BigInteger e){//Euclid算法
		BigInteger[] ret = new BigInteger[3];  
	    BigInteger u = BigInteger.valueOf(1), u1 = BigInteger.valueOf(0);  
	    BigInteger v = BigInteger.valueOf(0), v1 = BigInteger.valueOf(1);  
	    if (e.compareTo(sn) > 0) {  
	        BigInteger tem = sn;  
	        sn = e;  
	        e = tem;  
	    }  
	    while (e.compareTo(BigInteger.valueOf(0)) != 0) {  
	        BigInteger tq = sn.divide(e); // tq = sn / e  
	        BigInteger tu = u;  
	        u = u1;  
	        u1 = tu.subtract(tq.multiply(u1)); // u1 =tu - tq * u1  
	        BigInteger tv = v;  
	        v = v1;  
	        v1 = tv.subtract(tq.multiply(v1)); // v1 = tv - tq * v1  
	        BigInteger tsn = sn;  
	        sn = e;  
	        e = tsn.subtract(tq.multiply(e)); // e = tsn - tq * e  
	        ret[0] = u;  
	        ret[1] = v;  
	        ret[2] = sn;  
	    }  
		return ret[1];
	}
	/**
	 * 计算密文
	 * c=(m^e)%n
	 * @param m 明文
	 * @param e
	 * @param n
	 * @return
	 */
	public BigInteger getC(BigInteger m,BigInteger e,BigInteger n){
		BigInteger c=null;
		c=m.modPow(e, n);//返回其值为 (m^e  mod n)
		return c;
	}
	/**
	 * 计算解密
	 * c^d=m%n m为解密后的结果
	 * @param c 密文
	 * @param n 公钥
	 * @param d 私钥
	 * @return
	 */
	public BigInteger getDecrypt(BigInteger c,BigInteger n,BigInteger d){
		BigInteger m=null;
		m=c.modPow(d, n);//返回其值为 (c^d  mod n)
		return m;
	}
}