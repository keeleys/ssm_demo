package com.ttianjun.model;


import javax.persistence.Id;

/**
 * @description
 * @author Joseph_Mok
 * @date 2016年1月21日下午4:11:44
 */
public class User  {

	private static final long serialVersionUID = 3696936428072745753L;
	@Id
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
