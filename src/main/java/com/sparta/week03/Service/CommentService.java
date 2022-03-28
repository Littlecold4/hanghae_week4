package com.sparta.week03.Service;

import com.sparta.week03.domain.Comment;
import com.sparta.week03.dto.CommentEditRequestDto;
import com.sparta.week03.dto.CommentRequestDto;
import com.sparta.week03.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(CommentRequestDto requestDto, Long blogId,Long userId){
        Comment comment = new Comment(requestDto,blogId,userId);
        
        commentRepository.save(comment);
        
        return comment;
    }
    public Comment updateComment(Long id, CommentEditRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException()
        );
        String content = requestDto.getContent();
        comment.setContent(content);
        return comment;
    }
    public List<Comment> getComments(Long blogId){

        return commentRepository.findAllByBlogId(blogId);
    }

    @Transactional
    public Long update(Long id, CommentEditRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new NullPointerException("아이디기 존재하지 않습니다.")
        );
        String content = requestDto.getContent();
        comment.setContent(content);
        commentRepository.save(comment);
        return id;
    }
}
