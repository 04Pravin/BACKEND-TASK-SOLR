package com.studentapp.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentapp.model.Student;
import com.studentapp.util.StudentUtil;

@Repository
public class StudentRepository implements IStudentRepository {

	private Logger logger = LoggerFactory.getLogger(StudentRepository.class);
	
	@Autowired
	StudentUtil studentUtil;
	
	@Autowired
	HttpSolrClient solr;

//	private static final HttpSolrClient solr = StudentUtil.getSolrClient();

	DocumentObjectBinder binder = new DocumentObjectBinder();

	/**
	 * @param student
	 * @throws SolrServerException
	 * @throws IOException
	 * Functionality - addition of student in the solr core
	 *
	 * @author PravinKumarKP
	 */
	@Override
	@ResponseBody
	public void addStudent(Student student) throws SolrServerException, IOException {
		logger.info("In repository - adding student");
		SolrInputDocument doc = new SolrInputDocument(); // document creation
		doc.addField("id", student.getId());
		doc.addField("name", student.getName());
		doc.addField("department", student.getDepartment());
		doc.addField("batch", student.getBatch());
		doc.addField("email", student.getEmail());
		doc.addField("mobileNum", student.getMobileNum());
		doc.addField("bloodGroup", student.getBloodGroup());
		solr.add(doc);
		solr.commit();

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
	@ResponseBody
	public void updateStudent(Student student) throws SolrServerException, IOException {
		logger.info("In repository - updating student");
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.setAction(UpdateRequest.ACTION.COMMIT, false, false);

		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("id", student.getId());
		document.addField("name", student.getName());
		document.addField("department", student.getDepartment());
		document.addField("batch", student.getBatch());
		document.addField("email", student.getEmail());
		document.addField("mobileNum", student.getMobileNum());
		document.addField("bloodGroup", student.getBloodGroup());

		updateRequest.add(document);
		UpdateResponse response = updateRequest.process(solr);
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
	@ResponseBody
	public void deleteByField(String field, String value) throws SolrServerException, IOException {
		logger.info("In repository - deleting student");
		solr.deleteByQuery(field + ":" + value);
		solr.commit();
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
	@ResponseBody
	public List<Student> getAll() throws SolrServerException, IOException {
		logger.info("In repository - showing all student");
		SolrQuery query = new SolrQuery("*:*");
		query.addField("*");
		QueryResponse queryResponse = solr.query(query);
		SolrDocumentList doc = queryResponse.getResults();
		solr.commit();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Student>students = binder.getBeans(Student.class, doc);
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
	@ResponseBody
	public List<Student> getByField(String field, String value) throws SolrServerException, IOException {
		logger.info("In repository - searching a student based on field "+field);
		List<Student> students = new ArrayList<>();
		SolrQuery query = new SolrQuery();
		query.setQuery(field+ ":" + (value));
		QueryResponse queryResponse = solr.query(query);
		SolrDocumentList doc = queryResponse.getResults();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		if(doc.isEmpty()) {
			query.setQuery(field+ ":" + value+"*");
			queryResponse = solr.query(query);
			doc = queryResponse.getResults();
			students = binder.getBeans(Student.class, doc);
		}if(doc.isEmpty()) {
			query.setQuery(field+ ":"+"*"+ value);
			queryResponse = solr.query(query);
			doc = queryResponse.getResults();
			students = binder.getBeans(Student.class, doc);
		}if(doc.isEmpty()) {
			query.setQuery(field+ ":"+ "*" + value+"*");
			queryResponse = solr.query(query);
			doc = queryResponse.getResults();
			students = binder.getBeans(Student.class, doc);
		}else {
			students = binder.getBeans(Student.class, doc);
		}
		
		System.out.println(students);
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
	@ResponseBody
	public List<Student> sortByField(String field, String order) throws SolrServerException, IOException {
		logger.info("In repository - sorting list of student based on field "+field);
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.addField("*");
		if (order.equalsIgnoreCase("desc")) {
			query.setSort(field, ORDER.desc);
		} else {
			query.setSort(field, ORDER.asc);
		}
		QueryResponse response = solr.query(query);
		List<Student> students = response.getBeans(Student.class);
		solr.commit();

		return students;

	}
}
