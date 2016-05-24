import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Main {

	public static void main(String args[]) {
		String directory = System.getProperty("user.dir");
		// System.out.println(directory);
		final File folder = new File(directory);
		List<File> files = getFiles(folder);

		Map<String, List<LabBestOrderAndReply>> map = new HashMap<String, List<LabBestOrderAndReply>>();
		for (File file : files) {
			try {
				Document document = (Document) parseXmlDocumentFromFile(file);
				Element rootNode = document.getRootElement();
				LabBestOrderAndReply order = getLabBestOrderAndReply(rootNode);
				String id = order.getPatient().getPatientID();
				List<LabBestOrderAndReply> res = new ArrayList<LabBestOrderAndReply>();
				if (map.get(id) != null) {
					res = map.get(id);
				}
				res.add(order);
				map.put(id, res);
			} catch (JDOMException | IOException e) {
				e.printStackTrace();
			}
		}

		witeToFile(map);
	}

	private static LabBestOrderAndReply getLabBestOrderAndReply(Element rootNode) {
		Meddelande meddelande = getMeddelande(rootNode);
		Kund kund = getKund(rootNode);
		Patient patient = getPatient(rootNode);
		Remiss remiss = getRemiss(rootNode);
		Prov prov = getProv(rootNode);
		return new LabBestOrderAndReply(meddelande, kund, patient, remiss, prov);
	}

	private static Prov getProv(Element rootNode) {
		Element node = rootNode.getChild("Prov");
		String kommentar = node.getAttributeValue("Kommentar");
		String lid = node.getAttributeValue("LID");
		List<Element> analysElements = node.getChildren("Analys");
		List<Analys> analyser = new LinkedList<Analys>();
		for (Element e : analysElements) {
			Analys analys = getAnalys(e);
			analyser.add(analys);
		}
		return new Prov(kommentar, lid, analyser);
	}

	private static Analys getAnalys(Element node) {
		String kod = node.getAttributeValue("Kod");
		String namn = node.getAttributeValue("Namn");
		String resultat = node.getAttributeValue("Resultat");
		String enhet = node.getAttributeValue("Enhet");
		String ref = node.getAttributeValue("Referensintervall");
		String flagga = node.getAttributeValue("Flagga");
		String kommentar = node.getAttributeValue("Kommentar");
		return new Analys(kod, namn, resultat, enhet, ref, flagga, kommentar);
	}

	private static Remiss getRemiss(Element rootNode) {
		Element node = rootNode.getChild("Remiss");
		String datum = node.getAttributeValue("Datum");
		String tid = node.getAttributeValue("Tid");
		String kommentar = node.getAttributeValue("Kommentar");
		String prioritet = node.getAttributeValue("Prioritet");
		String rid = node.getAttributeValue("RID");
		String svarstatus = node.getAttributeValue("Svarsstatus");
		return new Remiss(datum, tid, kommentar, prioritet, rid, svarstatus);
	}

	private static Patient getPatient(Element rootNode) {
		Element node = rootNode.getChild("Patient");
		String patientid = node.getAttributeValue("PatientID");
		String namn = node.getAttributeValue("Namn");
		String kon = node.getAttributeValue("Kon");
		return new Patient(patientid, namn, kon);
	}

	private static Kund getKund(Element rootNode) {
		Element node = rootNode.getChild("Kund");
		String best = node.getAttributeValue("Best");
		String xsvar = node.getAttributeValue("XSvar");
		String fakt = node.getAttributeValue("Fakt");
		String sign = node.getAttributeValue("Sign");
		return new Kund(best, xsvar, fakt, sign);
	}

	private static Meddelande getMeddelande(Element rootNode) {
		Element node = rootNode.getChild("Meddelande");
		String datum = node.getAttributeValue("Datum");
		String tid = node.getAttributeValue("Tid");
		String meddelandeID = node.getAttributeValue("MeddelandeID");
		return new Meddelande(datum, tid, meddelandeID);
	}

	private static Document parseXmlDocumentFromFile(File file)
			throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder(false);
		builder.setValidation(false);
		builder.setFeature("http://xml.org/sax/features/validation", false);
		builder.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
				false);
		builder.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-external-dtd",
				false);
		return builder.build(file);
	}

	private static void witeToFile(Map<String, List<LabBestOrderAndReply>> map) {

		String dir = System.getProperty("user.dir");

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")
				.format(Calendar.getInstance().getTime());

		// System.out.println(timeStamp);

		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(dir
					+ "/analys_" + timeStamp + ".xls"));

			WritableSheet sheet = workbook.createSheet("LabBestOrderAndReply",
					0); // sheet

			int titleRepeats = 0;
			for(String key : map.keySet()){
				for(LabBestOrderAndReply order : map.get(key)){
					titleRepeats += order.getProv().getAnalysList().size();
				}
			}
			addTitle(sheet, titleRepeats);

			int row = 1;
			int column;
			for (String key : map.keySet()) {
				column = 0;
				List<LabBestOrderAndReply> orders = map.get(key);

				for (LabBestOrderAndReply order : orders) {

					try {
						for (Analys analys : order.getProv().getAnalysList()) {
							
							sheet.addCell(new Label(column++, row, order
									.getMeddelande().getDatum()));
							sheet.addCell(new Label(column++, row, order
									.getMeddelande().getTid()));
							sheet.addCell(new Label(column++, row, order
									.getMeddelande().getMeddelandeID()));
							sheet.addCell(new Label(column++, row, order.getKund()
									.getBest()));
							sheet.addCell(new Label(column++, row, order.getKund()
									.getxSvar()));
							sheet.addCell(new Label(column++, row, order.getKund()
									.getFakt()));
							sheet.addCell(new Label(column++, row, order.getKund()
									.getSign()));
							sheet.addCell(new Label(column++, row, order
									.getPatient().getPatientID()));
							sheet.addCell(new Label(column++, row, order
									.getPatient().getNamn()));
							sheet.addCell(new Label(column++, row, order
									.getPatient().getKon()));
							sheet.addCell(new Label(column++, row, order
									.getRemiss().getDatum()));
							sheet.addCell(new Label(column++, row, order
									.getRemiss().getTid()));
//							sheet.addCell(new Label(column++, row, order
//									.getRemiss().getKommentar()));
//							sheet.addCell(new Label(column++, row, order
//									.getRemiss().getPrioritet()));
//							sheet.addCell(new Label(column++, row, order
//									.getRemiss().getRid()));
//							sheet.addCell(new Label(column++, row, order
//									.getRemiss().getSvarsstatus()));
//							sheet.addCell(new Label(column++, row, order.getProv()
//									.getKommentar()));
							sheet.addCell(new Label(column++, row, order.getProv()
									.getLid()));
							
							sheet.addCell(new Label(column++, row, analys
									.getKod()));
							sheet.addCell(new Label(column++, row, analys
									.getNamn()));
							sheet.addCell(new Label(column++, row, analys
									.getResultat()));
							sheet.addCell(new Label(column++, row, analys
									.getEnhet()));
							sheet.addCell(new Label(column++, row, analys
									.getReferensintervall()));
							sheet.addCell(new Label(column++, row, analys
									.getFalgga()));
							// Remove line breaks
							String analyskommentar = analys.getKommentar()
									.replaceAll("[\n\r]", "");
							sheet.addCell(new Label(column++, row,
									analyskommentar));
						}
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
				// System.out.println(key + ", " +
				// order.getProv().getAnalysList().get(0).getKod());
				row++;
			}

			sheetAutoFitColumns(sheet);
			workbook.write();
			workbook.close();

		} catch (IOException | WriteException e) {
			e.printStackTrace();
		}
	}

	private static void addTitle(WritableSheet sheet, int titleRepeats) {
		String[] titles = { "Datum", "Tid", "MeddelandeID", "Kund", "XSvar",
				"Fakt", "Sign", "PatientID", "Namn", "Kön", "RemissDatum",
				"RemisTid", "LID" };
		// , "RemisKommentar", "Prioritet", "RID",
		// "Svarsstatus", "ProvKommentar",

		String[] analysTitles = { "AnalysKod", "AnalysNamn", "Resultat",
				"Enhet", "Referensintervall", "Flagga", "Kommentar" };
		try {
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBackground(Colour.GRAY_25);

			int colCount = 0;
			for(int i=0; i<titleRepeats; i++){
				for (int col = 0; col < titles.length; col++) {
					Label label = new Label(colCount++, 0, titles[col], cellFormat);
					sheet.addCell(label);
				}
				
				for (String title : analysTitles) {
					Label label2 = new Label(colCount++, 0, title, cellFormat);
					sheet.addCell(label2);
				}
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private static List<File> getFiles(final File folder) {
		List<File> files = new LinkedList<File>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				// listFilesForFolder(fileEntry);
			} else {
				if (fileEntry.getName().contains(".xml")) {
					files.add(fileEntry);
					// System.out.println(fileEntry.getName());
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
			cv.setSize((int) ((longestStrLen * 256 + 100) * 1.3)); /*
																	 * Every
																	 * character
																	 * is 256
																	 * units
																	 * wide, so
																	 * scale it.
																	 */
			sheet.setColumnView(i, cv);
		}
	}
}
