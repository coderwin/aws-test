package com.example.awstestback.web.dto;

import com.example.awstestback.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMemberInfoDTO {


    private Long id;
    private String nickname;    // 아이디
    private String profileUrl;

    @Builder
    public ResponseMemberInfoDTO(Long id, String nickname, String profileUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }


    public static ResponseMemberInfoDTO create(Member member) {

        return ResponseMemberInfoDTO.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .build();

    }
}
