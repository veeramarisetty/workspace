package com.file.uploader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Autowired
	FileService fileService;
	
	@RequestMapping(path="/file", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile( @RequestParam String fileName,@RequestParam String fileType,
			@RequestParam String fileDescription, @RequestParam MultipartFile file){
		
		FileDetail fileD = new FileDetail(fileName, fileType, fileDescription);
		File fileO;
		try {
			Blob blobValue = new SerialBlob(file.getBytes());
			fileO = new File(blobValue, file.getBytes().length);
			fileD.setFile(fileO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseEntity<String> respEntity = new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			return respEntity;
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseEntity<String> respEntity = new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			return respEntity;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseEntity<String> respEntity = new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			return respEntity;
		}

		String fileId = fileService.createFile(fileD);
		ResponseEntity<String> respEntity = new ResponseEntity<>(fileId, HttpStatus.OK);
		return respEntity;
	};
	
	@RequestMapping(path="/file/metadata", method = RequestMethod.GET)
	public ResponseEntity<List<FileDTO>> FindAllFileMetadata(){
		List<FileDTO> fileList = fileService.getAllFilesDetails();
		ResponseEntity<List<FileDTO>> respEntity = new ResponseEntity<List<FileDTO>>(fileList, HttpStatus.OK);
		return respEntity;
	};
	
	@RequestMapping(path="/file/search/{filename}", method = RequestMethod.GET)
	public ResponseEntity<List<FileDTO>> searchFilesByName(@PathVariable String fileName){
		
		return null;
	};
	
	@RequestMapping(path="/file/{fileId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getFile(@PathVariable String fileId){
		FileDetail fileD = fileService.extractFile(fileId);
		Blob fileBytes = fileD.getFile().getFile();
		byte[] outputFile = null;
		try {
			outputFile = fileBytes.getBytes(1, fileD.getFile().getFileLength()) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity
	                .unprocessableEntity()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileD.getFileName() + "\"")
	                .body(null);
		}
		return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileD.getFileName() + "\"")
                .body(outputFile);
	};
}
