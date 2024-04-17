package com.fan.essay.serive.Impl;

import com.fan.essay.serive.CategoryService;
import com.fan.essay.mapper.CategoryMapper;
import com.fan.essay.pojo.Category;
import com.fan.essay.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        // 获得当前用户的id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        // 获取当前用户的id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        return categoryMapper.list(id);
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public void update(Category category) {
        // 设置更新时间
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
