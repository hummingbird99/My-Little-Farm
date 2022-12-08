package com.example.choi.service;


import com.example.choi.domain.entity.Calendar;
import com.example.choi.domain.repository.CalendarRepository;
import com.example.choi.dto.CalendarDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {
    private CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Transactional
    public Long savePost(CalendarDto calendarDto) {return calendarRepository.save(calendarDto.toEntity()).getIdx(); }

    @Transactional
    public List<CalendarDto> getCalendarSubList(String creator) {
        List<Calendar> calendarList = calendarRepository.findAllByCreator( creator);
        List<CalendarDto> calendarDtoList = new ArrayList<>();

        for(Calendar calendar : calendarList) {
            CalendarDto calendarDto = CalendarDto.builder()
                    .idx(calendar.getIdx())
                    .strdate(calendar.getStrdate())
                    .strmonth(calendar.getStrmonth())
                    .creator(calendar.getCreator())
                    .content(calendar.getContent())
                    .createDate(calendar.getCreateDate())
                    .build();
            calendarDtoList.add(calendarDto);
        }
        return calendarDtoList;
    }

    @Transactional
    public void deletePost(Long idx) {
        calendarRepository.deleteById(idx);
    }
}
