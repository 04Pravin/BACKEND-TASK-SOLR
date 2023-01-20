package com.studentapp.util;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StudentUtil {



	@Value("${solr.url}")
	private String url;
	
	/**
	 * functionality - initialize the connection with the server and 
	 * sends and retrieves data in XML format using XMLResponseParser
	 * 
	 * @author PravinKumarKP
	 */
	@Bean
	public HttpSolrClient getSolrClient() {
		
		HttpSolrClient solr = new HttpSolrClient.Builder(url).build();
		solr.setParser(new XMLResponseParser());
		return solr;
	}
}
