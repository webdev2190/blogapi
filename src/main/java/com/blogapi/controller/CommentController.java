//package com.blogapi.controller;

//import com.blogapi.payload.CommentDto;
//import com.blogapi.service.CommentService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/")
//public class CommentController {
//
//    private CommentService commentService;
//
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    //TODO: http://localhost:8080/api/posts/1/comments
//    @PostMapping("/posts/{postId}/comments")
//    public ResponseEntity<CommentDto> createComment(
//            @PathVariable(value = "postId") long postId,
//            @RequestBody CommentDto commentDto){
//        CommentDto dto = commentService.createComment(postId, commentDto);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
//
//
//    @GetMapping("/posts/{postId}/comments")
//    public List<CommentDto> findCommentByPostId(@PathVariable(value = "postId") long postId){
//            List<CommentDto> dtos = commentService.findCommentByPostId(postId);
//        return dtos;
//    }
//
//}
//
//----------------------------------------------------------------Copy code----------------------------------------------------------------
package com.blogapi.controller;

import com.blogapi.payload.CommentDto;
import com.blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentByPostId(@PathVariable(value = "postId") long postId) {
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return dtos;
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id) {
        commentService.deleteCommentById(postId,id);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }

    @GetMapping ("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id) {
        CommentDto dto = commentService.getCommentById(postId,id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //TODO: http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{commentId}") //TODO Update comment 04/07/2023
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updateComment(postId,commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
}
