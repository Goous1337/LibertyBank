package pojo.investmentService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewArticleForNews {

        public String id;
        public String title;
        public String text;
        public String tagType;
        public String articleType;
        public String description;
        public long instant;
        public String image;
}

