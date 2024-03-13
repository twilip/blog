package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.service.PostService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDto dto = postService.createPost(postDto);
return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletepost(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getallPost(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false)String  sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir


    ){
      List<PostDto> dtos= postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
      return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestParam("postId") long id,@RequestBody PostDto postDto){
       PostDto dto= postService.updatePost(id,postDto);
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
