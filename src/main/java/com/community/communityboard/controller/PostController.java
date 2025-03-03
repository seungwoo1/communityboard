package com.community.communityboard.controller;

import com.community.communityboard.entity.Post;
import com.community.communityboard.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 모든 게시글 조회 API
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 특정 게시글 조회 (단건 조회)
    @GetMapping("/{id}") // ?
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if(post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환함
        }
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        // 게시글 작성 로직
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postService.savePost(post); // 저장소에 게시글 저장

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 성공적으로 작성되었습니다.");
    }

    @PutMapping("/{id}") // PUT 메소드로 요청을 받음, {id}는 수정할 게시글의 ID
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * 게시글 삭제 API
     * - 요청 URL: DELETE /posts/{id}
     * - 주어진 ID로 게시글을 삭제
     */
    @DeleteMapping("/{id}") // HTTP DELETE 요청을 처리하는 메소드
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        boolean isDeleted = postService.deletePost(id); // 서비스에서 삭제 처리

        if(isDeleted) {
            return ResponseEntity.ok("게시글 삭제 성공했습니다"); // 삭제 성공 시
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다."); // 게시글이 없으면
        }
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String searchTerm) {
        return postService.searchPosts(searchTerm);
    }

}
