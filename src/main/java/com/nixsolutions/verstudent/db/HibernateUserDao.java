package com.nixsolutions.verstudent.db;

import java.util.Collection;
import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Fetch;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.verstudent.Group;
import com.nixsolutions.verstudent.Student;

public class HibernateUserDao implements UserDao {
	
	private SessionFactory sessionFactory;
	    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}

	@Transactional 
	public Student createStudent(Student student) throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		session.save(student);		
		return student;
	}

	public void updateStudent(Student student) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void deleteStrudent(Student student) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public Student findStudent(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Collection findAllStudents() throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		List students = session.createCriteria(Student.class).list();
		return students;
	}

	@Transactional 
	public Group createGroup(Group group) throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		session.save(group);		
		return group;
	}

	public void updateGroup(Group group) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional 
	public void deleteGroup(Group group) throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(group);
	}
	
	@Transactional 
	public Group findGroup(Long id) throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		Group group = (Group) session.get(Group.class, id);
		return group;
	}

	@Transactional 
	public Collection findAllGroups() throws DatabaseException {
		Session session = getSessionFactory().getCurrentSession();
		List groups = session.createCriteria(Group.class).list();
		return groups;
	}



}
