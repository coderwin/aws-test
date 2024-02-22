package com.example.awstestback.web;

import com.example.awstestback.service.MemberServiceImpl;
import com.example.awstestback.util.Result;
import com.example.awstestback.web.dto.RequestSignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping
    public ResponseEntity<Result<Void>> create(
            @RequestPart RequestSignUpDTO requestSignUpDto,
            @RequestPart(required = false) MultipartFile profileImage
            ) {

        memberServiceImpl.create(requestSignUpDto, profileImage);

        Result<Void> body = Result.success();

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }



}
