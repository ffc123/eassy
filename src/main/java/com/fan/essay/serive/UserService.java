package com.fan.essay.serive;

import com.fan.essay.pojo.User;



public interface UserService /*extends BaseMapper<User>*/ {
    // 根据用户名查询用户
    User selectByName(String username);

    // 注册
    void register(String username, String password);

    // 更新
    void update(User user);

    // 更新头像
    void updateAvatar(String avatarUrl);

    // 更新密码
    void updatePwd(String newPwd);
}
