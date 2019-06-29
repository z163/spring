package com.kkb.project.dao.anno;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kkb.project.dao.UserDao;
import com.kkb.project.po.User;

//@Repository
@Component
public class AnnoUserDaoImpl implements UserDao {

	// 自动装配
	// 方式1：
	// @Qualifier("")
//	 @Autowired
	// 方式2：
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate template;

	@Override
	public void insert(User user) {
		// 业务SQL
		String sql = "INSERT INTO user (username,address) VALUES(?,?)";
		// 参数数组
		Object[] args = { user.getUsername(), user.getAddress() };
		// template类中没有insert方法，直接调用update
		template.update(sql, args);
	}

	@Override
	public void delete(int id) {
		// 业务SQL
		String sql = "DELETE FROM user WHERE id=?";
		// 参数数组
		Object[] args = { id };
		// template类中没有insert方法，直接调用update
		template.update(sql, args);
	}

	@Override
	public void update(User user) {
		// 业务SQL
		String sql = "UPDATE user SET address = ? WHERE id=?";
		// 参数数组
		Object[] args = { user.getAddress(), user.getId() };
		// template类中没有insert方法，直接调用update
		template.update(sql, args);
	}

	@Override
	public User findUserById(int id) {
		// 业务SQL
		String sql = "SELECT * FROM user WHERE id = ?";
		// 参数数组
		Object[] args = { id };
		// template类中没有insert方法，直接调用update
		return template.queryForObject(sql, args, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setAddress(rs.getString("address"));
				return user;
			}
		});
	}

}
