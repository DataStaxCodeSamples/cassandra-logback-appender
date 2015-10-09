package com.datastax.logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

public class LoggerDao {
	
	private Session session;
	private PreparedStatement insert;

	public LoggerDao(String[] contactPoints, String keyspace, String table){
		Cluster cluster = Cluster.builder()
				.withLoadBalancingPolicy(new TokenAwarePolicy(new DCAwareRoundRobinPolicy()))
				.addContactPoints(contactPoints).build();
		
		this.session = cluster.connect();
		
		this.session.execute("create table if not exists " + keyspace + "." + table + " (id uuid PRIMARY KEY, date timestamp, threadId text, level text, name text, msg text);");		
		this.insert = this.session.prepare("insert into " + keyspace + "." + table + "(id, date, threadId,level,name,msg) values (now(),?,?,?,?,?);");
	}
	
//	public void insert (TempLoggerEvent event){
//		
//		BoundStatement bound = new BoundStatement(insert);
//				
//		this.session.executeAsync(bound.bind(event.getDate(), event.getThreadId(), event.getLevel(), event.getName(), event.getMsg()));
//	}
	
}
