package com.file.uploader;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface FileService {

	List<FileDTO> getAllFilesDetails();
	String createFile(FileDetail fileDetail);
	FileDetail extractFile(String fileId);
}
