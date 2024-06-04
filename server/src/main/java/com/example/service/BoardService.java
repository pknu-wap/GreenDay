package com.example.service;

import com.example.domain.entity.BoardEntity;
import com.example.dto.BoardDto;
import com.example.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 페이징 메서드를 구현
    public Page<BoardEntity> getBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public void write(BoardDto boardDto, String userEmail) {
        // DTO를 엔티티로 변환
        BoardEntity boardEntity = boardDto.toEntity();
        boardEntity.setUserEmail(userEmail); // 유저 이메일 설정
        boardRepository.save(boardEntity);
    }

    @Transactional
    public void update(Long id, BoardDto boardDto, String userEmail) {
        // 존재하는 게시글을 찾고, 사용자 이메일로 권한 확인
        BoardEntity boardEntity = boardRepository.findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않거나 권한이 없습니다."));

        // 게시글 내용 업데이트
        boardEntity.setContent(boardDto.getContent());
        boardRepository.save(boardEntity);
    }

    @Transactional
    public void delete(Long id, String userEmail) {
        // 존재하는 게시글을 찾고, 사용자 이메일로 권한 확인
        BoardEntity boardEntity = boardRepository.findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않거나 권한이 없습니다."));

        // 게시글 삭제
        boardRepository.delete(boardEntity);
    }
}
