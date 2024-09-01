package tdr.pet.weblibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tdr.pet.weblibrary.model.dto.AuthorDTO;
import tdr.pet.weblibrary.model.entity.Author;
import tdr.pet.weblibrary.model.mapper.AuthorMapper;
import tdr.pet.weblibrary.service.AuthorService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TestRestAuthorController {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private RestAuthorController restAuthorController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restAuthorController).build();
    }

    @Test
    void testFindAuthorsByName() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");
        authorDTO.setEmail("john.doe@example.com");
        authorDTO.setImgUrl("http://example.com/image.jpg");

        Author author = new Author();

        List<Author> authors = List.of(author);

        when(authorService.findAuthorsByName(anyString())).thenReturn(authors);
        when(authorMapper.toDTO(any(Author.class))).thenReturn(authorDTO);

        mockMvc.perform(get("/api/v1/author/name/{name}", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].imgUrl").value("http://example.com/image.jpg"));
    }

    @Test
    void testFindAuthorsByEmail() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");
        authorDTO.setEmail("john.doe@example.com");
        authorDTO.setImgUrl("http://example.com/image.jpg");

        Author author = new Author();

        Set<Author> authors = new HashSet<>(List.of(author));

        when(authorService.findAuthorsByEmail(anyString())).thenReturn(authors);
        when(authorMapper.toDTO(any(Author.class))).thenReturn(authorDTO);

        mockMvc.perform(get("/api/v1/author/email/{email}", "john.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].imgUrl").value("http://example.com/image.jpg"));
    }

    @Test
    void testCreateNewAuthor() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");
        authorDTO.setEmail("john.doe@example.com");
        authorDTO.setImgUrl("http://example.com/image.jpg");

        Author author = new Author();

        when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(author);
        doNothing().when(authorService).createNewAuthor(any(Author.class));

        mockMvc.perform(post("/api/v1/author")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateAuthorById() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");
        authorDTO.setEmail("john.doe@example.com");
        authorDTO.setImgUrl("http://example.com/image.jpg");

        Author author = new Author();

        when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(author);
        doNothing().when(authorService).updateAuthorById(anyLong(), any(Author.class));

        mockMvc.perform(put("/api/v1/author/{id}", 1L)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAuthorByEmail() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");
        authorDTO.setEmail("john.doe@example.com");
        authorDTO.setImgUrl("http://example.com/image.jpg");

        Author author = new Author();

        when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(author);
        doNothing().when(authorService).updateAuthorByEmail(anyString(), any(Author.class));

        mockMvc.perform(put("/api/v1/author/email/{email}", "john.doe@example.com")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAuthorById() throws Exception {
        doNothing().when(authorService).deleteAuthorById(anyLong());

        mockMvc.perform(delete("/api/v1/author/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAuthorByEmail() throws Exception {
        doNothing().when(authorService).deleteAuthorByEmail(anyString());

        mockMvc.perform(delete("/api/v1/author/email/{email}", "john.doe@example.com"))
                .andExpect(status().isNoContent());
    }
}