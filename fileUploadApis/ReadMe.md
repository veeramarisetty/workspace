**********************************************************************
Spring boot application for uploading and extracting the files from DB.
**********************************************************************

This contain pretty simple APIs that can load a text or pdf file and some meta data into
the inmemory DB(H2). This can be easily extended and enhanced to work with any files.

There are 3 rest api end points that were exposed right now.

1) POST request --  http://localhost:8080/file
set the form data entries while posting this information by POSTMAN or REST client.
	fileName - testDocument.pdf
	fileType - application/pdf
	fileDescription - test document.
	file(Please use small text files or pdf files)

2) GET request --  http://localhost:8080/file/metadata
extract the meta data alone of the files that are inserted.

3) GET request --  http://localhost:8080/file/{1}
extract the text file that was stored. The path variable 1 at the end is the fileId returned.

Please note that this just covers the basic happy path testing.

Running instructions ::
	-Import project into STS
	-mvn clean
	-mvn package
	-Run As - Java application or mvn Spring-boot:run.
