package desafio.desafiojrblog.domain.dto;

import desafio.desafiojrblog.domain.Post;
import desafio.desafiojrblog.utils.TypeUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String body;

    public Post toEntity() {
        return TypeUtils.parseToEntity(this, Post.class);
    }
}
