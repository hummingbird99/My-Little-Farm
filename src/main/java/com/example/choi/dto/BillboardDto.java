package com.example.choi.dto;


import com.example.choi.domain.entity.Billboard;
import com.example.choi.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BillboardDto {
    private Long id;
    private String userid;
    private String username;
    private String title;
    private String content;
    private Long fileId;
    private String bbstype;
    private Integer readcnt;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Billboard toEntity() {
        Billboard build = Billboard.builder()
                .id(id)
                .userid(userid)
                .username(username)
                .title(title)
                .ir1(content)
                .fileId(fileId)
                .readcnt(readcnt)
                .bbstype(bbstype)
                .build();
        return build;
    }

    @Builder
    public BillboardDto(Long id, String userid, String username, String title, String content, Long fileId, String bbstype, Integer readcnt, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.bbstype = bbstype;
        this.readcnt = readcnt;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}