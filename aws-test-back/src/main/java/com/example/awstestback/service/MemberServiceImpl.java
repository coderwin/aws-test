package com.example.awstestback.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.awstestback.domain.Member;
import com.example.awstestback.repository.MemberRepository;
import com.example.awstestback.web.dto.RequestSignUpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.upload.profile.image-url}")
    private String s3UploadUrl;
    @Value("${cloud.aws.s3.upload.profile.key}")
    private String s3UploadKey;

    private final AmazonS3Client amazonS3Client;

    private final MemberRepository memberRepository;

    @Transactional
    public void create(RequestSignUpDTO requestSignUpDto, MultipartFile profileImage) {

        log.info("************ bucket : {} *************", bucket);
        log.info("************ s3UploadUrl : {} *************", s3UploadUrl);
        log.info("************ s3UploadKey : {} *************", s3UploadKey);

        // TODO : 아이디 중복 검사

        // profileImage를 S3의 url로 바꾸기
        String profileUrl = null;

        // **************** s3에 파일 등록 시작 **************
        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(profileImage.getContentType());
        objectMetadata.setContentLength(profileImage.getSize());

        // 저장되는 파일 이름 생성
        String originalFilename = profileImage.getOriginalFilename();
        int position = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(position + 1);
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + "." + ext;

        // db 저장될 url
        profileUrl = s3UploadUrl + newFileName;


        try {
            // 파일 데이터 불러오기
            InputStream inputStream = profileImage.getInputStream();

            // s3 파일 저장
            PutObjectResult putObjectResult = amazonS3Client.putObject(
                    new PutObjectRequest(
                            bucket,
                            s3UploadKey + newFileName,
                            inputStream,
                            objectMetadata
                    )
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String contentMd5 = putObjectResult.getContentMd5();
            log.info("********** contentMd% : {} ************", contentMd5);

        } catch (IOException e) {
            log.info("S3 파일 업로드에 실팼습니다. {}", e.getMessage());
            throw new IllegalStateException("S3 파일 업로드에 실팼습니다.", e);
        }

        // **************** s3에 파일 등록 끝 **************

        // Member 객체 생성
        Member newMember = Member.create(requestSignUpDto, profileUrl);

        // 저장하기
        memberRepository.save(newMember);

    }
}
