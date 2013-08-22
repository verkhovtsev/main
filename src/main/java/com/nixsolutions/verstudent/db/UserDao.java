package com.nixsolutions.verstudent.db;

import java.util.Collection;

import com.nixsolutions.verstudent.Group;
import com.nixsolutions.verstudent.Student;


public interface UserDao {

	//FOR STUDENTS
	Student createStudent(Student student) throws DatabaseException;
	void updateStudent(Student student) throws DatabaseException;
	void deleteStrudent(Student student) throws DatabaseException;
	Student findStudent(Long id) throws DatabaseException;
	Collection findAllStudents() throws DatabaseException;
	
	//FOR GROUPS
	Group createGroup(Group group) throws DatabaseException;
	void updateGroup(Group group) throws DatabaseException;
	void deleteGroup(Group group) throws DatabaseException;
	Group findGroup(Long id) throws DatabaseException;
	Collection findAllGroups() throws DatabaseException;

}
