package desafio.desafiojrblog.repository;

import desafio.desafiojrblog.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void itShouldSavePost() {
        Post post = postRepository.save(buildPost());

        Assertions.assertNotNull(post);
        Assertions.assertNotNull(post.getId());
        Assertions.assertFalse(post.getDeleted());
    }

    @Test
    public void itShouldFindPostById() {
        Post savedPost = postRepository.save(buildPost());

        Optional<Post> post = postRepository.findById(savedPost.getId());

        Assertions.assertNotNull(post.get());
        Assertions.assertNotNull(post.get().getId());
        Assertions.assertFalse(post.get().getDeleted());
        Assertions.assertEquals(savedPost.getId(), post.get().getId());
    }

    @Test
    public void itShouldFindAllPost() {
        postRepository.save(buildPost());
        postRepository.save(buildPost());

        List<Post> posts = postRepository.findAll();

        Assertions.assertNotNull(posts);
        Assertions.assertFalse(posts.isEmpty());
        Assertions.assertFalse(posts.isEmpty());
    }

    @Test
    public void itShouldDeletePostById() {
        Post savedPost = postRepository.save(buildPost());

        postRepository.deleteById(savedPost.getId());

        Optional<Post> deletedPost = postRepository.findById(savedPost.getId());

        Assertions.assertTrue(deletedPost.isEmpty());
    }

    @Test
    public void itShouldFindByIdAndDeletedIsTrue() {
        Post post = buildPost();

        post.setDeleted(true);
        post.setDeletedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        Optional<Post> deletedPost = postRepository.findByIdAndDeletedIsTrue(savedPost.getId());

        Assertions.assertTrue(deletedPost.isPresent());
    }

    @Test
    public void itShouldDeleteOldPosts() {
        Post post = buildPost();

        post.setDeleted(true);
        post.setDeletedAt(LocalDateTime.now().minusDays(30));

        Post savedPost = postRepository.save(post);

        postRepository.deleteOldPosts(LocalDateTime.now());

        Optional<Post> deletedPost = postRepository.findByIdAndDeletedIsTrue(savedPost.getId());

        Assertions.assertTrue(deletedPost.isEmpty());
    }

    private Post buildPost() {
        return Post.builder()
                .id(null)
                .title("unit test")
                .description("unit test")
                .body("unit test")
                .build();
    }
}
