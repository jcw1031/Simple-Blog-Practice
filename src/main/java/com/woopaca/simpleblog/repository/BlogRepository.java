package com.woopaca.simpleblog.repository;

import com.woopaca.simpleblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
