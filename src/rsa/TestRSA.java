package rsa;

import java.math.BigInteger;
import java.util.Scanner;

public class TestRSA {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
		RSA rsa=new RSA();
		BigInteger[] primeNumber = rsa.getPrimeNumber();
		//step1:产生两个大素数
		BigInteger p=primeNumber[0];
		BigInteger q=primeNumber[1];
		System.out.println("1、B产生的两个大素数是(保密)：p="+p+" q="+q);
		//step2.1:计算n
		BigInteger n=p.multiply(q);//n=p*q
		System.out.println("2、计算的n是："+n);
		//step2.2:计算sn
		BigInteger sn=(p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));//sn=(p-1)*(q-1)
		System.out.println("   计算的sn是："+sn);
		//step3:随机选取e
		BigInteger e=rsa.getE(sn);//0<e<sn && e和 sn互素
		System.out.println("3、选取的e是："+e);
		//step4:计算d
		BigInteger d=rsa.getD(sn, e).mod(sn);//d同时与n和sn互素
		System.out.println("4、计算的d是："+d);
		//step5:B将n和e作为公钥公开
		System.out.println("5、公钥：n="+n+" e="+e);
		//step6:用户A获取到公钥
		System.out.println("6、用户A已经获取到公钥了，可以开始发数据了...");
		//step7:进行加密
		System.out.println("7、请输入要发送的明文（仅数字）:");
		Scanner scanner=new Scanner(System.in);
		BigInteger m=new BigInteger(scanner.next());
		BigInteger c=rsa.getC(m,e,n);//计算密文
		System.out.println("加密后的密文是："+c);
		//step8:进行解密
		BigInteger mm=rsa.getDecrypt(c,n,d);//进行解密
		System.out.println("8、解密后的的结果是："+mm);
	}
}