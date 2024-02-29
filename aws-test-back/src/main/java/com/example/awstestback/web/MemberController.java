package com.example.awstestback.web;

import com.example.awstestback.service.MemberServiceImpl;
import com.example.awstestback.util.Result;
import com.example.awstestback.web.dto.RequestSignUpDTO;
import com.example.awstestback.web.dto.ResponseMemberInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<Result<List<ResponseMemberInfoDTO>>> selectAllWithNicknameAndImageUrl() {

        List<ResponseMemberInfoDTO> responseMemberInfoDTOList = memberServiceImpl.selectAllWithNicknameAndImageUrl();
        Result<List<ResponseMemberInfoDTO>> body = Result.success(
                responseMemberInfoDTOList,
                HttpStatus.OK.value(),
                HttpStatus.OK.name()
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



}
