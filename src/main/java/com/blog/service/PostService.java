package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletepost(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePost( long id,PostDto postDto);
}
