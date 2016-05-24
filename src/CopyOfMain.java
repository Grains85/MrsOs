import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CopyOfMain {

	public static void main(String args[]) {
		try {

			String directory = System.getProperty("user.dir");
			System.out.println(directory);
			final File folder = new File(directory);
			List<File> files = getFiles(folder);

			try {

				// Key, attribute, value
				Map<String, Map<String,String>> map = new HashMap<String,Map<String,String>>();

				String[] titles = { "Datum", "Tid", "MeddelandeID", "Best",
						"XSvar", "Fakt", "Sign", "PatientID", "Namn",
						"Kon", "RemissDatum", "RemisTid", "RemisKommentar",
						"Prioritet", "RID", "Svarsstatus", "ProvKommentar1",
						"ProvLID1", "ProvKommentar2",
						"ProvLID2","AnalysKod", "AnalysNamn", "AnalysResultat",
						"AnalysEnhet", "AnalysReferensintervall", "AnalysFlagga", "AnalysKommentar" };
				
				for (File file : files) {

					FileInputStream fstream = new FileInputStream(file);
					// Get the object of DataInputStream
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(in));
					


					

					int analysCount = 0;
					
//					x = 0;
					Map<String,String> dataMap = new HashMap<String,String>();
					
					String key = "noName";
					
					String line;
					int i = 0;
					// Read File Line By Line
					while ((line = br.readLine()) != null) {
						// Print the content on the console
						if (line.contains("Meddelande")) {
							String datum = getSubstring(line, "Datum", '"');
							String tid = getSubstring(line, "Tid", '"');
							String med = getSubstring(line, "MeddelandeID", '"');

							dataMap.put(titles[i++],datum);
							dataMap.put(titles[i++],tid);
							dataMap.put(titles[i++],med);
							
//							Label label = new Label(x++, y, datum);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, tid);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, med);
//							sheet.addCell(label);
						} else if (line.contains("Kund")) {
							String a = getSubstring(line, "Best", '"');
							String b = getSubstring(line, "XSvar", '"');
							String c = getSubstring(line, "Fakt", '"');
							String d = getSubstring(line, "Sign", '"');

							dataMap.put(titles[i++],a);
							dataMap.put(titles[i++],b);
							dataMap.put(titles[i++],c);
							dataMap.put(titles[i++],d);
							
//							Label label = new Label(x++, y, a);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, b);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, c);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, d);
//							sheet.addCell(label);
						} else if (line.contains("Patient")) {
							String a = getSubstring(line, "PatientID", '"');
							String b = getSubstring(line, "Namn", '"');
							String c = getSubstring(line, "Kon", '"');

							dataMap.put(titles[i++],a);
							dataMap.put(titles[i++],b);
							dataMap.put(titles[i++],c);
							
							key = b;
							
//							System.out.println("KKEEYY::"+key);
//							Label label = new Label(x++, y, a);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, b);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, c);
//							sheet.addCell(label);
						} else if (line.contains("Remiss")) {
							String a = getSubstring(line, "Datum", '"');
							String b = getSubstring(line, "Tid", '"');
							String c = getSubstring(line, "Kommentar", '"');
							String d = getSubstring(line, "Prioritet", '"');
							String e = getSubstring(line, "RID", '"');
							String f = getSubstring(line, "Svarsstatus", '"');

							dataMap.put(titles[i++],a);
							dataMap.put(titles[i++],b);
							dataMap.put(titles[i++],c);
							dataMap.put(titles[i++],d);
							dataMap.put(titles[i++],e);
							dataMap.put(titles[i++],f);
							
							
//							Label label = new Label(x++, y, a);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, b);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, c);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, d);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, e);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, f);
//							sheet.addCell(label);
						} else if (line.contains("Prov")
								&& line.contains("Kommentar")) {
							String a = getSubstring(line, "Kommentar", '"');
							String b = getSubstring(line, "LID", '"');

							dataMap.put(titles[i++],a);
							dataMap.put(titles[i++],b);
							
//							Label label = new Label(x++, y, a);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, b);
//							sheet.addCell(label);
						} else if (line.contains("Analys")) {

							// Skriv titel om titel saknas
//							if (analysCount > 0) {
//								int analysIndex = titles.length - 7;
//								int titelX = x;
//								for (int i = analysIndex; i < titles.length; i++) {
//									String analysTitel = titles[i];
//									Label label = new Label(titelX++, 0,
//											analysTitel);
//									sheet.addCell(label);
//								}
//
//							}
							String a = getSubstring(line, "Kod", '"');
							String b = getSubstring(line, "Namn", '"');
							String c = getSubstring(line, "Resultat", '"');
							String d = getSubstring(line, "Enhet", '"');
							String e = getSubstring(line, "Referensintervall",
									'"');
							String f = getSubstring(line, "Flagga", '"');
							String g = getSubstring(line, "Kommentar", '"');

							i = 18;
							System.out.println("analys i=="+i);
							
							dataMap.put(titles[i++],a);
							dataMap.put(titles[i++],b);
							dataMap.put(titles[i++],c);
							dataMap.put(titles[i++],d);
							dataMap.put(titles[i++],e);
							dataMap.put(titles[i++],f);
							dataMap.put(titles[i++],g);
							
//							Label label = new Label(x++, y, a);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, b);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, c);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, d);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, e);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, f);
//							sheet.addCell(label);
//
//							label = new Label(x++, y, g);
//							sheet.addCell(label);

//							analysCount++;
						} // if
						System.out.println(line);
					} // while
					
						if(map.containsKey(key)){
							Map<String,String> oldData = map.get(key);
							i = 16; // Move to analys i
							int analysNr = 1;
							if(!map.containsKey(titles[i] + analysNr)){
								oldData.put(titles[i++] + analysNr, dataMap.get("provkommentar"));
								oldData.put(titles[i++] + analysNr, dataMap.get("povlid"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analyskod"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analusnamn"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analysresultat"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analysenhet"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analysreferensintervall"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analysflagga"));
								oldData.put(titles[i++] + analysNr,dataMap.get("analyskommentar"));
							}
						}
						else if(!key.equals("noName")){
							System.out.println("INSERTING KEY: "+key);
							map.put(key, dataMap);
						}
							
					

					in.close();

					// Close the input stream

				} // File

				WritableWorkbook workbook;

				String dir = System.getProperty("user.dir");

				String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")
						.format(Calendar.getInstance().getTime());

				System.out.println(timeStamp);

				workbook = Workbook.createWorkbook(new File(dir + "/analys_"
						+ timeStamp + ".xls"));

				// test.xls is my work book
				// STEP 2:
				//
				WritableSheet sheet = workbook.createSheet("First Sheet", 0); // sheet
				
				// Write title
				int x = 0;
				int y = 0;
				
				for (int i = 0; i < titles.length; i++) {
					Label label = new Label(x++, y, titles[i]);
					sheet.addCell(label);
				}
				
				// Write data
				for(String nameKey : map.keySet()){
					System.out.println("nameKey:"+nameKey);
					Map<String,String> dataMap = map.get(nameKey);
					x = 0;
					y++;
					for(int i=0; i<dataMap.size(); i++){
						if(titles.length > i){
							String titleKey = titles[i];
							String data = dataMap.get(titleKey+"1");
							if(data == null)
								data = dataMap.get(titleKey);
							Label label = new Label(x++, y, data);
							sheet.addCell(label);
						}
					}
				}
				
				sheetAutoFitColumns(sheet);
				
				workbook.write();
				workbook.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static String getSubstring(String line, String start, char end) {
		String ans;
		// System.out.println("line:"+line+", start:"+start+", end:"+end);
		try {
			int startIndex = line.indexOf(start) + start.length() + 2; // +2
																		// --->
																		// ="
			String tail = line.substring(startIndex);
			int endIndex = tail.indexOf(end);
			ans = tail.substring(0, endIndex);
		} catch (Exception e) {
			ans = "noFound";
			// System.out.println("NOT FOUND!");
		}
		return ans;
	}

	private static List<File> getFiles(final File folder) {
		List<File> files = new LinkedList<File>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				// listFilesForFolder(fileEntry);
			} else {
				if (fileEntry.getName().contains(".xml")) {
					files.add(fileEntry);
					System.out.println(fileEntry.getName());
				}
			}
		}
		return files;
	}

	private static void sheetAutoFitColumns(WritableSheet sheet) {
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell[] cells = sheet.getColumn(i);
			int longestStrLen = -1;

			if (cells.length == 0)
				continue;

			/* Find the widest cell in the column. */
			for (int j = 0; j < cells.length; j++) {
				if (cells[j].getContents().length() > longestStrLen) {
					String str = cells[j].getContents();
					if (str == null || str.isEmpty())
						continue;
					longestStrLen = str.trim().length();
				}
			}

			/* If not found, skip the column. */
			if (longestStrLen == -1)
				continue;

			/* If wider than the max width, crop width */
			if (longestStrLen > 255)
				longestStrLen = 255;

			CellView cv = sheet.getColumnView(i);
			cv.setSize(longestStrLen * 256 + 100); /*
													 * Every character is 256
													 * units wide, so scale it.
													 */
			sheet.setColumnView(i, cv);
		}
	}
}
