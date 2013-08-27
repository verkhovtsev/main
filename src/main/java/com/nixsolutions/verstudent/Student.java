package com.nixsolutions.verstudent;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="students")
public class Student {
	
	@Id @GeneratedValue
	@Column(name="student_id")

	private Long id;
	
	@Column(name="firstname")
	private String firstName;

	@Column(name="lastname")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateofadmission")
	private Date dateOfAdmission;
	
    @ManyToOne
    @JoinColumn(name="group_id")
	private Group group;
	
	public Student() {}
	
	public Student(String firstName, String lastName, Date dateOfAdmission) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfAdmission = dateOfAdmission;
		
	}
	
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Student(Long id, String firstName, String lastName, Date dateOfAdmission) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfAdmission = dateOfAdmission;
			
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	
	
	
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		if (this == obj) {return true;}
		if (this.getId() == null && ((Student)obj).getId() == null) {return true;}
		
		return this.getId().equals(((Student)obj).getId());
	}

	public int hashCode() {
		if (this.getId() == null) return 0;
		return this.getId().hashCode();
	}
	
	
}
