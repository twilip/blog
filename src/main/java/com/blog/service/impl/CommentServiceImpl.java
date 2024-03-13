package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepo;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepository) {
        this.postRepo = postRepo;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found of this id" + postId)
        );
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public void deletePost(long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found")
        );
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getAllCommentsbypostId(long postId) {

        List<Comment> commets = commentRepository.findByPostId(postId);
        List<CommentDto> dto = commets.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("commment not found")
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment save = commentRepository.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> dto = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dto;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;
    }
}
