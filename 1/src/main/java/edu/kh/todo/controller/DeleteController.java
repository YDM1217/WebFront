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

@WebServlet("/delete")
public class DeleteController extends HttpServlet{

	TodoService service = new TodoService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			HttpSession session = req.getSession();
			
			List<Todo> list = (ArrayList<Todo>)session.getAttribute("list");
			
			int index = Integer.parseInt(req.getParameter("index"));
			
			Todo todo = list.get(index);
			int todoNo = todo.getTodoNo();
			
			int result = service.delete(todoNo);

			if(result > 0) {

				session.setAttribute("msg", "삭제되었습니다.");
				
				Member member = (Member) session.getAttribute("member");
				List<Todo> todolist = new ArrayList<Todo>();
				
				todolist = service.select(member.getMemberNo());
				
				session.setAttribute("list", todolist);
				
				resp.sendRedirect("/");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}

