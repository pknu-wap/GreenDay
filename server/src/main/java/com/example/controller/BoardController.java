//package com.example.controller;
//
//import com.example.config.JwtTokenProvider;
//import com.example.domain.entity.BoardEntity;
//import com.example.dto.BoardDto;
//import com.example.service.BoardService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/board")
//public class BoardController {
//
//    @Autowired
//    private BoardService boardService;
//
//    // @Autowired 어노테이션을 사용하여 JwtTokenProvider를 주입
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    // 게시글 작성
//    @PostMapping("/write")
//    // ? 부분 -> String으로 바꿔도 상관없음, 통일하면 더 명확해짐
//    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            String userId = jwtTokenProvider.getUserId(token);
//            Long postId = boardService.write(boardDto, userId); // 생성된 게시글 ID
//            return ResponseEntity.ok(postId); // ID를 프론트엔드에 반환
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 실패하였습니다.");
//        }
//    }
//
//    // 게시글 수정
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardDto boardDto, HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            String userId = jwtTokenProvider.getUserId(token);
//            boardService.update(id, boardDto, userId);
//            return ResponseEntity.ok("수정을 완료하였습니다.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 실패하였습니다.");
//        }
//    }
//
//    // 게시글 삭제
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            String userId = jwtTokenProvider.getUserId(token);
//            boardService.delete(id, userId);
//            return ResponseEntity.ok("삭제를 완료하였습니다.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 실패하였습니다.");
//        }
//    }
//
//    // 페이징 결과를 클라이언트에 반환하는 메서드 추가
//    @GetMapping("/list")
//    public ResponseEntity<Page<BoardDto>> list(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "7") int size) {
//        // 데이터베이스로부터 페이징 처리된 게시글 목록을 조회하는 핵심 로직
//        Page<BoardDto> dtoPage = boardService.getBoardList(page, size);
//        // BoardEntity 객체를 직접 클라이언트에 반환하지 않고, BoardDto 객체를 이용해 반환
//        return ResponseEntity.ok(dtoPage);
//    }
//}