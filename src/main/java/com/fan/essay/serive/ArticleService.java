package com.fan.essay.serive;

import com.fan.essay.pojo.PageBean;
import com.fan.essay.pojo.Article;

public interface ArticleService {
    // 添加文章
    void add(Article article);


    // 分页查询文章列表
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 查询文章详情
    Article detail(Integer id);

    // 更新文章
    void update(Article article);

    // 删除文章
    void delete(Integer id);
}
