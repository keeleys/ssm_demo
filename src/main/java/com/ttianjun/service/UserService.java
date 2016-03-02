package com.ttianjun.service;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttianjun.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.ttianjun.model.User;
import tk.mybatis.mapper.entity.Example;

/**
 * @description 
 * @author Joseph_Mok
 * @date 2016年1月21日下午6:05:26
 */

@Service
public class UserService {

	@Resource
	private UserMapper userMapper;
	public List <User> getAll() {
		return userMapper.getAll();
	}

	public List<User> findByName(String name){
		Example ex = new Example(User.class);
		ex.createCriteria().andLike("name",name);
		return userMapper.selectByExample(ex);
	}

	/**
	 *
	 * @param pageNum 页码 1开始
	 * @param pageSize 每页数量
     * @return
     */
	public PageInfo<User> findByPage(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<User> users=userMapper.getAll();
		PageInfo<User> pageInfo= new PageInfo(users);
		return pageInfo;
	}
}
