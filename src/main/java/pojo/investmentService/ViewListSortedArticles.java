package pojo.investmentService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewListSortedArticles {
    public String uri;
    public ArrayList<Content> content;
    public Pageable pageable;
    public int totalPages;
    public int totalElements;
    public boolean last;
    public int size;
    public int number;
    public Sort sort;
    public int numberOfElements;
    public boolean first;
    public boolean empty;

    @Data
    public static class Pageable {
        public int pageNumber;
        public int pageSize;
        public Sort sort;
        public int offset;
        public boolean unpaged;
        public boolean paged;
    }

    @Data
    public static class Sort {
        public boolean empty;
        public boolean unsorted;
        public boolean sorted;
    }

    @Data
    public static class Content {
        public String id;
        public String title;
        public String text;
        public String tagType;
        public String articleType;
        public String description;
        public Object instant;
        public String image;
    }
}