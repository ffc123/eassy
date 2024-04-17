package com.fan.essay.controller;


import com.fan.essay.pojo.PageBean;
import com.fan.essay.pojo.Result;
import com.fan.essay.serive.ArticleService;
import com.fan.essay.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){

        articleService.add(article);

        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pageBean =  articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id){
        return Result.success(articleService.detail(id));
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Article.update.class) Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success();
    }



}
