package demo.service;

import com.itextpdf.text.pdf.PdfPTable;
import demo.entity.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportService {

    private String type;
    private List<Book> books;

    public ReportService(String type, List<Book> books) {
        this.type = type;
        this.books = books;
    }

    public void generate() {
        if (type.equals("PDF")) {
            generatePDF();
        } else {
            generateCSV();
        }
    }

    public void generatePDF() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDFont font = PDType1Font.TIMES_ROMAN;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            String message = "Books out of stock :";
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.setLeading(10f);
            contentStream.newLineAtOffset(50, 685);
            contentStream.showText(message);
            contentStream.newLine();
            contentStream.newLine();
            for (Book book : books) {
                contentStream.showText(book.toString());
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            document.save("EmptyStockBooks.pdf");
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateCSV() {
        try {
            PrintWriter writer = new PrintWriter(new File("EmptyStockBooks.csv"));
            StringBuilder builder = new StringBuilder();
            builder.append("Id");
            builder.append(',');
            builder.append("Title");
            builder.append(',');
            builder.append("Author");
            builder.append(',');
            builder.append("ISBN");
            builder.append(',');
            builder.append("Genre");
            builder.append(',');
            builder.append("Price");
            builder.append('\n');
            for (Book book : books) {
                builder.append(book.getId());
                builder.append(',');
                builder.append(book.getName());
                builder.append(',');
                builder.append(book.getAuthor());
                builder.append(',');
                builder.append(book.getIsbn());
                builder.append(',');
                builder.append(book.getGenre());
                builder.append(',');
                builder.append(book.getPrice());
                builder.append('\n');
            }
            writer.write(builder.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
