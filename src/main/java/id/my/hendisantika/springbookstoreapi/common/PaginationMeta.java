package id.my.hendisantika.springbookstoreapi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 07:49
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationMeta {
    private Long totalCount;
    private Integer pageSize;
    private Integer totalPage;
    private Integer pageNumber;
    private Boolean isLast;
    private Boolean isFirst;

    public static <T> PaginationMeta createPagination(Page<T> page) {
        PaginationMeta paginationMeta = new PaginationMeta();

        paginationMeta.setIsFirst(page.isFirst());
        paginationMeta.setIsLast(page.isLast());
        paginationMeta.setPageNumber(page.getNumber() + 1);
        paginationMeta.setPageSize(page.getSize());
        paginationMeta.setTotalCount(page.getTotalElements());
        paginationMeta.setTotalPage(page.getTotalPages());

        return paginationMeta;
    }
}
