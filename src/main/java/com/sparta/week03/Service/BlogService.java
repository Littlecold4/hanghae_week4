package com.sparta.week03.Service;

import com.sparta.week03.domain.Blog;
import com.sparta.week03.repository.BlogRepository;
import com.sparta.week03.dto.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new NullPointerException("아이디가 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return id;
    }
}
