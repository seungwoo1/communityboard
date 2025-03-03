package com.community.communityboard.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션 (엔티티, 테이블 매핑 등)
import lombok.Getter; // Lombok: getter 메서드 자동 생성
import lombok.NoArgsConstructor; // Lombok: 기본 생성자 자동 생성
import lombok.Setter; // Lombok: setter 메서드 자동 생성
import java.time.LocalDateTime; // Java의 날짜 및 시간 API

/**
 * 게시글 엔티티 (Post)
 * - 데이터베이스의 posts 테이블과 매핑됨
 * - 제목, 내용, 작성자, 생성/수정일을 포함
 */
@Entity // JPA 엔티티 클래스로 선언 (데이터베이스 테이블과 매핑)
@Table(name = "posts") // 테이블 이름을 'posts'로 지정
@Getter // Lombok: 모든 필드의 getter 자동 생성
@Setter // Lombok: 모든 필드의 setter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성 (JPA에서 필요함)
public class Post {
    @Id // 기본 키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (Auto Increment)
    private Long id; // 게시글 ID

    @Column(nullable = false) // NOT NULL 설정값
    private String title; // 게시글 제목

    @Column(columnDefinition = "TEXT", nullable = false) // 긴 텍스트 저장 가능 (NOT NULL)
    private String content; // 게시글 내용

    @Column(nullable = false) // NOT NULL 설정
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성일 (기본값: 현재 시간)

    @Column(nullable = false) // NOT NULL 설정
    private LocalDateTime updatedAt = LocalDateTime.now(); // 수정일 (기본값: 현재 시간)



}
