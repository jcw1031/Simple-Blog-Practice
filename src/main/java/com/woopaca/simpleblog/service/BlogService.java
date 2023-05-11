package com.woopaca.simpleblog.service;

import com.woopaca.simpleblog.domain.Article;
import com.woopaca.simpleblog.dto.AddArticleRequest;
import com.woopaca.simpleblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}