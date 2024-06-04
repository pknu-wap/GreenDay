package com.example.controller;

import com.example.config.JwtTokenProvider;
import com.example.domain.entity.BoardEntity;
import com.example.dto.BoardDto;
import com.example.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // @Autowired 어노테이션을 사용하여 JwtTokenProvider를 주입
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            boardService.write(boardDto, userId);
            return ResponseEntity.ok("작성을 완료하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardDto boardDto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            boardService.update(id, boardDto, userId);
            return ResponseEntity.ok("수정을 완료하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 실패하였습니다.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            boardService.delete(id, userId);
            return ResponseEntity.ok("삭제를 완료하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 실패하였습니다.");
        }
    }

    // 페이징 결과를 클라이언트에 반환하는 메서드 추가
    @GetMapping("/list")
    public ResponseEntity<Page<BoardEntity>> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "7") int size) {
        Page<BoardEntity> boardPage = boardService.getBoardList(page, size);
        return ResponseEntity.ok(boardPage);
    }
}
