package basedao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Hibernatedao implements iHibernatedao {

	@Override
	public Object save(Object object) {
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			Serializable count = session.save(object);
			session.flush();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			HibernateSessionFactory.closeSession();
		}
		return 0;
	}

	@Override
	public int update(Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(object);
			session.flush();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			HibernateSessionFactory.closeSession();
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getObj(Class cla, Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Object obj = session.get(cla, (Serializable) object);
		return obj;
	}

	@Override
	public int delete(Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.delete(object);
			session.flush();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			HibernateSessionFactory.closeSession();
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int delete(Class cla, Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Object obj = session.get(cla, (Serializable) object);
		Transaction ts = session.beginTransaction();
		try {
			session.delete(obj);
			session.flush();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			HibernateSessionFactory.closeSession();
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List select(String HQL) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List select(String HQL, Object[] parmas) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		for (int i = 0; i < parmas.length; i++) {
			query.setParameter(i, parmas[i]);
		}
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public Object seleceValue(String HQL) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		Object object = query.uniqueResult();
		return object;
	}

	@Override
	public Object seleceValue(String HQL, Object[] parmas) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		for (int i = 0; i < parmas.length; i++) {
			query.setParameter(i, parmas[i]);
		}
		Object object = query.uniqueResult();
		return object;
	}

	@Override
	public Long getPageCount(String HQL, int perCount) {
		// TODO Auto-generated method stub
		String SHQL = "select count(*) " + HQL;
		Object oCount = seleceValue(SHQL);
		long lCount = (Long) oCount;
		long pageCount = 0;
		if (lCount % perCount == 0) {
			pageCount = lCount / perCount;
		} else {
			pageCount = lCount / perCount + 1;
		}
		return pageCount;
	}

	@Override
	public Long getPageCount(String HQL, Object[] parmas, int perCount) {
		// TODO Auto-generated method stub
		String SHQL = "select count(*) " + HQL;
		Object oCount = seleceValue(SHQL, parmas);
		long lCount = (Long) oCount;
		long pageCount = 0;
		if (lCount % perCount == 0) {
			pageCount = lCount / perCount;
		} else {
			pageCount = lCount / perCount + 1;
		}
		return pageCount;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List selectPage(String HQL, int perCount, int index) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		query.setFirstResult((index - 1) * perCount);
		query.setMaxResults(perCount);
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List selectPage(String HQL, Object[] parmas, int perCount, int index) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(HQL);
		query.setFirstResult((index - 1) * perCount);
		query.setMaxResults(perCount);
		for (int i = 0; i < parmas.length; i++) {
			query.setParameter(i, parmas[i]);
		}
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

}
