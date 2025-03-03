package com.community.communityboard.repository;


import com.community.communityboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Post 엔티티의 데이터베이스 접근을 담당하는 리포지토리
 * - JpaRepository를 상속받아 기본적인 CRUD 기능을 제공함.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 모든 게시글을 ID 기준으로 내림차순 정렬하여 조회
    List<Post> findAllByOrderByIdDesc();

    /**
     * 제목 또는 내용에 검색어가 포함된 게시글을 찾는 메소드
     * @param title 제목에 포함된 검색어
     * @param content 내용에 포함된 검색어
     * @return 검색된 게시글 리스트
     * findByTitle: title 필드를 기준으로 찾기
     * Containing: 해당 값이 포함된 결과를 찾기
     * IgnoreCase: 대소문자 구분 없이 찾기
     */
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

}
