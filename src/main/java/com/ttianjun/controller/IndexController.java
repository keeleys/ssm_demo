package com.ttianjun.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.remoting.p2p.Group;
import com.github.pagehelper.PageInfo;
import com.ttianjun.bean.Order;
import com.ttianjun.mapper.UserMapper;
import com.ttianjun.model.User;
import com.ttianjun.service.OrderService;
import com.ttianjun.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ttianjun.base.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
		return renderTemp("list");
	}
	@RequestMapping(value="/detail/{userId}.html")
	public String userDetail(@PathVariable("userId") Integer userId,Model model){

		User user = userMapper.selectByPrimaryKey(userId);
		model.addAttribute("user",user);

		return renderTemp("detail");
	}
	@RequestMapping(value="/login.html")
	public String login(){
		return renderTemp("login");
	}
	@RequestMapping(value="/accErr.html")
	public String accErr(){
		return renderTemp("acc_err");
	}


	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/index.html";
	}

	/**
	redis例子
	@Resource
	private ShardedJedisPool shardedJedisPool;

	@RequestMapping(value="/redis")
	@ResponseBody
	public User redis(){
		ShardedJedis jedis =  shardedJedisPool.getResource();
		User user = new User();
		user.setId(1);
		user.setName(jedis.get("tian"));
		return user;
	}
	*/

	/**
	 * dubbo 例子

	@Reference(group = "sz")
	private OrderService orderService;

	@RequestMapping(value="/dubbo")
	@ResponseBody
	public List<Order> dubbo(){
		return orderService.getAll();
	}

	 */
}
