package desafio.desafiojrblog.repository;

import desafio.desafiojrblog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCreatedAtBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT * FROM t_post post WHERE post.id = :id AND post.deleted = TRUE AND deleted_at IS NOT NULL", nativeQuery = true)
    Optional<Post> findByIdAndDeletedIsTrue(@Param("id") Long id);

    @Query(value = "DELETE FROM t_post post WHERE post.deleted_at <= :referenceDate AND post.deleted = TRUE", nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteOldPosts(@Param("referenceDate") LocalDateTime referenceDate);
}
