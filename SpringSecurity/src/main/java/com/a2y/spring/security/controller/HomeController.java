package com.a2y.spring.security.controller;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.a2y.spring.security.model.Student;

@RestController
public class HomeController {
	private static final List<Student> studentList = Arrays.asList(
			new Student(1, "User Name One"),
			new Student(2, "User Name Two"),
			new Student(3, "User Name Three")
			);
	
	@RequestMapping("/api/v2/student/{studentId}")
	public Student getStudent(@PathVariable Integer studentId) throws Exception {
		return studentList.stream()
				.filter((student) -> studentId == student.getStudentId())
				.findFirst()
				.orElseThrow(() -> new Exception("Student not Found.."));
	}
	@RequestMapping(value = "/management/api/v2/student", method=RequestMethod.GET )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	public List<Student> getALLStudents()  {
		return studentList;
	}
	
	@RequestMapping(value = "/management/api/v2/student/{studentId}", method=RequestMethod.DELETE )
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable(value = "studentId") Integer studentId)  {
		System.out.println(studentId);
	}
	
	@RequestMapping(value = "/management/api/v2/student", method=RequestMethod.POST )
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student)  {
		System.out.println(student.toString());
	}
	
	@RequestMapping(value = "/management/api/v2/student/{studentId}", method=RequestMethod.PUT )
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable(value = "studentId") Integer studentId,@RequestBody Student student)  {
		System.out.println(String.format("%S %S", studentId,student));
	}
	
	
	

}
