package com.blog.controller;

import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId,@RequestBody CommentDto commentDto){
      CommentDto dto=  commentService.createComment(postId,commentDto);
return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deletePost(@PathVariable long commentId){
        commentService.deletePost(commentId);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsbypostId(@PathVariable long postId){
      List<CommentDto>dtos=  commentService.getAllCommentsbypostId(postId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);

    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long commentId,@RequestBody CommentDto commentDto){
       CommentDto dto= commentService.updateComment(commentId,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<CommentDto>>getAllComments( ){
   List<CommentDto>   dto= commentService.getAllComments();
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
