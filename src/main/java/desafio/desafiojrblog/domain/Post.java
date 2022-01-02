package desafio.desafiojrblog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_post")
@Where(clause = "deleted <> true")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post extends BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "sq_post", allocationSize = 1)
    @GeneratedValue(generator = "sq_post")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String body;
}
