package com.kkb.project.service.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkb.project.dao.UserDao;
import com.kkb.project.po.User;
import com.kkb.project.service.UserService;

@Component
//@Repository
//@Controller
//@Service
public class AnnoUserServiceImpl implements UserService {

	// 自动装配属性，不需要set方法
	@Autowired
	private UserDao dao;

	@Override
	public void insert(User user) {
		// 事务开启
		dao.insert(user);
		// 事务提交
	}

	@Override
	public void delete(int id) {
		// 事务开启
		dao.delete(id);
		// 事务提交
	}

	@Override
	public void update(User user) {
		// 事务开启
		dao.update(user);
		// 事务提交
	}

	@Override
	public User findUserById(int id) {
		return dao.findUserById(id);
	}

}
