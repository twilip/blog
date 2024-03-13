package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post saved = postRepo.save(post);
        PostDto dto=new PostDto();
        dto.setId(saved.getId());
        dto.setTitle(saved.getTitle());
        dto.setDescription(saved.getDescription());
        dto.setContent(saved.getContent());

        return dto;
    }

    @Override
    public void deletepost(long id) {
        postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post is not found of this id" + id)
        );

        postRepo.deleteById(id);

    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy):Sort.by(sortBy);
Pageable pageable=PageRequest.of(pageNo,pageSize,sort);

        Page<Post> pagepost = postRepo.findAll(pageable);

        List<Post> post = pagepost.getContent();
        List<PostDto> dto = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public PostDto updatePost(long id,PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post not found this id")
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post saved = postRepo.save(post);
        PostDto dto = mapToDto(saved);
        return dto;
    }

    PostDto mapToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

}
