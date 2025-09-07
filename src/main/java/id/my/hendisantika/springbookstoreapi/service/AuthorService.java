package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.common.APIResponse;
import id.my.hendisantika.springbookstoreapi.common.PaginationMeta;
import id.my.hendisantika.springbookstoreapi.data.AuthorData;
import id.my.hendisantika.springbookstoreapi.entity.Author;
import id.my.hendisantika.springbookstoreapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:31
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public APIResponse getAuthors(Pageable pageable) {
        APIResponse apiResponse = new APIResponse();

        // make db call to get authors
        Page<Author> authorPage = authorRepository.findAll(pageable);

        List<Author> authors = authorPage.getContent();
        PaginationMeta authorPaginationMeta = PaginationMeta.createPagination(authorPage);

        AuthorData authorData = new AuthorData();
        authorData.setAuthors(authors);
        authorData.setPagination(authorPaginationMeta);

        apiResponse.setData(authorData);
        return apiResponse;
    }

    public APIResponse getAuthorsWithNamedQuery(Pageable pageable) {
        APIResponse apiResponse = new APIResponse();

        // make db call to get authors
        Page<Author> authorPage = authorRepository.findAllByOrderByIdDesc(pageable);

        List<Author> authors = authorPage.getContent();
        PaginationMeta authorPaginationMeta = PaginationMeta.createPagination(authorPage);

        AuthorData authorData = new AuthorData();
        authorData.setAuthors(authors);
        authorData.setPagination(authorPaginationMeta);

        apiResponse.setData(authorData);
        return apiResponse;
    }

    public Author getAuthorById(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        return authorOpt.orElse(null);
    }

    public Author createAuthor(Author author) {
        author.setCreatedAt(new DateTime());
        author.setUpdatedAt(new DateTime());
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorUpdate) {
        Optional<Author> existingAuthorOpt = authorRepository.findById(id);
        if (existingAuthorOpt.isPresent()) {
            Author existingAuthor = existingAuthorOpt.get();
            existingAuthor.setName(authorUpdate.getName());
            existingAuthor.setGender(authorUpdate.getGender());
            existingAuthor.setUpdatedAt(new DateTime());
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    public String deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return "Author deleted successfully";
        }
        return "Author not found";
    }
}
