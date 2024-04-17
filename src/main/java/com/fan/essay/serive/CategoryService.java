package com.fan.essay.serive;

import com.fan.essay.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 添加分类
    void add(Category category);

    // 查询分类
    List<Category> list();

    // 查询分类详情
    Category selectById(Integer id);

    // 更新分类
    void update(Category category);

    // 删除分类
    void delete(Integer id);
}
