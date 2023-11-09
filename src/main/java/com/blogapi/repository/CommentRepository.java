package com.blogapi.repository;

import com.blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPostId(Long id);//todo:whenever you write starting method starts with findBy nad the Column name (findByPostId) PostId is Column name this will automatically internally start write MySQL query

    //how will you find the record
//    List<Comment> findByEmail(String email);//todo: This way you can find the records.

//    List<Comment>findByName(String name);//todo: this is called Custom query. Now you can find the records by name.

    //How can you a return a Boolean value?
//    boolean existByEmail(String email); //todo: this is how you can return a values by boolean value

    //TODO: How you can use And Operators
//    List<Comment> findByPostIdandEmail(long id, String email); //This way you can use AND Operators in Hibernate you can use OR Operators as well
}
