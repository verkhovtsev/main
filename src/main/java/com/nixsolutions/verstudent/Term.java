package com.nixsolutions.verstudent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Term {
	
	@Id @GeneratedValue
	@Column(name="student_id")
	private Long id;
	
	@Column(name="termname")
	private String name;
	
	@Column(name="duration")
	private Integer duration; 
	
	
	
}
