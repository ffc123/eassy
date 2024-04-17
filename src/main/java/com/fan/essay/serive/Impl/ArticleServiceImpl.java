package com.fan.essay.serive.Impl;


import com.fan.essay.pojo.PageBean;
import com.fan.essay.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fan.essay.mapper.ArticleMapper;
import com.fan.essay.pojo.Article;
import com.fan.essay.serive.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // 补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);

        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建分页对象
        PageBean<Article> pageBean = new PageBean<>();

        // 2.开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Article> ac = articleMapper.list(id,categoryId, state);
        // Page中提供了方法，可以获取PageHelper分页查询的结果,得到总记录数和当前页数据
        Page<Article> p = (Page<Article>) ac;

        // 把查询结果封装到pageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());

        return pageBean;
    }

    @Override
    public Article detail(Integer id) {
       return articleMapper.selectById(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
