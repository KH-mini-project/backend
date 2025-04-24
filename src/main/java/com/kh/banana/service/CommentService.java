package com.kh.banana.service;

import com.kh.banana.dto.request.CommentRequestDTO;
import com.kh.banana.dto.response.CommentResponseDTO;
import com.kh.banana.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity saveComment(CommentRequestDTO requestDTO, Long postId, Long userId);

    List<CommentResponseDTO> getCommentsByPostId(Long postId);
}
