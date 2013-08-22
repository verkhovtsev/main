package com.nixsolutions.verstudent.db;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nixsolutions.verstudent.Group;
import com.nixsolutions.verstudent.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:contextHibernate.xml")
public class HibernateUserDaoTest  {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BasicDataSource dataSource;

    @Before
    public void setUp() throws Exception {
    	IDatabaseConnection connection = getConnection();
    	DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
    	connection.close();
     }
    
    @After
    public void tearDown() throws Exception {
    	IDatabaseConnection connection = getConnection();
     	DatabaseOperation.DELETE_ALL.execute(connection, getDataSet());
    	connection.close();
    }
    
   protected IDataSet getDataSet() throws Exception {
       	return new XmlDataSet(getClass().getClassLoader().getResourceAsStream("testDataSet.xml"));
    }
   
   	protected IDatabaseConnection getConnection() throws Exception {
   		return new DatabaseConnection(dataSource.getConnection());
   	}
   	
    @Test
    public void CreateStudent() {
		try {
			Student student = new Student();
			student.setFirstName("John");
			student.setLastName("Doe");
			Group group = new Group("2143");
			group.setId(003L);
			student.setGroup(group);
			Date date = new Date();
			student.setDateOfAdmission(date);
			
			assertNull(student.getId());
			student = userDao.createStudent(student);
			assertNotNull(student);
			assertNotNull(student.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
        
    @Test
    public void FindAllStudents() {
		try {
			Collection collection = userDao.findAllStudents();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 5, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
    
    @Test
    public void CreateGroup() {
    	try {
			Group group = new Group();
			group.setName("SYA-10-1");
			assertNull(group.getId());
			userDao.createGroup(group);
			assertNotNull(group);
			assertNotNull(group.getId());
   		
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
    }
    
    @Test
    public void FindAllGroups() {
		try {
			Collection collection = userDao.findAllGroups();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	} 
    
    @Test
	public void FindGroup() {
		try {
			Group foundGroup = userDao.findGroup(002L);
			assertNotNull(foundGroup);
			assertEquals(foundGroup.getName(), "Bill");
			
			List<Student> students = foundGroup.getStudents();
			assertNotNull(students);
			assertEquals(3,students.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
  
    @Test
    public void DeleteGroup() {
		try {
			Group foundGroup = userDao.findGroup(002L);
			assertNotNull(foundGroup);
			
			userDao.deleteGroup(foundGroup);
			Group group = userDao.findGroup(002L);
			assertNull(group);
			
			Collection collection = userDao.findAllStudents();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
			
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	} 
    
  
    /*  

    @Test
	@SuppressWarnings("deprecation")
	public void testFind() {
		try {
			User foundUser = userDao.find(1002L);
			assertNotNull(foundUser);
			assertEquals(foundUser.getFullName(), "Verkhovtsev, Andrey");
			assertEquals(foundUser.getDateOfBirthd().getYear(), 1989-1900);
			assertEquals(foundUser.getDateOfBirthd().getMonth(), 4);
			assertEquals(foundUser.getDateOfBirthd().getDate(), 24);
			assertEquals(foundUser.getPhoneNumber().getPhoneNumber(), "122334235");
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
    @Test
	public void testDelete() {
		try {
			User user = new User();
			user.setFirstName("Alexey");
			user.setLastName("Grachov");
			user.setId(1003L);
			Calendar calendar = Calendar.getInstance();
			calendar.set(1988, Calendar.NOVEMBER, 8);
			Date date = calendar.getTime();
			user.setDateOfBirthd(date);
			user.getPhoneNumber().setPhoneNumber("56982");
		
			assertNotNull(userDao.find(user.getId()));
			userDao.delete(user);
			assertNull(userDao.find(user.getId()));
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
    @Test
	public void testUpdate() {
		try {
			/*User user = new User();
			user.setFirstName("Ivan");
			user.setLastName("Petrov");
			user.setId(1003L);
			Calendar calendar = Calendar.getInstance();
			calendar.set(1985, Calendar.JANUARY, 28);
			Date date = calendar.getTime();
			user.setDateOfBirthd(date);
			user.getPhoneNumber().setPhoneNumber("56982");
			
			User user =  userDao.find(1003L);
			user.setFirstName("Ivan");
			user.setLastName("Petrov");
			//user.setId(1003L);
			Calendar calendar = Calendar.getInstance();
			calendar.set(1985, Calendar.JANUARY, 28);
			Date date = calendar.getTime();
			user.getPhoneNumber().setPhoneNumber("56982");;
			
			userDao.update(user);
			
			User foundUser = userDao.find(user.getId());
			assertNotNull(foundUser);
			assertEquals(foundUser.getFullName(), user.getFullName());
			assertEquals(foundUser.getDateOfBirthd().getYear(), user.getDateOfBirthd().getYear());
			assertEquals(foundUser.getDateOfBirthd().getMonth(), user.getDateOfBirthd().getMonth());
			assertEquals(foundUser.getDateOfBirthd().getDate(), user.getDateOfBirthd().getDate());
			assertEquals(foundUser.getPhoneNumber().getPhoneNumber(), user.getPhoneNumber().getPhoneNumber());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

    @Test
	public void testFindName() {
		try {
			String firstName = "Alexey";
			String lastName = "Grachov";
			
			Collection collection = userDao.find(firstName, lastName);
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
    
*/


}

