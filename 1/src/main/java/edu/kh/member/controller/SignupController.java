package edu.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.member.model.service.MemberService;


@WebServlet("/signup")
public class SignupController extends HttpServlet{

	MemberService service  = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			String inputNickname = req.getParameter("inputNickname");
			
			// 조회 후 동일 아이디 존재하면 실패
			Member checkMember =  new Member();
			checkMember = service.select(inputId);

			if(checkMember.getMemberId() == null) {

				// 가입 실행
				int result = service.signup(inputId, inputPw, inputNickname);

				if(result > 0) {

					Member member = service.login(inputId, inputPw);

					HttpSession session = req.getSession();

					session.setAttribute("member", member);
					session.setMaxInactiveInterval(60*60);

					req.getRequestDispatcher("/index.jsp").forward(req, resp);
				} 

			} else {

				HttpSession session = req.getSession();
				
				session.setAttribute("msg", "이미 존재하는 계정입니다.");

				String referer = req.getHeader("referer");

				resp.sendRedirect(referer);

			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
