package com.blogapi.service.impl;

import com.blogapi.entity.Post;
import com.blogapi.exceptions.ResourceNotFoundException;
import com.blogapi.payload.PostDto;
import com.blogapi.repository.PostRepository;
import com.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class PostServiceImpl implements PostService { //todo: PHIR hume PostServiceImpl implementation yehe pe kerna hota hai jo bhi method PostService me create hua hoga usay hum aise he Implementation kerte hai.

    // So create a private PostRepository

    private PostRepository postRepo; //Instead of @Autowired annotation for dependency injection we can use Constructor based injection.
    //Post repository is bultin feature in spring boot.
    //TODO: Meaning right click -->goto Generate -->goto Constructor and hit Ok.
    //TODO: You can Dependency Injection into Two ways first @Autowired annotation and Second one is Construct based injection.

    // TODO: This is the constructor for the postRepo has generated.

    private ModelMapper modelMapper; //TODO 23/06/2023
    //In my project i have used ModelMapper library while using this libarary i had to copy the data from entity to Dto and Dto to entity i wanted to implement that i my project to reduce the number of linec of code.
    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper){
        this.postRepo = postRepo;
        this. modelMapper = modelMapper;//Construct based injection 23/06/2023
    }//Based on Spring Boot dependency injection object can not be created @Autowired or Constructor based injections can not work directly when it is externally libraries

    @Override
    public PostDto createPost(PostDto postDto) {

        // TODO: If you want to Convert postDto to mapToEntity 16/06/2023
        Post post = mapToEntity(postDto); //and mapToEntity will tack to the PostDto object.

        //Now here we will convert the PostDto to Entity because you can not save the data into this Dto.

//        Post post = new Post();//Create the entity object and Entity object can goto the database.
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent()); //TODO: 16/6/23  This this code is convert Post mapToEntity

        Post savedPost = postRepo.save(post);// Alt+enter to Introduce a local variable because I want to know  what is saved so that's why I have created a local variable
        // After you save the post it'll give a new object with the content telling what did be saved in the database.


//        PostDto dto = new PostDto();//Copy this and past inside the mapToDto
//        dto.setId(savedPost.getId());
//        dto.setTitle(savedPost.getTitle());
//        dto.setDescription(savedPost.getDescription());
//        dto.setContent(savedPost.getContent());


        PostDto dto = mapToDto(savedPost);//TODO:16/06/2023 Update features
        //mapToDto pass the Entity Object.

        return dto; //TODO: Now Return this dto back.

    }

    @Override
    public PostDto getPostById(long id){
      Post post = postRepo.findById(id).orElseThrow(
              ()->new ResourceNotFoundException(id)
      );

      PostDto dto = mapToDto(post);
        return dto; //TODO: Now Return
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //if you want use this sortDir then We need to use here Turnary operator is replacement of If/Else condition
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortDir).descending();

        Pageable pageable =  PageRequest.of(pageNo, pageSize, sort);//TODO: What you'll do Sort.by() is it'll Convert this String SortBy into Sort object
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> postDtos = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return postDtos;
    }


    @Override
    public void deletePost(long id) {//16/06/2023
        Post post = postRepo.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(id)
        );
        postRepo.deleteById(id);
    }

    @Override//TODO: updatePost implementation from postService 19/06/2023
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(id)
        );

       Post updatedContent = mapToEntity(postDto);
       updatedContent.setId(post.getId());

        Post updatePostInfo = postRepo.save(updatedContent);

        //Now Convert into Dto object
        return mapToDto(updatePostInfo);


    }

    PostDto mapToDto(Post post){//TODO:16/06/2023 ka work.
//        PostDto dto = new PostDto();
        PostDto dto = modelMapper.map(post,PostDto.class); //TODO: 23/06/2023 this 4 lines is reduced here you can see only use of modelMapper.
//        dto.setId(post.getId()); //savePost Convert into post
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;
    }

    Post mapToEntity(PostDto  postDto){// 16/6/23 Create the entity object to mapToEntity

        Post post = modelMapper.map(postDto, Post.class);//modelMapper.map(postDto) Alt+enter and select the local Variable

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post; //Return back to the entity
    }

}
