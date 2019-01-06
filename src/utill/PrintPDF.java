package utill;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.Room;
import dto.TypeRoom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;


public class PrintPDF {
	private static String FILE = "";
	static String FONT = "resources/font/arial.ttf";
	static Font catFont = FontFactory.getFont(FONT, "Cp1250", BaseFont.EMBEDDED);

	static File saveFile;
	public static void PrintFilePDF(String title, String sub_title,Shell shlLpBoCo, ArrayList<TypeRoom> lsb, ArrayList<Room> lsb2) {
		try {
			FILE = System.getProperty("user.dir") + "/temp/FirstPdf.pdf";
			System.out.print(FILE);

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			//addTitlePage(document,title);
			addContent(document,sub_title,lsb,lsb2);
			document.close();

			FileDialog dialog = new FileDialog(shlLpBoCo, SWT.SAVE);
			dialog
			.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
			dialog.setFilterExtensions(new String[] { "pdf", "*.*" }); // Windows
			// wild
			// cards
			dialog.setFilterPath("C:\\"); // Windows path
			dialog.setFileName("Report.pdf");

			saveFile = new File(dialog.open());
			copyFile(new File(FILE),saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	private static void addMetaData(Document document) {
		document.addTitle("REPORT");
		document.addSubject("QLKS");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("1742968");
		document.addCreator("1742968");
	}

	private static void addContent(Document document,String title, ArrayList<TypeRoom> lsb, ArrayList<Room> lsb2) throws DocumentException {
		Anchor anchor = new Anchor(title, catFont);
		anchor.setName(title);

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("", catFont);
		Section subCatPart = catPart.addSection(subPara);

		// add a table
		createTable(subCatPart, lsb, lsb2);

		// now add all this to the document
		document.add(catPart);
	}

	private static void createTable(Section subCatPart, ArrayList<TypeRoom> lsb, ArrayList<Room> lsb2)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Ten"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Doanh Thu"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Ty le", catFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		double sum = 0;

		for (Room room : lsb2) {
			sum+=room.getPRICE();
		}
	
		for (Room room : lsb2) {
			table.addCell(Utill.removeAccent(room.getNAME()));
			table.addCell(Utill.formatCurrency(room.getPRICE()));
			table.addCell(String.format("%10.1f",(room.getPRICE()/sum  * 100)) + "%");

		}
		sum = 0;

		for (TypeRoom room : lsb) {
			sum+=room.getPRICE();
		}
	
		for (TypeRoom room : lsb) {
			table.addCell(Utill.removeAccent(room.getNAME()));
			table.addCell(Utill.formatCurrency(room.getPRICE()));
			table.addCell(String.format("%10.1f",(room.getPRICE()/sum  * 100))+ "%");

		}
		

		subCatPart.add(table);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	private static void copyFile(File src, File dest) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		int len;
		byte buffer[] = new byte[512];

		try {
			inputStream = new FileInputStream(src);
			outputStream = new FileOutputStream(dest);

			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		} finally {
			try {
				if(inputStream != null){
					inputStream.close();
				}
				if(outputStream != null){
					outputStream.close();
				}
			} catch (IOException ex) {

			}
		}

	}
}
