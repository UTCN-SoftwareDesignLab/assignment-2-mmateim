package demo.controller;

import demo.entity.Book;
import demo.report.GeneratePdfReport;
import demo.service.BookService;
import demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        System.out.println("ReportController : return report.html");
        model.addAttribute("message", "");
        return "report";
    }

    @ResponseBody
    @RequestMapping(params = "pdf=", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> booksReport() throws IOException {
        System.out.println("ReportController : generate PDF report");
        List<Book> books = (List<Book>) bookService.findEmptyStock();
        ByteArrayOutputStream outputStream = GeneratePdfReport.booksReport(books);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        /*String filename = "D:\\facultate\\an3\\sem2\\SD\\Ass2\\assignment-2-mmateim\\Assignments\\Assignment 2\\demo\\src\\main\\java\\demo\\emptyStockBooks.pdf";
        File file = new File(filename);
        if (!file.exists()){
           if(!file.createNewFile()){
               throw new FileNotFoundException("file was not created.");
           }
        }
        InputStream in = new FileInputStream(file);*/

        ReportService reportService = new ReportService("PDF", bookService.findEmptyStock());
        reportService.generate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=emptyStockBooks.pdf");
        ResponseEntity<InputStreamResource> response = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
        //FileCopyUtils.copy(in, outputStream);
        return response;
    }

    @RequestMapping(params = "csv=", method = RequestMethod.GET)
    public String generateCSV(Model model) {
        System.out.println("ReportController : generate CSV report");
        model.addAttribute("message", "generated CSV report");
        ReportService reportService = new ReportService("CSV", bookService.findEmptyStock());
        reportService.generate();
        return "report";
    }
}
