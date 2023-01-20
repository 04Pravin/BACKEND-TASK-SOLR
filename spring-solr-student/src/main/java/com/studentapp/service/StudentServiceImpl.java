package com.studentapp.service;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentapp.exception.StudentNotFoundException;
import com.studentapp.model.Student;
import com.studentapp.repository.IStudentRepository;
import com.studentapp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {
	
	private Logger logger = LoggerFactory.getLogger(StudentRepository.class);
	
	@Autowired
	private IStudentRepository studentRepo;

	/**
	 * @param student
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - addition of student in the solr core
	 *
	 * @author PravinKumarKP
	 */
	@Override
	public void addStudent(Student student) throws SolrServerException, IOException {
		logger.info("In service - adding student");
		studentRepo.addStudent(student);
	}

	/**
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException 
	 * Functionality - delete the document using its id or based on field in the solr core
	 *
	 * @author PravinKumarKP
	 */
	@Override
	public void deleteByField(String field, String value) throws SolrServerException, IOException {
		logger.info("In service - deleting student");
		studentRepo.deleteByField(field, value);
	}

	/**
	 * @param student
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - updates the document in the solr core
	 * 
	 * @author PravinKumarKP
	 */
	@Override
	public void updateStudent(Student student) throws SolrServerException, IOException {
		logger.info("In service - updating student");
		studentRepo.updateStudent(student);
	}
	
	/**
	 * @return returns list of students
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - getting all the students from the solr core
	 * 
	 * @author PravinKumarKP
	 */
	@Override
	public List<Student> getAll() throws SolrServerException, IOException {
		logger.info("In service - showing all student");
		return studentRepo.getAll();
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
		logger.info("In service - searching a student based on field");
		if(studentRepo.getByField(field, value).isEmpty()){
			throw new StudentNotFoundException();
		}
		return studentRepo.getByField(field, value);
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
		logger.info("In service - sorting list of student based on field");
		return studentRepo.sortByField(field, order);
	}


}
