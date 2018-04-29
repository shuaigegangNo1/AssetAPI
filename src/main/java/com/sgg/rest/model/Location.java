package com.sgg.rest.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String code;
	private String name;
	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name="parent_id")
	@JsonBackReference
	private Location location;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creater_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updater_time;
	private String is_vaild = "y";
    @OneToMany(mappedBy = "location",fetch=FetchType.EAGER)
    private Set<Equipment> equipmentSet=new HashSet<Equipment>(); // 集合
    @OneToMany(mappedBy = "location",fetch=FetchType.EAGER)
    private Set<Location> locationSet=new HashSet<Location>();
	public Set<Equipment> getEquipmentSet() {
		return equipmentSet;
	}
	public void setEquipmentSet(Set<Equipment> equipmentSet) {
		this.equipmentSet = equipmentSet;
	}
	public Set<Location> getLocationSet() {
		return locationSet;
	}
	public void setLocationSet(Set<Location> locationSet) {
		this.locationSet = locationSet;
	}
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
}
