package org.autodoc.daoimpl;

import org.apache.log4j.Logger;
import org.autodoc.dao.IdDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class IdDaoImpl implements IdDao {
	Logger logg = Logger.getLogger(this.getClass());
	
	public void closeSession(Session session, String functionName) throws Exception {
		try {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		} catch(Exception e) {
			logg.error(functionName + " while closing.", e);
		}
	}
}