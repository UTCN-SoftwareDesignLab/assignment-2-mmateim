package demo.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDto {
    @Size(min = 2)
    public String name;
    @Pattern(regexp = "^[1-9]+$")
    @Size(min = 5, max = 5, message = "ISBN is the wrong size")
    public String isbn;
    @Size(min = 2)
    public String author;
}
