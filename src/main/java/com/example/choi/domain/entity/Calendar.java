package com.example.choi.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
@Table(name="mypagelist")
public class Calendar {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "strmonth")
    private String strmonth;

    @Column(name = "strdate")
    private String strdate;

    @Column(length = 10, nullable = false)
    private String creator;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Builder
    public Calendar(Long idx, String strdate,String strmonth, String creator, String content) {
        this.idx = idx;
        this.strmonth = strmonth;
        this.strdate = strdate;
        this.creator = creator;
        this.content = content;
    }
}