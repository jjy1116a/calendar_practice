package calendar;

import java.util.Scanner;

public class Sum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력할 두 수를 입력하시오 : ");
		int a = sc.nextInt(), b = sc.nextInt();
		System.out.print("두 수의 합은 " + (a+b) + "입니다");
		sc.close();
	}
}
