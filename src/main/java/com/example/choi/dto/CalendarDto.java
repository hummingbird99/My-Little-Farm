package com.example.choi.dto;


import com.example.choi.domain.entity.Calendar;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CalendarDto {
    private Long idx;
    private String strmonth;
    private String strdate;
    private String creator;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Calendar toEntity() {
        Calendar build = Calendar.builder()
                .idx(idx)
                .strmonth(strmonth)
                .strdate(strdate)
                .creator(creator)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public CalendarDto(Long idx, String strmonth, String strdate, String creator, String content,  LocalDateTime createDate,
                       LocalDateTime modifyDate) {

        this.idx = idx;
        this.strdate = strdate;
        this.strmonth = strmonth;
        this.creator = creator;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;

    }
}