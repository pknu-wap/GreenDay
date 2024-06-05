package com.example.repository;

import com.example.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByIdAndUserEmail(Long boardId, String userEmail);
    Page<BoardEntity> findAll(Pageable pageable);
}


