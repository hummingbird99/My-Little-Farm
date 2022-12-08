package com.example.choi.domain.repository;

import com.example.choi.domain.entity.Billboard;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@SuppressWarnings("ALL")
@Repository
//public interface BoardRepository extends JpaRepository<Users,Integer> {}

public interface BillboardRepository extends JpaRepository <Billboard, Long> {
    List<Billboard>  findAllByBbstype(String bbstype, Sort sort);

    //List<Billboard>  findAllByBbstypeByOrderByModifiedDateDesc(String bbstype, Sort sort);

    @Modifying    @Query("update Billboard p set p.readcnt = p.readcnt + 1 where p.id = :id")
    int updateView(Long id);

}
