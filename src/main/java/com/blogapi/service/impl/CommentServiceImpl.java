//package com.blogapi.service.impl;
//
//import com.blogapi.entity.Comment;
//import com.blogapi.entity.Post;
//import com.blogapi.exceptions.ResourceNotFoundException;
//import com.blogapi.payload.CommentDto;
//import com.blogapi.repository.CommentRepository;
//import com.blogapi.repository.PostRepository;
//import com.blogapi.service.CommentService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service //Todo: This Annotations is called Stereotype. Stereotype it means the class which is in ordinary java class i', handing over the management of the class in spring IOC so that spring ioc can mention the bean life cycle of the object created with that class. otherwise will mention by Spring IOC.
//public class CommentServiceImpl implements CommentService {
//
//    private PostRepository postRepo;
//    private CommentRepository commentRepo;
//
////    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepo) {
////        this.postRepo = postRepo;
////        this.commentRepo = commentRepo;
////    }
//
//    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
//        this.postRepo = postRepo;
//        this.commentRepo = commentRepo;
//    }
//
//    @Override
//    public CommentDto createComment(long postId, CommentDto commentDto) {
//        Post post = postRepo.findById(postId).orElseThrow(
//                ()-> new ResourceNotFoundException(postId)
//        );
//
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//
//        comment.setPost(post); // TODO: Saving the comment for this post
//
//        Comment savedComment = commentRepo.save(comment);
//
//        CommentDto dto = new CommentDto();
//        dto.setId(savedComment.getId());
//        dto.setName(savedComment.getName());
//        dto.setEmail(savedComment.getEmail());
//        dto.setBody(savedComment.getBody());
//
//        return dto;
//    }
//
//    @Override
//    public List<CommentDto> findCommentById(long PostId) {
//        return null;
//    }
//
//    public List<CommentDto> findCommentByPostId(long postId) {//please this incomlete method is been return by you in the CommentService layer
//        Post post = postRepo.findById(postId).orElseThrow(
//                ()-> new ResourceNotFoundException(postId)
//        );
//        List<Comment> comments = commentRepo.findByPostId(postId);
//       return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
//
//
//    }
//    CommentDto mapToDto(Comment comment){
//        CommentDto dto = new CommentDto();
//        dto.setId(comment.getId());
//        dto.setName(comment.getName());
//        dto.setEmail(comment.getEmail());
//        dto.setBody(comment.getBody());
//        return dto;
//    }
//}
package com.blogapi.service.impl;

import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.exceptions.ResourceNotFoundException;
import com.blogapi.payload.CommentDto;
import com.blogapi.repository.CommentRepository;
import com.blogapi.repository.PostRepository;
import com.blogapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());

        return dto;
    }
    @Override
    public List<CommentDto> findCommentByPostId(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(long postId, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

       Comment comment = commentRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

       commentRepo.deleteById(id);
    }

    @Override
    public CommentDto getCommentById(long postId, long id) {

        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        Comment comment = commentRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        CommentDto commentDto = mapToDto(comment);

        return commentDto;
    }

    @Override // TODO: UpdateComments 04/07/2023
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException( postId));

        // retrieve comment by id
        Comment comment = commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException(commentId));


        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepo.save(comment);
        return mapToDto(updatedComment);
    }//End of Update comment code.


    @Override
    public List<CommentDto> findCommentById(long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}

