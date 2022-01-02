package desafio.desafiojrblog.repository;

import desafio.desafiojrblog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCreatedAtBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}
