package com.blogapi.service; //as we have created this file inside the service.iml
// package folder after this we don't want to inside the impl folder remove the impl the alt+enter move to the service folder.

import com.blogapi.payload.PostDto;

import java.util.List;

public interface PostService {


    public PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir); //todo:16/06/2023 Jo bhi hum PostController me method Create kerte hai uska method PostService me Create hota hai jaise ke ye or hum issaye PostServiceImpl me Implementation bhi kerte hai.

    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);

}
