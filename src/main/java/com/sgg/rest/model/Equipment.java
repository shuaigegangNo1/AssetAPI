package com.sgg.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String code;
	private String name;
	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name="location_id")
	@JsonBackReference
	private Location location;
	private String type;
	private String batch_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expired_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buy_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creater_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updater_time;
	private String is_vaild;
	@Column(name ="comments",length=1024)
	private String comments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public Date getCreater_time() {
		return creater_time;
	}
	public void setCreater_time(Date creater_time) {
		this.creater_time = creater_time;
	}
	public Date getUpdater_time() {
		return updater_time;
	}
	public void setUpdater_time(Date updater_time) {
		this.updater_time = updater_time;
	}
	public String getIs_vaild() {
		return is_vaild;
	}
	public void setIs_vaild(String is_vaild) {
		this.is_vaild = is_vaild;
	}
	public Date getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
