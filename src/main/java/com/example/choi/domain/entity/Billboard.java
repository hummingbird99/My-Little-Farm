package com.example.choi.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Billboard {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, nullable = false)
    private String userid;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String bbstype;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private Long fileId;

    @Column
    @ColumnDefault("0")
    private Integer readcnt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Billboard(Long id, String userid, String username, String title, String ir1, Long fileId, String bbstype, Integer readcnt) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.title = title;
        this.content = ir1;
        this.fileId = fileId;
        this.bbstype = bbstype;
        this.readcnt = readcnt;

    }

}