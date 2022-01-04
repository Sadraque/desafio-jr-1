package desafio.desafiojrblog.service;

import desafio.desafiojrblog.domain.Post;
import desafio.desafiojrblog.domain.dto.PostDTO;
import desafio.desafiojrblog.exception.NotFoundException;
import desafio.desafiojrblog.repository.PostRepository;
import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(postRepository.save(Mockito.any(Post.class)))
                .thenReturn(buildPost());

        Mockito.when(postRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(buildPost()));

        Mockito.when(postRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(buildPost()));

        Mockito.when(postRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(buildPostPage());

        Mockito.when(postRepository.findByIdAndDeletedIsTrue(Mockito.any(Long.class)))
                .thenReturn(Optional.of(buildPostDeleted()));

        Mockito.when(postRepository.findAllByCreatedAtBetween(Mockito.any(Pageable.class),
                        Mockito.any(LocalDateTime.class),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(buildPostPage());
    }

    @Test()
    public void whenSaveThenReturnSavedPost() {
        Post post = postService.save(buildPostDTO());

        assertNotNull(post);
        assertNotNull(post.getTitle());
        assertNotNull(post.getDescription());
        assertNotNull(post.getBody());
        assertNotNull(post.getDeleted());
        assertNotNull(post.getCreatedAt());
        assertNotNull(post.getUpdatedAt());

        assertEquals(1L, post.getId());
        assertEquals(false, post.getDeleted());

        assertNotEquals(StringUtils.EMPTY, post.getTitle());
        assertNotEquals(StringUtils.EMPTY, post.getDescription());
        assertNotEquals(StringUtils.EMPTY, post.getBody());
    }

    @Test()
    public void whenUpdateThenReturnUpdatedPost() {
        assertNotNull(postService.update(1L, buildPost()));
    }

    @Test()
    public void whenFindByIdThenReturnPost() {
        Post post = postService.findById(1L);

        assertNotNull(post);
        assertNotNull(post.getId());
        assertEquals(1L, post.getId());

        Mockito.when(postRepository.findById(Mockito.any(Long.class)))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> postService.findById(1L));
    }

    @Test()
    public void whenFindAllThenReturnAllPost() {
        Page<Post> posts = postService.findAll(new PageImpl<>(buildPostList()).getPageable());

        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertTrue(posts.getTotalElements() > 0);
    }

    @Test()
    public void whenRestoreThenReturnRestoredPost() {
        Post post = postService.restoreById(1L);

        assertNotNull(post);
        assertNotNull(post.getId());
        assertEquals(1L, post.getId());
        assertFalse(post.getDeleted());

        Mockito.when(postRepository.findByIdAndDeletedIsTrue(Mockito.any(Long.class)))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> postService.restoreById(1L));
    }

    @Test()
    public void whenFindAllByDateThenReturnAllPost() {
        Page<Post> posts = postService.findAllByDate(new PageImpl<>(buildPostList()).getPageable(),
                LocalDateTime.now().minusDays(30),
                LocalDateTime.now());

        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertTrue(posts.getTotalElements() > 0);
    }

    private Post buildPost() {
        Post post = Post.builder()
                .id(1L)
                .title("unit test")
                .description("unit test")
                .body("unit test")
                .build();

        post.setDeleted(false);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        return post;
    }

    private Post buildPostDeleted() {
        Post post = Post.builder()
                .id(1L)
                .title("unit test")
                .description("unit test")
                .body("unit test")
                .build();

        post.setDeleted(true);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setDeletedAt(LocalDateTime.now());

        return post;
    }

    private PostDTO buildPostDTO() {
        return PostDTO.builder()
                .title("unit test")
                .description("unit test")
                .body("unit test")
                .build();
    }

    private List<Post> buildPostList() {
        return Arrays.asList(buildPost(), buildPost());
    }

    private Page<Post> buildPostPage() {
        return new PageImpl<>(Arrays.asList(buildPost(), buildPost()));
    }
}
