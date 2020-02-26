package com.durgasoft.dao;

import com.durgasoft.beans.Student;

public interface StudentDao {
	public String add(Student std);
	public Student search(String sid);
	public String update(Student std);
	public String delete(String sid);
}
