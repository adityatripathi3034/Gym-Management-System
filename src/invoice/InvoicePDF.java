package invoice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import model.Member;

public class InvoicePDF {
	
	public InvoicePDF() {
//		startGUI();
	}
	
	public static void startGUI(Member member) {
		System.out.println("Test data "+member.getRegis());
		
		Document document = new Document(PageSize.A4, 16f, 16f, 16f, 16f);

		try {
			//System.out.println("Pdf created successfully");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("GMS_Invoice.pdf"));
			document.open();

			float fntSize, lineSpacing;
			fntSize = 25.7f;
			lineSpacing = 10f;
			Paragraph paragraph = new Paragraph(
					new Phrase(lineSpacing, "INVOICE", FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize)));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			LineSeparator lineSeparator = new LineSeparator();
            document.add(new Chunk(lineSeparator));// Line Seprator
            
            document.add( Chunk.NEWLINE );//space
			
			// Create Table here.
			PdfPTable pdfPTable = new PdfPTable(4);
			pdfPTable.setWidthPercentage(100);
			pdfPTable.setSpacingBefore(11F);
			pdfPTable.setSpacingAfter(11F);
			float cellWidth [] = {2f, 2f, 2f, 2f};
			pdfPTable.setWidths(cellWidth);
			
			//Create Table Cell Here.
			PdfPCell cell_1 = new PdfPCell(new Paragraph(lineSpacing,"Name-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_1.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_2 = new PdfPCell(new Paragraph(new Phrase(member.getFirstName(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_2.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_3 = new PdfPCell(new Paragraph(lineSpacing,"Last Name-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_3.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_4 = new PdfPCell(new Paragraph(new Phrase(member.getLastName(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_4.setBorderColor(BaseColor.WHITE);	
			
			
			//create table1 here
			PdfPTable pdfPTable1 = new PdfPTable(4);
			pdfPTable1.setWidthPercentage(100);
			pdfPTable1.setSpacingBefore(11F);
			pdfPTable1.setSpacingAfter(11F);
			float cellWidth1[] = {2f, 2f, 2f, 2f};
			pdfPTable1.setWidths(cellWidth1);
			
			PdfPCell cell_5 = new PdfPCell(new Paragraph(lineSpacing,"Address-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_5.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_6 = new PdfPCell(new Paragraph(new Phrase(member.getAddress(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_6.setBorderColor(BaseColor.WHITE);
			
			PdfPCell cell_7 = new PdfPCell(new Paragraph(lineSpacing,"Date-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_7.setBorderColor(BaseColor.WHITE);	
			//Date date = new Date();
			PdfPCell cell_8 = new PdfPCell(new Paragraph(lineSpacing,member.getStartDate(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_8.setBorderColor(BaseColor.WHITE);	
			
			//create table2 here
			PdfPTable pdfPTable2 = new PdfPTable(4);
			pdfPTable2.setWidthPercentage(100);
			pdfPTable2.setSpacingBefore(11F);
			pdfPTable2.setSpacingAfter(11F);
			float cellWidth2[] = {2f, 2f, 2f, 2f};
			pdfPTable2.setWidths(cellWidth2);
			
			PdfPCell cell_9 = new PdfPCell(new Paragraph(lineSpacing,"Subscipation-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_9.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_10 = new PdfPCell(new Paragraph(new Phrase(member.getSubscripation(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_10.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_11 = new PdfPCell(new Paragraph(lineSpacing,"Facility-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_11.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_12 = new PdfPCell(new Paragraph(new Phrase(member.getFacility(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_12.setBorderColor(BaseColor.WHITE);	
			
			//create table3 here
			
			PdfPTable pdfPTable3 = new PdfPTable(4);
			pdfPTable3.setWidthPercentage(100);
			pdfPTable3.setSpacingBefore(11F);
			pdfPTable3.setSpacingAfter(11F);
			float cellWidth3[] = {2f, 2f, 2f, 2f};
			pdfPTable3.setWidths(cellWidth3);
			
			PdfPCell cell_13= new PdfPCell(new Paragraph(lineSpacing,"Ammount-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_13.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_14 = new PdfPCell(new Paragraph(new Phrase(member.getAmmount(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_14.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_15 = new PdfPCell(new Paragraph(lineSpacing,"Pending Ammount-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_15.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_16 = new PdfPCell(new Paragraph(new Phrase(member.getPendingAmmount(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_16.setBorderColor(BaseColor.WHITE);	
			
			//create table 4
			
			PdfPTable pdfPTable4 = new PdfPTable(4);
			pdfPTable4.setWidthPercentage(100);
			pdfPTable4.setSpacingBefore(11F);
			pdfPTable4.setSpacingAfter(11F);
			float cellWidth4[] = {2f, 2f, 2f, 2f};
			pdfPTable3.setWidths(cellWidth4);
			
			PdfPCell cell_17= new PdfPCell(new Paragraph(lineSpacing,"Trainer Name-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_17.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_18 = new PdfPCell(new Paragraph(new Phrase(member.getPersonalTrainer(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_18.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_19 = new PdfPCell(new Paragraph(lineSpacing,"Registration-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_19.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_20 = new PdfPCell(new Paragraph(new Phrase(member.getRegis(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_20.setBorderColor(BaseColor.WHITE);	
			
			
			//create table5
			PdfPTable pdfPTable5= new PdfPTable(4);
			pdfPTable5.setWidthPercentage(100);
			pdfPTable5.setSpacingBefore(11F);
			pdfPTable5.setSpacingAfter(11F);
			float cellWidth5[] = {2f, 2f, 2f, 2f};
			pdfPTable3.setWidths(cellWidth5);
			
			PdfPCell cell_21= new PdfPCell(new Paragraph(lineSpacing,"Contact no-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_21.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_22 = new PdfPCell(new Paragraph(new Phrase(member.getContactNo(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_22.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_23 = new PdfPCell(new Paragraph(lineSpacing,"EmailAdd-:",FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
			cell_23.setBorderColor(BaseColor.WHITE);	
			PdfPCell cell_24 = new PdfPCell(new Paragraph(new Phrase(member.getEmailAdd(),FontFactory.getFont(FontFactory.COURIER_BOLD, 15))));
			cell_24.setBorderColor(BaseColor.WHITE);	
			
			
			
			
			
			pdfPTable.addCell(cell_1);
			pdfPTable.addCell(cell_2);
			pdfPTable.addCell(cell_3);
			pdfPTable.addCell(cell_4);
			
			pdfPTable1.addCell(cell_5);
			pdfPTable1.addCell(cell_6);
			pdfPTable1.addCell(cell_7);
			pdfPTable1.addCell(cell_8);
			
			pdfPTable2.addCell(cell_9);
			pdfPTable2.addCell(cell_10);
			pdfPTable2.addCell(cell_11);
			pdfPTable2.addCell(cell_12);
			
			pdfPTable3.addCell(cell_13);
			pdfPTable3.addCell(cell_14);
			pdfPTable3.addCell(cell_15);
			pdfPTable3.addCell(cell_16);
			
			pdfPTable4.addCell(cell_17);
			pdfPTable4.addCell(cell_18);
			pdfPTable4.addCell(cell_19);
			pdfPTable4.addCell(cell_20);
			
			pdfPTable5.addCell(cell_21);
			pdfPTable5.addCell(cell_22);
			pdfPTable5.addCell(cell_23);
			pdfPTable5.addCell(cell_24);
			
			
			
			document.add(pdfPTable);
			document.add(pdfPTable1);
			document.add(pdfPTable2);
			document.add(pdfPTable3);
			document.add(pdfPTable4);
			document.add(pdfPTable5);
			document.close();
			pdfWriter.close();

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
