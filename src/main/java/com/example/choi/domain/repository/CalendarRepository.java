package com.example.choi.domain.repository;


import com.example.choi.domain.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository <Calendar, Long> {
     List<Calendar> findAllByCreator(String test);
}
