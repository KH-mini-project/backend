package com.kh.banana.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.kh.banana.dto.request.PostRequestDTO;

public interface WriteService {
	ResponseEntity<?> savePost(PostRequestDTO dto);

	ResponseEntity<?> saveImage(MultipartFile postFile);
}
