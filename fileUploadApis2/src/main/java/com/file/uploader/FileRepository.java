package com.file.uploader;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface FileRepository extends CrudRepository<FileDetail, Long> {

	@Query("select f from FileDetail f where f.fileName like %?1% ")
	Iterable<FileDetail> findByFileNameLike(String fileName);
}
