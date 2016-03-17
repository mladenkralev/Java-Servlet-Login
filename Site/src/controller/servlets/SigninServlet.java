package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.Validaiton;
import model.Student;
import model.msqlDaoFactory.MysqlConnection;

@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet implements Validaiton {
	private static final long serialVersionUID = 1L;
	private Controller control;

	public SigninServlet() {
		super();
		MysqlConnection.connect();
		control = new Controller();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordconfirm = request.getParameter("passwordconfirm");

		if (validate(username) && validate(password) && validate(passwordconfirm)) {
			if (comparePasswords(password, passwordconfirm)) {
				Student student = new Student(username, password);
				if (control.doesExist(student)) {
					response.sendRedirect("http://localhost:8080/Site/Login.html");
				} else {
					if (control.loginPerformed(student) > 0) {
						writer.println("Student added " + student);
					}
				}
			}
		}

	}

	@Override
	public boolean validate(String data) {
		if ((data == null) || (data == "")) {
			return false;
		}
		return true;
	}

	public boolean comparePasswords(String password, String passwordconfirm) {
		if (password.equals(passwordconfirm)) {
			return true;
		} else {
			return false;
		}
	}

}
