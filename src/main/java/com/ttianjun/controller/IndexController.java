package com.ttianjun.controller;

import com.github.pagehelper.PageInfo;
import com.ttianjun.mapper.UserMapper;
import com.ttianjun.model.User;
import com.ttianjun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ttianjun.base.BaseController;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * @description 
 * @author Joseph_Mok
 * @date 2016年1月20日下午3:01:39
 */

@Controller
public class IndexController extends BaseController {

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserService userService;


	@RequestMapping(value="/index.html")
	public String index(Model model) {
		return "/template/index";
	}
	@RequestMapping(value="/list-{pageNum}.html")
	public String list(@PathVariable("pageNum") Integer pageNum,Model model) {
		final int pageSize = 20;
		PageInfo<User> pageInfo= userService.findByPage(pageNum,pageSize);
		model.addAttribute("page",pageInfo);
		return "/template/list";
	}
	@RequestMapping(value="/userDetail/{userId}.html")
	public String userDetail(@PathVariable("userId") Integer userId,Model model){

		User user = userMapper.selectByPrimaryKey(userId);
		model.addAttribute("user",user);

		return "/template/detail";
	}

	//redis例子
	@Resource
	private ShardedJedisPool shardedJedisPool;

	@RequestMapping(value="/redis.html")
	public String redis(){
		ShardedJedis jedis =  shardedJedisPool.getResource();
		jedis.append("tian","juns");
		System.out.println(jedis.get("tian"));
		return "/template/detail";
	}
}
