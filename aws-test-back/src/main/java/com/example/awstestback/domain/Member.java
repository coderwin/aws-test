package com.example.awstestback.domain;

import com.example.awstestback.web.dto.RequestSignUpDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;    // 이름
    @Column(nullable = false)
    private String nickname;    // 아이디
    private String introduction;// 짧은 소개
    private String profileUrl;

    @Builder
    public Member(String username, String nickname, String introduction, String profileUrl) {
        this.username = username;
        this.nickname = nickname;
        this.introduction = introduction;
        this.profileUrl = profileUrl;
    }

    // 생성 로직
    public static Member create(RequestSignUpDTO requestSignUpDto, String profileUrl) {

       return Member.builder()
                .username(requestSignUpDto.getUsername())
                .nickname(requestSignUpDto.getNickname())
                .introduction(requestSignUpDto.getIntroduction())
                .profileUrl(profileUrl)
                .build();
    }
}
