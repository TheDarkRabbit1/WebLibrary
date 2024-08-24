package tdr.pet.weblibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tdr.pet.weblibrary.model.dao.Genre;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private int pages;
    private String isbn;
    private Genre genre;
    private LocalDateTime written;
}
