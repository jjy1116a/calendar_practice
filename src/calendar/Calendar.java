package calendar;

import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public int getMaxDaysOfMonth(int month) {
		return MAX_DAYS[month-1];
	}
	
	public void printSampleOfCalendar() {
		System.out.println("  일  월 화  수 목  금 토");
		System.out.println("---------------------");
		System.out.println("  1  2  3  4  5  6  7");
		System.out.println("  8  9 10 11 12 13 14");
		System.out.println(" 15 16 17 18 19 20 21");
		System.out.println(" 22 23 24 25 26 27 28");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Calendar cal = new Calendar();
		System.out.println("반복 횟수를 입력하세요");
		int repeat = sc.nextInt();
		int[] answer = new int[repeat];
		System.out.println("월을 입력하세요");
		for(int i = 0; i < repeat; i++) {
			answer[i] = sc.nextInt();
		}
		for(int i = 0; i < repeat; i++) {
			System.out.println(answer[i] + "월은 " + cal.getMaxDaysOfMonth(answer[i]) + "일 까지 있습니다.");
		}
		
		sc.close();
	}

}
