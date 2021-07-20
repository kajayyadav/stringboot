package com.a2y.spring.security.model;

public class Student {
private Integer studentId;
private String StudentName;


public Student() {
	
}
public Student(Integer studentId, String studentName) {
	this.studentId = studentId;
	StudentName = studentName;
}
public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}
public String getStudentName() {
	return StudentName;
}
public void setStudentName(String studentName) {
	StudentName = studentName;
}
@Override
public String toString() {
	return "Student [studentId=" + studentId + ", StudentName=" + StudentName + "]";
}



}
