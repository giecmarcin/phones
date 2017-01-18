package com.mmgiec.app.services.impl;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mmgiec.app.entities.PhoneAndQuantityInOrder;
import com.mmgiec.app.entities.User;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class Pdf {
    private java.util.List<PhoneAndQuantityInOrder> phonesInOrder;
    private User customer;
    private String FILE;
    private BaseFont baseFont;
    private Font font;


    public Pdf(java.util.List<PhoneAndQuantityInOrder> phonesInOrder, User customer) {
        String fileName = LocalDate.now().toString() + "-" + customer.getFirst_name() + " " + customer.getLast_name() + "-" + UUID.randomUUID();
        this.FILE = System.getProperty("user.dir") + "/" + fileName + ".pdf";
        this.phonesInOrder = phonesInOrder;
        this.customer = customer;

        try {
            this.baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf",
                    BaseFont.CP1250, BaseFont.EMBEDDED);
            this.font = new Font(baseFont, 12);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addContent(document);
        document.close();
        openPDF(FILE);
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Faktura", font);
        anchor.setName("Faktura");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Klient", font);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Imię: " + customer.getFirst_name(),font));
        subCatPart.add(new Paragraph("Nazwisko: " +customer.getLast_name(),font));
        subCatPart.add(new Paragraph("Email: " +customer.getEmail(), font));

        subPara = new Paragraph("Telefony", font);
        subCatPart = catPart.addSection(subPara);


        // add a list
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);
        double cost =0;
        for(PhoneAndQuantityInOrder item : phonesInOrder){
            cost+= item.getPhone().getPrice() * item.getQuantity();
        }
        subCatPart.add(new Paragraph("Wartość: " + cost + " zł",font));
        subCatPart.add(Chunk.NEWLINE);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);
    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Nazwa", font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cena",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ilość",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (PhoneAndQuantityInOrder item : phonesInOrder) {
            table.addCell(item.getPhone().getFullName());
            table.addCell(String.valueOf(item.getPhone().getPrice()));
            table.addCell(String.valueOf(item.getQuantity()));
        }
        subCatPart.add(table);

    }

//    private static void createList(Section subCatPart) {
//        List list = new List(true, false, 10);
//        list.add(new ListItem("First point"));
//        list.add(new ListItem("Second point"));
//        list.add(new ListItem("Third point"));
//        subCatPart.add(list);
//    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void openPDF(String filePath) {
        try {

            if ((new File(filePath)).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler " + filePath);
                p.waitFor();

            } else {

                System.out.println("File is not exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
