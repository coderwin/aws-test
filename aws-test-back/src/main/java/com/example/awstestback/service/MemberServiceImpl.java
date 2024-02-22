package com.example.awstestback.service;

import com.example.awstestback.domain.Member;
import com.example.awstestback.repository.MemberRepository;
import com.example.awstestback.web.dto.RequestSignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    @Transactional
    public void create(RequestSignUpDTO requestSignUpDto, MultipartFile profileImage) {

        // TODO : 아이디 중복 검사

        // profileImage를 S3의 url로 바꾸기
        String profileUrl = null;

        // Member 객체 생성
        Member newMember = Member.create(requestSignUpDto, profileUrl);

        // 저장하기
        memberRepository.save(newMember);

    }
}
