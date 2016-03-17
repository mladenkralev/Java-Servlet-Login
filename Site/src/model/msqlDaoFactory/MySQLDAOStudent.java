package model.msqlDaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import java.sql.Connection;

import model.Student;
import model.StudentDAO;

/*
 * 	DAOFactory -> MySQLDAOFactory -> MysqlDAOStudent 
 */

public class MySQLDAOStudent implements StudentDAO {
	
	@Override
	public int addInDB(Student student) throws SQLException {
		Connection connection = MysqlConnection.getConnection();
	
		PreparedStatement statement = (PreparedStatement) connection
				.prepareStatement("insert into "
						+ "student(username , password) values (?,?)");

		statement.setString(1, student.getUsername());
		statement.setString(2, student.getPassword());

		int upadeted = statement.executeUpdate();

		statement.close();

		return upadeted;
	}

	@Override
	public List<Student> getStudents() throws SQLException {
		
		List<Student> students = new ArrayList<Student>();
		Connection connection = MysqlConnection.getConnection();
		
		Statement statement = (Statement) connection
				.createStatement();
		ResultSet resultSet = statement.executeQuery("select username, "
				+ "password from "
				+ "student");
		
		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");

			Student student = new Student(username, password);
			students.add(student);
		}
		statement.close();
		return students;
	}

}	
