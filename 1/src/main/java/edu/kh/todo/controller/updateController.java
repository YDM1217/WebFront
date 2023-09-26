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

@WebServlet("/update")
public class updateController extends HttpServlet {

	TodoService service = new TodoService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		List<Todo> list = (ArrayList<Todo>) session.getAttribute("list");

		int index = Integer.parseInt(req.getParameter("index"));
		int todoNo = Integer.parseInt(req.getParameter("todoNo"));
		
		Todo todo = list.get(index);
	
		session.setAttribute("todoTitle", todo.getTodoTitle());
		session.setAttribute("todoMemo", todo.getTodoMemo());
		session.setAttribute("todoNo", todoNo);
		
		req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
	
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
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			
			int result = service.update(todoTitle, todoMemo, memberNo, todoNo);
			
			if(result>0) {
				
				session.setAttribute("msg", "수정이 완료되었습니다.");

				List<Todo> list = new ArrayList<Todo>();
				
				list = service.select(member.getMemberNo());

				session.setAttribute("list", list);
				
				resp.sendRedirect("/");

			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
