package rsa;

import java.math.BigInteger;
import java.util.Scanner;

public class TestRSA {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			RSA rsa = new RSA();
			BigInteger[] primeNumber = rsa.getPrimeNumber();
			//step1:产生两个大素数
			BigInteger p = primeNumber[0];
			BigInteger q = primeNumber[1];
			System.out.println("1、生成两个大素数：p="+ p +" q=" + q);
			//step2.1:计算n
			BigInteger n = p.multiply(q);//n=p*q
			System.out.println("2、计算的n是："+n);
			//step2.2:计算sn
			BigInteger sn = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));//sn=(p-1)*(q-1)
			System.out.println("   计算的sn=(p-1)*(q-1)：sn=" + sn);
			//step3:随机选取e
			BigInteger e = rsa.getE(sn);//0<e<sn && e和 sn互素
			System.out.println("3、选取的e和sn互素：e=" + e);
			//step4:计算d
			BigInteger d = rsa.getD(sn, e).mod(sn);//d同时与n和sn互素
			System.out.println("4、计算d同时与n和sn互素：d=" + d);
			//step5:B将n和e作为公钥公开
			System.out.println("5、公钥：n=" + n + " e=" + e);
			//step6:用户A获取到公钥
			System.out.println("6、公钥以获取，发送数据");
			//step7:进行加密
			System.out.println("7、请输入要发送的明文:");
			Scanner scanner = new Scanner(System.in);
			String sm = scanner.nextLine();
			//step:若输入字符，则将字符串转化为数字数组后加密
			BigInteger m = StringProcess.stringToBigInteger(sm);

			BigInteger c = rsa.getC(m,e,n);//计算密文
			System.out.println("RSA加密后的密文是：" + c);
			//step8:进行解密
			BigInteger mm = rsa.getDecrypt(c,n,d);//进行解密
			System.out.println("8、解密后的的结果是：" + mm);
		} catch (Exception ex) {
			//TODO: handle exception
		}
	}
}