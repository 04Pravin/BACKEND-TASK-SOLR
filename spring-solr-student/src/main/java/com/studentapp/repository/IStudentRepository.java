package com.studentapp.repository;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;

import com.studentapp.model.Student;

public interface IStudentRepository {

	public void addStudent(Student student) throws SolrServerException, IOException;

	public void updateStudent(Student student) throws SolrServerException, IOException;
	
	public void deleteByField(String field, String value) throws SolrServerException, IOException;

	List<Student> getAll() throws SolrServerException, IOException;

	List<Student> getByField(String field, String value) throws SolrServerException, IOException;

	List<Student> sortByField(String field, String order) throws SolrServerException, IOException;

	
	
	
	
}
