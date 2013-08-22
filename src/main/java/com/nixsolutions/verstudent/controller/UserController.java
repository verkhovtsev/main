package com.nixsolutions.verstudent.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.verstudent.Student;
import com.nixsolutions.verstudent.db.UserDao;


@Controller
@RequestMapping("/")
public class UserController {
    
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/studentsList.html")
	public ModelAndView browse() throws Exception {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("studentsList");
	    List<Student> students = new ArrayList<Student>();
	    students.addAll(userDao.findAllStudents());
	    mav.addObject("listOfStudents", students);
	    return mav;
	}
	
	@RequestMapping(value = "/studentAdd.html", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
	    model.addAttribute(new Student());
	    return "studentAdd";
	}

	@RequestMapping(value = "/studentAdd.html", method = RequestMethod.POST)
	public String save(Student student) throws Exception {
		userDao.createStudent(student);
		return "redirect:studentsList.html";
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	   		 new SimpleDateFormat("dd.MM.yyyy"), false));
	}
	
	
/*
	@RequestMapping(value = "/delete.html")
    public String delete(@RequestParam("id") Long id) throws Exception{
		Student user = new Student();
        user.setId(id);
 		userDao.delete(user);
	    return "redirect:browse.html";
    }
	
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
	    model.addAttribute(new User());
	    return "add";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String save(User user) throws Exception {
		userDao.create(user);
		return "redirect:browse.html";
	}

	@RequestMapping(value = "/details.html", method = RequestMethod.GET)
	public String details(@RequestParam("id") Long id, Model model) throws Exception {
	    User user = userDao.find(id);
		model.addAttribute(user);
	    return "details";
	}
		
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public String edit(@RequestParam("uid") Long id, Model model) throws Exception {
		User user = userDao.find(id);
	    model.addAttribute(user);
	    return "add";
	}
	
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public String edit(User user) throws Exception {
		userDao.update(user);
		return "redirect:browse.html";
	}
	
	@RequestMapping(value = "/loginpage.html", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if (error == true) {
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		return "loginpage";
	}
	
	@RequestMapping(value = "/createuser.html", method = RequestMethod.GET)
	public String getCreateUserPage() {
		return "createuser";
	}


	@RequestMapping(value = "/createuser.html", method = RequestMethod.POST)
	public String createUser(@RequestParam(value="firstName", required=false) String firstName,
							 @RequestParam(value="lastName", required=false) String lastName,
							 @RequestParam("nickname") String nickName,
							 @RequestParam("password") String password,
							 @RequestParam("cpassword") String cpassword,
							 @RequestParam(value="admin", required=false) boolean admin,
							 Model model) throws Exception {
		
		Connection connection = myDataSource.getConnection();
		
		String CREATE_USER_1 = "INSERT INTO users VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(CREATE_USER_1);
		//statement.setString(1, firstName);
		//statement.setString(2, lastName);
		statement.setString(1, nickName);
		statement.setString(2, password);
		statement.setString(3, "TRUE");
		statement.executeUpdate();
		
		String CREATE_USER_2 = "INSERT INTO authorities VALUES (?, ?)";
		statement = connection.prepareStatement(CREATE_USER_2);
		statement.setString(1, nickName);
		if(!admin) statement.setString(2, "ROLE_USER");
		else statement.setString(2, "ROLE_ADMIN");
			
		statement.executeUpdate();
	
		statement.close();
		connection.close();
		
		return "redirect:browse.html";
	}
	
	/*
	@RequestMapping(value = "/j_spring_security_logout")
	public String logout() throws Exception {
		return "redirect:redirect.html";
	}
	
*/

	
}

