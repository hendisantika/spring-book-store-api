package id.my.hendisantika.springbookstoreapi.service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.springbookstoreapi.entity.BookEdition;
import id.my.hendisantika.springbookstoreapi.repository.BookEditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for BookEdition operations
 * Handles business logic for book edition management
 */
@Service
@RequiredArgsConstructor
public class BookEditionService {

    private final BookEditionRepository bookEditionRepository;

    public List<BookEdition> getAllBookEditions() {
        return bookEditionRepository.findAll();
    }

    public Page<BookEdition> getAllBookEditions(Pageable pageable) {
        return bookEditionRepository.findAll(pageable);
    }

    public BookEdition getBookEditionById(Long id) {
        Optional<BookEdition> editionOpt = bookEditionRepository.findById(id);
        return editionOpt.orElse(null);
    }

    public List<BookEdition> getBookEditionsByBookId(Long bookId) {
        return bookEditionRepository.findByBookId(bookId);
    }

    public BookEdition createBookEdition(BookEdition bookEdition) {
        return bookEditionRepository.save(bookEdition);
    }

    public BookEdition updateBookEdition(Long id, BookEdition editionUpdate) {
        Optional<BookEdition> existingEditionOpt = bookEditionRepository.findById(id);
        if (existingEditionOpt.isPresent()) {
            BookEdition existingEdition = existingEditionOpt.get();
            existingEdition.setPrice(editionUpdate.getPrice());
            existingEdition.setPageSize(editionUpdate.getPageSize());
            existingEdition.setDescription(editionUpdate.getDescription());
            existingEdition.setIsbn(editionUpdate.getIsbn());
            if (editionUpdate.getBook() != null) {
                existingEdition.setBook(editionUpdate.getBook());
            }
            return bookEditionRepository.save(existingEdition);
        }
        return null;
    }

    public String deleteBookEdition(Long id) {
        if (bookEditionRepository.existsById(id)) {
            bookEditionRepository.deleteById(id);
            return "Book edition deleted successfully";
        }
        return "Book edition not found";
    }
}