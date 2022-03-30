package com.sparta.week03.controller;

import com.sparta.week03.domain.Blog;
import com.sparta.week03.domain.BlogNice;
import com.sparta.week03.dto.NiceRequestDto;
import com.sparta.week03.repository.BlogRepository;
import com.sparta.week03.dto.BlogRequestDto;
import com.sparta.week03.repository.NiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final NiceRepository niceRepository;


    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    @GetMapping("/api/blogs")
    public List<Blog> readBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/niceblog/{Blogid}")
    public List<BlogNice> howmanynice(@PathVariable Long Blogid){
        return niceRepository.findAllByBlogId(Blogid);
    }

    @PostMapping("/api/niceblog")
    public void pressNice(@RequestBody NiceRequestDto requestDto){
        BlogNice blogNice = new BlogNice(requestDto);
        niceRepository.save(blogNice);
    }

    @DeleteMapping("/api/niceblog/{id}")
    public void deleteNice(@PathVariable Long id){
        niceRepository.deleteById(id);
    }



//    @GetMapping("/datail/{id}")
//    public ModelAndView readDetail3(@PathVariable Long id){
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                ()->new NullPointerException()
//        );
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("response",blog);
//        return mav;
//    }

//@GetMapping("/datail")
//    public ModelAndView readDetail3(){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("detail.html");
//        return mav;
//    }

//    @GetMapping("/detail/{id}")
//    public Blog readDetail2(@PathVariable Long id){
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                ()->new NullPointerException()
//        );
//        return blog;
//    }

    @GetMapping("/api/blogs/{id}")
    public Blog readDetail(@PathVariable Long id){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new NullPointerException()
        );
        return blog;
    }


//    @DeleteMapping("/api/blogs/{id}")
//    public Long deleteBlog(@PathVariable Long id) {
//        blogRepository.deleteById(id);
//        return id;
//    }
//
//
//    @PutMapping("/api/blogs/{id}")
//    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
//        blogService.update(id, requestDto);
//        return id;
//    }
}


