package com.fan.essay.mapper;


import com.fan.essay.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    // 添加文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime}) " )
    void add(Article article);

    // 分页查询文章列表
    List<Article> list(Integer id, Integer categoryId, String state);

    // 查询文章详情
    @Select("select * from article where id = #{id}")
    Article selectById(Integer id);

    // 更新文章
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg}," +
            "state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    // 删除文章
    @Delete("delete from article where id = #{id}")
    void delete(Integer id);
}
