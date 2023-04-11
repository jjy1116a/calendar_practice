package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year))
			return LEAP_MAX_DAYS[month - 1];
		else
			return MAX_DAYS[month - 1];
	}

	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public int getWeekDay(int year, int month) {
		int count = 4;
		boolean leap;
		for (int i = 1970; i < year; i++) {
			leap = isLeapYear(i);
			if (leap) {
				count += 366;
			} else {
				count += 365;
			}
		}
		leap = isLeapYear(year);
		for (int i = 1; i < month; i++) {
			if (leap) {
				count += LEAP_MAX_DAYS[i - 1];
			} else {
				count += MAX_DAYS[i - 1];
			}
		}
		System.out.println("count : " + count);
		return count % 7;
	}

	public boolean cheakMonth(int month) {
		if (0 < month && month < 13) {
			return true;
		} else {
			return false;
		}
	}

	public void printCalendar(int year, int month) {
		int maxDay = getMaxDaysOfMonth(year, month);
		int week = getWeekDay(year, month);
		if (week == 0)
			week = 7;
		System.out.println("week : " + week);

		System.out.printf("    << %4d  %2d >>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");
		for (int i = 0; i < week && week != 7; i++) {
			System.out.print("   ");
		}
		for (int i = 1; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if (i % 7 == (7 - week)) {
				System.out.println("");
			}
		}
		System.out.println("\n");
	}

	public void showUI() {
		System.out.println("+-------------------+");
		System.out.println("|    1. 일정 등록");
		System.out.println("|    2. 일정 검색");
		System.out.println("|    3. 달력 보기");
		System.out.println("| h. 도움말    q. 종료");
		System.out.println("+-------------------+");
		System.out.println("명령 (1, 2, 3, h, q)");
		System.out.print("> ");
	}

	// 메뉴 기능 구현
	public void setSchedule(Scanner sc, SaveFile saveFile) {
		System.out.println("[일정 등록]");
		String date;
		Date day;
		PARSE: while (true) {
			System.out.println("날짜를 입력하세요 (yyyy-MM-dd)");
			System.out.print("> ");
			date = sc.next();
			sc.nextLine();
			try {
				day = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				break PARSE;
			} catch (ParseException e) {
				System.out.println("입력한 날짜의 형식이 잘못되었습니다.");
			}
		}
		System.out.print("내용을 입력하세요 > ");
		String detail = sc.nextLine();
		
		saveFile.fileSave(day, detail);
		System.out.println("[일정 등록 완료]\n");
	}

	public void getSchedule(Scanner sc, SaveFile saveFile) {
		System.out.println("[일정 검색]");
		String date;
		Date day;
		PARSE: while (true) {
			System.out.println("날짜를 입력하세요 (yyyy-MM-dd)");
			System.out.print("> ");
			date = sc.next();
			sc.nextLine();
			try {
				day = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				break PARSE;
			} catch (ParseException e) {
				System.out.println("입력한 날짜의 형식이 잘못되었습니다.");
			}
		}
		saveFile.loadFile(day);
	}

	public void showCalendar(Scanner sc, Calendar cal) {
		int month;
		int year;
		CALENDAR: while (true) {
			System.out.println("년도를 입력하세요");
			System.out.print("> ");
			year = sc.nextInt();
			if (year == -1) {
				System.out.println("bye~");
				break CALENDAR;
			}

			MONTH: while (true) {
				System.out.println("월을 입력하세요");
				System.out.print("> ");
				month = sc.nextInt();
				if (cal.cheakMonth(month)) {
					break MONTH;
				} else {
					System.out.println("잘못된 월을 입력하였습니다. 다시 입력해주세요");
				}
			}
			cal.printCalendar(year, month);
		}
	}

	public void help() {
		System.out.println("도움말 입력 예정");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calendar cal = new Calendar();
		SaveFile saveFile = new SaveFile();
		MENU: while (true) {
			cal.showUI();
			String order = sc.next();
			switch (order) {
			case "1":
				cal.setSchedule(sc, saveFile);
				continue;
			case "2":
				cal.getSchedule(sc, saveFile);
				continue;
			case "3":
				cal.showCalendar(sc, cal);
				continue;
			case "h":
				cal.help();
				continue;
			case "q":
				System.out.println("bye~");
				break MENU;
			default:
				System.out.println("잘못 입력하였습니다\n");
				continue;
			}
		}
	}

}
