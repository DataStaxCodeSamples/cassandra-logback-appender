package com.datastax.logger;

import java.io.IOException;
import java.io.OutputStream;

public class CassandraOutputStream extends OutputStream{

	private StringBuffer buffer = new StringBuffer();
	private String table;
	private String contactPoints;
	private String keyspace;
	private LoggerDao loggerDao;
	
	public CassandraOutputStream(String contactPoints, String keyspace, String table) {
		this.contactPoints = contactPoints;
		this.keyspace = keyspace;
		this.table = table;
		
		this.loggerDao = new LoggerDao(contactPoints.split(","), keyspace, table);
	}

	@Override
	public synchronized void write(int b) throws IOException {
		buffer.append(Character.toChars(b));
	}

	@Override
	public synchronized void flush(){
		System.out.println(buffer);
		buffer = new StringBuffer();
	}
}
