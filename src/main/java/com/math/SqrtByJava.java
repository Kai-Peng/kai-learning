package com.math;

import java.util.Date;

public class SqrtByJava {

	// 二分法
	public static double sqrt1(double t, Double precise) {
		double low = 0, high = t, middle, squre, prec = precise != null ? precise
				: 1e-7;
		if (t < 0) {
			throw new RuntimeException(
					"Negative number cannot have a sqrt root.");
		}

		int k = 0;
		while (high - low > prec) {
			middle = (low + high) / 2;
			squre = middle * middle;

			if (squre > t) {
				high = middle;
			} else if (squre < t) {
				low = middle;
			} else {
				System.out.println("sqrt1迭代次数："+k);
				return middle;
			}
			k++;
		}

		System.out.println("sqrt1迭代次数："+k);
		return (low + high) / 2;
	}

	//牛顿迭代法
	public static double sqrt2(double t, Double precise) {
		double x0 = t, x1, differ, prec = precise != null ? precise : 1e-7;

		int k = 0;
		while (true) {
			x1 = (x0 * x0 + t) / (2 * x0);
			differ = x1 * x1 - t;

			if (differ <= prec && differ >= -prec) {
				System.out.println("sqrt2迭代次数："+k);
				return x1;
			}
			x0 = x1;
			
			k++;
		}
	}

	//第三解法
	public static double sqrt3(double m) {
		if (m == 0) {
			return 0;
		}

		double i = 0;
		double x1, x2 = 0;
		int k = 0;
		while ((i * i) <= m) {
			i += 0.1;
			k++;
		}
		x1 = i;
		for (int j = 0; j < 10; j++) {
			x2 = m;
			x2 /= x1;
			x2 += x1;
			x2 /= 2;
			x1 = x2;
			
			k++;
		}
		
		System.out.println("sqrt3迭代次数："+k);
		return x2;
	}

	
	public static void main(String[] args) {
		double testNum = 5.000000000e10;
		
		//解法1测试
		long starttime = new Date().getTime();
		double result1 = SqrtByJava.sqrt1(testNum, null);
		System.out.println("result1=" + result1 + ",wastetime1="
				+ (new Date().getTime() - starttime));
		
		//解法2测试
		/*starttime = new Date().getTime();
		result1 = SqrtByJava.sqrt2(testNum, null);
		System.out.println("result2=" + result1 + ",wastetime2="
				+ (new Date().getTime() - starttime));
		*/
		//解法3测试
		starttime = new Date().getTime();
		result1 = SqrtByJava.sqrt3(testNum);
		System.out.println("result3=" + result1 + ",wastetime3="
				+ (new Date().getTime() - starttime));
		
		//java api方法测试
		starttime = new Date().getTime();
		result1 = Math.sqrt(testNum);
		System.out.println("result4=" + result1 + ",wastetime4="
				+ (new Date().getTime() - starttime));
	}

}
