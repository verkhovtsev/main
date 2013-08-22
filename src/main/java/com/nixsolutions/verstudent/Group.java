package com.nixsolutions.verstudent;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="groups")
public class Group {
	
	@Id @GeneratedValue
	@Column(name="group_id")
	private Long id;
	
	@Column(name="groupname")
	private String name;
	
	@Column(name="numberOfStudents")
	private Integer numberOfStudents;
	
	@OneToMany(mappedBy="group", cascade = CascadeType.ALL)
    private List<Student> students;	
	
	
	
	public Group() {}
	
	public Group(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
}
