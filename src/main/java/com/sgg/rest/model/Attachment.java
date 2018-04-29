package com.sgg.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String fileName;
	private String filePath;
	private String fileFormat;
	@ManyToOne(targetEntity = Equipment.class)
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creater_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updater_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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
}
