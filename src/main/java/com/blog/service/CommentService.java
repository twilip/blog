package com.blog.service;

import com.blog.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    void deletePost(long commentId);

    List<CommentDto> getAllCommentsbypostId(long postId);

    CommentDto updateComment(long commentId, CommentDto commentDto);

    List<CommentDto> getAllComments();
}
