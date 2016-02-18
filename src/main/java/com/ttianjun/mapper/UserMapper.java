package com.ttianjun.mapper;

import com.ttianjun.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @user keeley
 * @date 16/2/18
 */
public interface UserMapper extends Mapper<User> {
    public List<User> getAll();
}
