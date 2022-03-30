package com.sparta.week03.repository;

import com.sparta.week03.domain.BlogNice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NiceRepository extends JpaRepository<BlogNice, Long> {
    List<BlogNice> findAllByBlogId(Long blogId);
}
