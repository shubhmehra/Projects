package org.autodoc.dao;

import org.hibernate.Session;

public interface IdDao {
	public void closeSession(Session session, String functionName) throws Exception;
}