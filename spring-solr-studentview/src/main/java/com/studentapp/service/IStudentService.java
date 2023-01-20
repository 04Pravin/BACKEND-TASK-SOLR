package com.studentapp.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.studentapp.model.Student;

public interface IStudentService {

	List<Student>getAll() throws SolrServerException, IOException;
	
	List<Student> getByField(String field, String value) throws SolrServerException, IOException;
	
	List<Student> sortByField(String field, String order) throws SolrServerException, IOException;
	
	
	
}
