package com.file.uploader;

import org.springframework.data.repository.CrudRepository;

interface FileRepository extends CrudRepository<FileDetail, Long> {

}
