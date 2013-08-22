package com.nixsolutions.verstudent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Discipline {
	
	@Id @GeneratedValue
	@Column(name="discipline_id")


	private Long id;
	
	@Column(name="disciplinename")

    s
	private String name;
	
	@Column(name="duration")
	private Integer duration;
	
	
}
