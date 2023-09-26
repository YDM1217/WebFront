package edu.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.member.model.service.MemberService;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	Member member = new Member(); 
	MemberService service = new MemberService();
	TodoService todoService = new TodoService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setCharacterEncoding("UTF-8");

			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");

			Member member =  service.login(inputId, inputPw);
	
			HttpSession session = req.getSession();

			if(member.getMemberNo() != 0) {
				
				List<Todo> list = new ArrayList<Todo>();
				
				list = todoService.select(member.getMemberNo());

				session.setAttribute("list", list);
				
				session.setAttribute("member", member);
				session.setMaxInactiveInterval(60*60);

				
				resp.sendRedirect("/"); 

			}else{

				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");

				String referer = req.getHeader("referer");

				resp.sendRedirect(referer);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}




	}
}
