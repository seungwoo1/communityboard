package com.community.communityboard.service;

import com.community.communityboard.entity.Post;
import com.community.communityboard.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    /**
     * 저장된 모든 게시글을 조회
     */
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    /**
     * 특정 게시글 조회
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null); // 해당 id의 게시글을 찾거나 없으면 null
    }

    /**
     * 게시글을 저장하는 메소드
     */
    public Post savePost(Post post) {
        // 기본적으로 생성일과 수정일이 자동으로 설정되게 만들어둠
        return postRepository.save(post); // PostRepository의 save 메소드 사용
    }

    /**
     * 게시글 수정
     * - 게시글 ID를 기준으로 조회 후, 수정된 값을 저장
     */
    public Post updatePost(Long Id, Post post) {
        // 1. 게시글을 ID로 조회
        Optional<Post> existingPost = postRepository.findById(Id);

        if(existingPost.isPresent()) {
            // 2. 기존 게시글이 존재하면, 수정할 필드만 덮어쓰기
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(post.getTitle()); // 제목 수정
            updatedPost.setContent(post.getContent()); // 내용 수정
            updatedPost.setUpdatedAt(post.getUpdatedAt()); // 수정일 업데이트

            // 3. 수정된 게시글 저장
            return postRepository.save(updatedPost);
        } else {
            // 게시글이 존재하지 않으면 null 반환 또는 예외 처리
            return null;
        }
    }

    public boolean deletePost (Long id) {
        // 1. ID로 게시글을 조회
        Optional<Post> existingPost = postRepository.findById(id);

        // 2. 게시글이 존재하면 삭제
        if(existingPost.isPresent()) {
            postRepository.deleteById(id); // 게시글 삭제
            return true;
        } else {
            return false; // 게시글이 존재하지 않음
        }
    }

    /**
     * 제목이나 내용으로 게시글 검색
     * @param searchTerm 검색어 (제목 또는 내용)
     * @return 검색된 게시글 리스트
     */

    public List<Post> searchPosts(String searchTerm) {
        return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm, searchTerm);
    }
}
