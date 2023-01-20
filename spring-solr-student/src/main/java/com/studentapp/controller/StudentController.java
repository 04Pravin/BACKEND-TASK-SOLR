package com.studentapp.controller;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentapp.model.Student;
import com.studentapp.service.IStudentService;
@RestController
@RequestMapping("student-api")
public class StudentController {

	private Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	private IStudentService studentService;

	@Autowired
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	
	/**
	 * @param student
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - addition of student in the solr core
	 *
	 * @author PravinKumarKP
	 */
	@PostMapping("/students")
	ResponseEntity<Void> addStudent(@RequestBody Student student) throws SolrServerException, IOException {
		logger.info("Adding student");
		studentService.addStudent(student);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		
	}
	
	/**
	 * @param student
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - updates the document in the solr core
	 * 
	 * @author PravinKumarKP
	 */
	@PutMapping("/students")
	ResponseEntity<Void> updateStudent(@RequestBody Student student) throws SolrServerException, IOException {
		logger.info("Updating student");
		studentService.updateStudent(student);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	/**
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException 
	 * Functionality - delete the document using its id or based on field in the solr core
	 *
	 * @author PravinKumarKP
	 */
	@DeleteMapping("/students/field/{field}/value/{value}")
	ResponseEntity<Void> deleteByField(@PathVariable("field") String field, @PathVariable("value") String value) throws SolrServerException, IOException {
		logger.info("Deleting student");
		studentService.deleteByField(field, value);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	/**
	 * @param page
	 * @param rows
	 * @return response while getting all the students
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - getting all the students
	 * 
	 * @author PravinKumarKP
	 */
	@GetMapping("/students")
	ResponseEntity<List<Student>> getAll() throws SolrServerException, IOException {
		logger.info("showing all the students");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Returns a list students");
		header.add("info", "API - Get all students");
		ResponseEntity<List<Student>> response = new ResponseEntity<>(studentService.getAll(), header, HttpStatus.OK) ;
		return response;
	}
	
	/**
	 * @param field
	 * @param element
	 * @return response while searching a student based on field
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - searching student based on the field
	 * 
	 * @author PravinKumarKP
	 */
	@GetMapping("/students-search/field/{field}/value/{value}")
	ResponseEntity<List<Student>> getByField(@PathVariable("field")String field, @PathVariable("value")String value) throws SolrServerException, IOException {
		logger.info("get students where "+field+" = "+value);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Returns a student/list students by "+field);
		header.add("info", "API - Get by student "+field);
		ResponseEntity<List<Student>> response = new ResponseEntity<>(studentService.getByField(field, value),header, HttpStatus.FOUND);
		return response;
	}
	
	/**
	 * @param field
	 * @param value
	 * @return response while sorting
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - sorting students based on the field in ascending or descending order
	 * 
	 * @author PravinKumarKP
	 */
	@GetMapping("/students-sort/field/{field}/order/{order}")
	ResponseEntity<List<Student>> sortByfield(@PathVariable("field")String field, @PathVariable("order") String order) throws SolrServerException, IOException {
		String value;
		if(order.equalsIgnoreCase("desc")) {
			value = "descending order";
		}else {
			value = "ascending order";
		}
		logger.info("sorting list of students based on "+field+" in "+value);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Returns a sorted list based on students "+field);
		header.add("info", "API - Sort by student "+field);
		ResponseEntity<List<Student>> response = new ResponseEntity<>(studentService.sortByField(field, order), header, HttpStatus.OK);
		return response;
	}

}
