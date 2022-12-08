package com.example.choi.domain.repository;


import com.example.choi.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface BoardRepository extends JpaRepository<Users,Integer> {}

public interface BoardRepository extends JpaRepository <Board, Long> {
}
