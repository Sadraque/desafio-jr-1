package desafio.desafiojrblog.service;

import desafio.desafiojrblog.domain.Post;
import desafio.desafiojrblog.domain.dto.PostDTO;
import desafio.desafiojrblog.exception.NotFoundException;
import desafio.desafiojrblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Post save(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity());
    }

    @Transactional
    public Post update(Long id, Post post) {
        Post oldEntity = findById(id);

        post.setId(id);
        post.setCreatedAt(oldEntity.getCreatedAt());

        return postRepository.save(post);
    }

    @Transactional
    public void delete(Long id, Boolean permanentlyRemove) {
        Post entity = findById(id);

        if(permanentlyRemove) {
            postRepository.deleteById(id);

        } else {
            entity.setDeleted(true);
            update(id, entity);
        }
    }

    @Transactional
    public void delete(Long id) {
        delete(id, false);
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found!"));
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post restoreById(Long id) {
        Post post = findById(id);

        if (post != null) {
            post.setDeleted(false);
            post = postRepository.save(post);
        }

        return post;
    }

    public Page<Post> findAllByDate(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        return postRepository.findAllByCreatedAtBetween(pageable, startDate, endDate);
    }
}
