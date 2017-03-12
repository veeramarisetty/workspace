package com.file.uploader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepo;
	
	@Override
	public List<FileDTO> getAllFilesDetails() {

		 Iterable<FileDetail> fileList = fileRepo.findAll();
		 
		 List<FileDTO> fileDList = new ArrayList<FileDTO>();
		 for(FileDetail fileDetail : fileList){
			 FileDTO fileD = new FileDTO(fileDetail.getFileDetailId(),fileDetail.getFileName(),fileDetail.getFileType(),fileDetail.getFileDescription());
			 fileDList.add(fileD);
		 }
		 
		 return fileDList;
	}

	@Override
	public String createFile(FileDetail fileDetail) {
		FileDetail fileD =  fileRepo.save(fileDetail);		
		return fileD.getFileDetailId().toString();
	}
	
	@Override
	public List<FileDTO> searchForFile(String fileName) {
		 Iterable<FileDetail> fileList = fileRepo.findByFileNameLike(fileName);
		 
		 List<FileDTO> fileDList = new ArrayList<FileDTO>();
		 for(FileDetail fileDetail : fileList){
			 FileDTO fileD = new FileDTO(fileDetail.getFileDetailId(),fileDetail.getFileName(),fileDetail.getFileType(),fileDetail.getFileDescription());
			 fileDList.add(fileD);
		 }
		 
		 return fileDList;
	}

	@Override
	public FileDetail extractFile(String fileId) {

		Long fileDetailId = Long.parseLong(fileId);
		FileDetail fileD =  fileRepo.findOne(fileDetailId);
		return fileD;
	}

}
