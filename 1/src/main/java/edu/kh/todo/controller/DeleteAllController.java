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

@WebServlet("/deleteAll")
public class DeleteAllController extends HttpServlet {

	TodoService service = new TodoService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			HttpSession session = req.getSession();
			
			Member member = (Member)session.getAttribute("member");
			
			int memberNo = member.getMemberNo();
			
			int result = service.deleteAll(memberNo);

			if(result > 0) {

				session.setAttribute("msg", "모두 삭제되었습니다.");
				
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
