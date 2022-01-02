package desafio.desafiojrblog.controller;

import desafio.desafiojrblog.domain.Post;
import desafio.desafiojrblog.domain.dto.PostDTO;
import desafio.desafiojrblog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "", produces = "application/json")
    @Operation(description = "Save new Post")
    public ResponseEntity<Post> save(@RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postDTO));
    }

    @PutMapping(value = "{id}", produces = "application/json")
    @Operation(description = "Update a Post")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.update(id, post));
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    @Operation(description = "Delete a Post by ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "restore/{id}", produces = "application/json")
    @Operation(description = "Restore a Post by ID")
    public ResponseEntity<Post> restoreById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.restoreById(id));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    @Operation(description = "Find a Post by ID")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value = "", produces = "application/json")
    @Operation(description = "Find all Posts")
    public ResponseEntity<Page<Post>> findAll(Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @GetMapping(value = "date", produces = "application/json")
    @Operation(description = "Find all Posts by startDate and endDate")
    public ResponseEntity<Page<Post>> findAllByDate(Pageable pageable,
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern="dd/MM/yyyy HH:mm") @RequestParam(name = "startDate") LocalDateTime startDate,
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern="dd/MM/yyyy HH:mm") @RequestParam(name = "endDate") LocalDateTime endDate) {
        return ResponseEntity.ok(postService.findAllByDate(pageable, startDate, endDate));
    }

}