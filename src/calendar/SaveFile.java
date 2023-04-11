package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveFile {
	String path = "data.txt";
	File f = new File(path);

	public void fileSave(Date date, String detail) {
		try {
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(date + "," + detail);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println("익셉션 발생");
			e.printStackTrace();
		}
	}

	public String loadFile(Date date) {
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String readLine = null;
			String[] line = null;
			Date date2 = null;
			while ((readLine = br.readLine()) != null) {
				line = readLine.split(",");
				SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
				date2 = formatter.parse(line[0]);
				if (date.compareTo(date2) == 0) {
					try {
						System.out.println("내용 : " + line[1] + "\n");
					} catch (Exception e) {
						System.out.println("내용 : [없음]\n");
					}
				}
			}
			System.out.println();
			br.close();
		} catch (Exception e) {
			System.out.println("익셉션 발생");
			e.printStackTrace();
		}
		return null;
	}
}
