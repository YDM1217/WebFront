package edu.kh.todo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/insert")
public class InsertController extends HttpServlet{

	TodoService service = new TodoService();
	Todo todo = new Todo();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");

			HttpSession session = req.getSession();
			
			Member member = (Member)session.getAttribute("member");
			
			String todoTitle = req.getParameter("todoTitle");
			String todoMemo = req.getParameter("todoMemo");
			
			int memberNo = member.getMemberNo();
			
			int result = service.insert(todoTitle, todoMemo, memberNo);
			
			if(result>0) {
				
				session.setAttribute("msg", "등록되었습니다.");

				List<Todo> list = new ArrayList<Todo>();
				
				list = service.select(member.getMemberNo());

				session.setAttribute("list", list);
				
				session.setMaxInactiveInterval(60*60);
				
				resp.sendRedirect("/");

			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
