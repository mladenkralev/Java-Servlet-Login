package controller;

import java.sql.SQLException;
import java.util.List;

import model.Student;
import model.msqlDaoFactory.MySQLDAOStudent;

public class Controller {
	private MySQLDAOStudent factoryStudents;

	public Controller() {
		factoryStudents = new MySQLDAOStudent();
	}

	public int loginPerformed(Student student) {
		int result = -1;
		try {
			result = factoryStudents.addInDB(student);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean doesExist(Student student) {
		try {
			List<Student> list = factoryStudents.getStudents();
			for (Student s : list) {
				if (s.equals(student)) {
					System.out.println(s + " " + student + " " + s.equals(student));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}