package tdr.pet.weblibrary.service;

import tdr.pet.weblibrary.model.dto.AuthorDTO;
import tdr.pet.weblibrary.model.entity.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    List<Author> findAuthorsByName(String name);

    Set<Author> findAuthorsByEmail(String email);

    boolean exists(String email);

    void createNewAuthor(Author author);

    void updateAuthorById(Long id, AuthorDTO authorDTO);

    void updateAuthorByEmail(String email, AuthorDTO authorDTO);

    void deleteAuthorById(Long id);

    void deleteAuthorByEmail(String email);

}