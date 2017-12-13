package com.sat.jersey.app.config.db;

import org.springframework.data.mongodb.core.MongoTemplate;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.sat.jersey.db.pri.User;

@ChangeLog(order = "001")
public class Dbinit {
	@ChangeSet(order = "01", author = "initiator", id = "01-addUsers")
	public void addUsers(MongoTemplate mongoTemplate) {
		User systemUser = new User();
		systemUser.setId("user-0");
		systemUser.setUsername("tools");
		systemUser.setPassword("sat@123");
		mongoTemplate.save(systemUser);
	}
}
