package com.file.uploader;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

@Entity
@Table(name="FILE")
public class File implements Serializable{
	private @Id @GeneratedValue(strategy= GenerationType.AUTO) 
	@Column(name="FILE_ID")
	Long fileId;
	@Column(name="FILE")
	private Blob file;
	
	@Column(name="FILE_LENGTH")
	private int fileLength;
	
	@OneToOne(mappedBy="file")
	private FileDetail fileDetail;
	
	private File(){
		
	}
	
	public File(Blob file, int fileLength) throws SerialException, SQLException {
		super();
		Blob fileBlob = new SerialBlob(file);
		this.file = fileBlob;
		this.fileLength = fileLength;
	}

	public Blob getFile() {
		return file;
	}
	
	public void setFile(Blob file) {
		this.file = file;
	}
	
	public int getFileLength() {
		return fileLength;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public FileDetail getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(FileDetail fileDetail) {
		this.fileDetail = fileDetail;
	}
}
