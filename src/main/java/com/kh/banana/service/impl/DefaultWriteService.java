package com.kh.banana.service.impl;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.banana.dto.request.PostImageResponseDTO;
import com.kh.banana.dto.request.PostRequestDTO;
import com.kh.banana.dto.response.PostIdResponseDTO;
import com.kh.banana.entity.PostEntity;
import com.kh.banana.entity.UserEntity;
import com.kh.banana.repository.PostRepository;
import com.kh.banana.repository.UserRepository;
import com.kh.banana.service.WriteService;
import com.kh.banana.util.S3ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultWriteService implements WriteService {
	
	private final UserRepository userRepo;
	private final PostRepository postRepo;
	private final S3ServiceUtil s3ServiceUtil;
	
	@Override
	public ResponseEntity<?> savePost(PostRequestDTO dto) {
		UserEntity entity = userRepo.findById(dto.getUser()).get();
		PostEntity post = postRepo.save(dto.toEntity(entity));
		PostIdResponseDTO responseDto = PostIdResponseDTO.fromEntity(post);
		return ResponseEntity.ok(responseDto);
	}

	@Override
	public ResponseEntity<?> saveImage(MultipartFile postFile) {
		String imageUrl = "이미지 로딩 실패";
		try {
			imageUrl = s3ServiceUtil.uploadFile(postFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(imageUrl);
		return ResponseEntity.ok(imageUrl);
	}

}
