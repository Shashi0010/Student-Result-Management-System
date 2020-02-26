package com.durgasoft.service;

import com.durgasoft.beans.Student;
import com.durgasoft.dao.StudentDao;
import com.durgasoft.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public String addStudent(Student std) {
		StudentDao stdDao = StudentDaoFactory.getStudentDao();
		String status = stdDao.add(std);
		return status;
	}

	@Override
	public Student searchStudent(String sid) { 
		StudentDao stdDao = StudentDaoFactory.getStudentDao();
		Student std = stdDao.search(sid);
		return std;
	}

	@Override
	public String updateStudent(Student std) {
		StudentDao stdDao = StudentDaoFactory.getStudentDao();
		String status = stdDao.update(std);
		return status;
	}

	@Override
	public String deleteStudent(String sid) {
		StudentDao stdDao = StudentDaoFactory.getStudentDao();
		String status = stdDao.delete(sid);
		return status;
	}

}
