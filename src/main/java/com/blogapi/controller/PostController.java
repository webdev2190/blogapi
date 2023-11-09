package com.blogapi.controller;

import com.blogapi.payload.PostDto;
import com.blogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/posts")//this will tell us these are api url not a regular URL
public class PostController {

    //why responseEntity because ResponseEntity will return back a postman you can see the response section in the postman.
    // responseEntity will return back to the PostDto object
    //http://localhost:8080/api/posts //Copy this URL and paste it into postman and create some data that data go to the ControllerDto
    // iss url ko past kerne se @PostMapping ko call krega or Data de dega.

    private PostService postService; //todo: Here also i do the same thing injecting the dependencies with the help of Construct dependencies injection.


    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts

    @PreAuthorize("hasRole('ADMIN')")//What does it mean of this line of code is only when you login as admin you can create a new post.

    @PostMapping//TODO: if don't write this @Valid Annotations will note happened 22/06/2023
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) { //TODO: Status code = 201
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDto savedDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED); //TODO: Wherever we save you will see a status code and I want as Created (HttpStatus.CREATED) this will not.

    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){//TODO: Status code = 200
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK); //TODO: Ok status code means 200.
    }

    //TODO: Pagination Concept
    @GetMapping
//            //http://localhost:8080/api/posts?pageNo=3
//            (//Here we are going to create Pagination and How does it work. 20/06/2023
                   //ToDO: @RequestParam(value = "pageNo", defaultValue = "0") long pageNo; //This Pagination only works inside the Methods Only.
//            )

    public List<PostDto> getAllPost( //http://localhost:8080/api/posts?pageNo=1&pageSize=3
                                     //This Methods Calls a Handler methods that will read the value pageNo 0 is number one page.
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo, //TODO: This Pagination only works inside the Methods Only 20/06/2023
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,//sortBy Sort the Records.21/06/2023
                                     //http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title
                                     //You can sortBy anything by id, title, content, email sortBy=title link
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
                                     //http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title&sortDir=desc
                                     //You can sortDir means you can sorting in a descending order as well.
    ){
        List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy, sortDir);//todo: Create this way and put your cursor infront of getAllPost press alt+Enter and create a method to get all
        //Yaha pe getAllPosts(pageNo, pageSize) mention kiya hai wo place pe reflect hoga postService and PostServiceImpl me bhi change hoga ap check ker sakte ho
        return postDtos;
        }
        //Todo Delete A Record 16/06/2023
        //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")//Only when you login as admin you can delete a post
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePost(@PathVariable("id") long id){
            postService.deletePost(id);
            return new ResponseEntity<>("Post is Deleted!! ", HttpStatus.OK); //TODO: Ok status code means 200
        }

    //TODO: Updating a database 19/06/2023
    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")//Only when you login as admin you can update a post
    @PutMapping("/{id}") //TODO: PutMapping is for updating the database via POSTMan.
    public ResponseEntity<PostDto>updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){ //(@PathVariable("id") long id) is read the ID.
        //JSON ko kaise read kerna hai. to @RequestBody PostDto postDto ki help se JSON ko Read Krega.
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
