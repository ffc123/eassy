package com.fan.essay.controller;


import com.fan.essay.pojo.Result;
import com.fan.essay.serive.CategoryService;
import com.fan.essay.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 添加分类
    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    // 查询分类
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    // 查询分类详情
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        return Result.success(categoryService.selectById(id));
    }

    // 更新分类
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    // 删除分类
    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}