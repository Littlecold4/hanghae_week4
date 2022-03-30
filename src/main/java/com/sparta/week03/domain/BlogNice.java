package com.sparta.week03.domain;

import com.sparta.week03.dto.NiceRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BlogNice {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long blogId;

    @Column(nullable = false)
    private String nice;

    public BlogNice(NiceRequestDto requestDto){
        this.nice = requestDto.getNice();
        this.blogId = requestDto.getBlogId();
        this.username = requestDto.getUsername();
    }
}