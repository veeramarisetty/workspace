package com.file.uploader;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FILE_DETAILED")
public class FileDetail implements Serializable{

	private @Id @GeneratedValue(strategy= GenerationType.AUTO) 
	@Column(name="FILE_DETAIL_ID")
	Long fileDetailId;
	@Column(name="FILE_NAME")
	private String fileName;
	@Column(name="FILE_TYPE")
	private String fileType;
	@Column(name="FILE_DESC")
	private String fileDescription;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FILE_ID")
	private File file;
	//private List<String> itemTags;
	
	private FileDetail(){
		
	}
	
	public FileDetail(String fileName, String fileType, String fileDescription) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
	}

	public Long getFileDetailId() {
		return fileDetailId;
	}

	public void setFileDetailId(Long fileDetailId) {
		this.fileDetailId = fileDetailId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
