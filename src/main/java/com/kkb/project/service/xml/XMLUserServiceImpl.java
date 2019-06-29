package com.kkb.project.service.xml;

import com.kkb.project.dao.UserDao;
import com.kkb.project.po.User;
import com.kkb.project.service.UserService;

public class XMLUserServiceImpl implements UserService {

	// 使用XML中的property标签完成注入,必须有对应的set方法
	private UserDao dao;

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public void insert(User user) {
		dao.insert(user);

	}

	@Override
	public void delete(int id) {
		if (id == 0) {
			return;
		}
		dao.delete(id);
	}

	@Override
	public void update(User user) {
		if (user == null || user.getId() == 0) {
			return;
		}
		dao.update(user);
	}

	@Override
	public User findUserById(int id) {
		if (id == 0) {
			return null;
		}
		return dao.findUserById(id);
	}

}
