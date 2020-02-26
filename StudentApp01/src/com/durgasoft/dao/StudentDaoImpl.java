package com.durgasoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.durgasoft.beans.Student;
import com.durgasoft.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {
	String status = "";
	Student student;
	Connection con;
	@Override
	public String add(Student std) {
		
		student = search(std.getSid());
		if(student != null) {
			status = "existed";
		}
		else {
			try {
				
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "shashi", "chintu");
				PreparedStatement pst = con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, std.getSid());
				pst.setString(2, std.getName());
				pst.setString(3, std.getCls());
				pst.setInt(4, Integer.parseInt(std.getS1()));
				pst.setInt(5, Integer.parseInt(std.getS2()));
				pst.setInt(6, Integer.parseInt(std.getS3()));
				pst.setInt(7, Integer.parseInt(std.getS4()));
				pst.setInt(8, Integer.parseInt(std.getS5()));
				pst.setInt(9, Integer.parseInt(std.getS6()));
				pst.setInt(10, Integer.parseInt(std.getTotal()));
				int rC = pst.executeUpdate();
				if(rC == 1) {
					status = "success";
				}else {
					status = "failure";
				}				
			} catch (Exception e) {
				status = "failure";
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student std = null;
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst  = con.prepareStatement("select * from student where sid = ?");
			pst.setString(1, sid);
			ResultSet rs = pst.executeQuery();
			boolean b = rs.next();
			if(b == true) {
				std = new Student();
				std.setSid(rs.getString("SID"));
				std.setName(rs.getString("SNAME"));
				std.setCls(rs.getString("CLASS"));
				std.setS1(rs.getString("S1"));
				std.setS2(rs.getString("S2"));
				std.setS3(rs.getString("S3"));
				std.setS4(rs.getString("S4"));
				std.setS5(rs.getString("S5"));
				std.setS6(rs.getString("S6"));
				std.setTotal(rs.getString("TOTAL"));
				
			}
		} catch (Exception e) {
			std = null;
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String update(Student std) {
		String status = "";
		if(std == null) {
			status = "notexisted";
		}
		else {	/*		
				System.out.println(std.getSid());
				System.out.println(std.getName());
				System.out.println(std.getS1());
				System.out.println(std.getS2());
				System.out.println(std.getS3());
				System.out.println(std.getS4());
				System.out.println(std.getS5());
				System.out.println(std.getS6());
				System.out.println(std.getTotal());
				*/
			
			try {
				Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement("update student set SNAME=?, CLASS=?, S1 =?, S2 =?, S3 =?, S4 =?, S5 =?, S6 =?, TOTAL =? where SID=?");
				pst.setString(1, std.getName());
				pst.setString(2, std.getCls());
				pst.setInt(3, Integer.parseInt(std.getS1()));
				pst.setInt(4, Integer.parseInt(std.getS2()));
				pst.setInt(5, Integer.parseInt(std.getS3()));
				pst.setInt(6, Integer.parseInt(std.getS4()));
				pst.setInt(7, Integer.parseInt(std.getS5()));
				pst.setInt(8, Integer.parseInt(std.getS6()));
				pst.setInt(9, Integer.parseInt(std.getTotal()));
				pst.setString(10, std.getSid());
				
				
				int rC = pst.executeUpdate();
				if(rC == 1)
				{
					status = "success";
				}
				else {
					status = "failure";
				}
				
			} catch (Exception e) {
				status = "failure";
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String delete(String sid) {
		String status = "";
		Student std = search(sid);
		if(std == null) {
			status = "notexisted";
		}
		else {
			try {
				
				Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement("delete from student where sid=?");
				pst.setString(1, sid);
				int rC = pst.executeUpdate();
				if(rC == 1) {
					status = "success";
				}else {
					status = "failure";
				}
				
			} catch (Exception e) {
				status = "failure";
				e.printStackTrace();
			}
		}
		return status;
	}

}































