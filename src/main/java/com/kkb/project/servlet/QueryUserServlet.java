package com.kkb.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kkb.project.po.User;
import com.kkb.project.service.UserService;
import com.kkb.project.utils.JsonUtils;

//Servlet由谁初始化？由Servlet容器进行初始化
//Servlet什么时候初始化？
public class QueryUserServlet extends HttpServlet {

	private static final long serialVersionUID = 116714888769576151L;

	// Servlet不知道有这个属性，无法依赖注入
	// private UserService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置请求体的字符编码
		request.setCharacterEncoding("UTF-8");

		try {
			// 接收参数
			String id = request.getParameter("id");

			// 获取ApplicationContext
			// ApplicationContext context = new
			// ClassPathXmlApplicationContext("classpath:project/spring-xml.xml");
			// ApplicationContext context = new
			// ClassPathXmlApplicationContext("classpath:project/spring-anno-xml.xml");
			// ApplicationContext context = new
			// ClassPathXmlApplicationContext("classpath:project/spring-xml-import.xml");
			// ApplicationContext context = new
			// ClassPathXmlApplicationContext("classpath:project/spring-xml-dao.xml",
			// "classpath:project/spring-xml-service.xml");

			// 获取Web应用上下文（由ContextLoaderListener创建spring容器，并将它放入ServletContext域对象中）
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			
			UserService service = context.getBean(UserService.class);

			// 第二步：调用业务方法
			User user = service.findUserById(Integer.parseInt(id));

			// 响应
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JsonUtils.object2Json(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
