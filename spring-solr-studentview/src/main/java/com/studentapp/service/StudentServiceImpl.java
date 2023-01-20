package com.studentapp.service;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.studentapp.model.Student;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private static final String BASEURL = "http://localhost:8081/student-api/students";

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
	
	@Override
	public List<Student> getAll() throws SolrServerException, IOException {
		logger.info("Showing all the students");
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(BASEURL, List.class);
		List<Student> students = responseEntity.getBody();
		return students;
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
	@Override
	public List<Student> getByField(String field, String value) throws SolrServerException, IOException {
		logger.info("Searching students based on field");
		String url = BASEURL.concat("-search/field/").concat(field).concat("/value/").concat(value);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
		List<Student> students = responseEntity.getBody();
		return students;
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
	@Override
	public List<Student> sortByField(String field, String order) throws SolrServerException, IOException {
		logger.info("Sorting students based on field");
		String url = BASEURL.concat("-sort/field/").concat(field).concat("/order/").concat(order);
		System.out.println(url);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
		List<Student> students = responseEntity.getBody();
		return students;
	}
	
}
