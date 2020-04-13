package basedao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateSessionFactory {

	//用于保存某个线程共享变量(session约束)
	private static final ThreadLocal<Session> THREAD_LOCAL=new ThreadLocal<Session>();
	
	//SessionFactory对象
	private static SessionFactory sessionFactory=null;
	
	//创建会话工厂
	static {
		try {
			Configuration cfg=new Configuration().configure();//加载Hibernate配置文件
			sessionFactory=cfg.buildSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("创建会话工厂失败");
			e.printStackTrace();
		}
	};
	
	//获取Session
	public static Session getSession(){
		Session session=(Session)THREAD_LOCAL.get();//获取当前线程的变量
		if(session==null||!session.isOpen()){//session为空或被占用
			if(sessionFactory==null){
				rebuildSessionFactory();
			}
			session=sessionFactory.openSession();//重新开启一个线程创建Session，需要手动关闭Session
			THREAD_LOCAL.set(session);//为session添加线程约束
		}
		return session;
	}
	/**
	 * 重建会话工厂
	 */
	public static void rebuildSessionFactory(){
		try {
			Configuration cfg=new Configuration().configure();//加载Hibernate配置文件
			sessionFactory=cfg.buildSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("创建会话工厂失败");
			e.printStackTrace();
		}
	}
	
	//获取SessionFactory对象
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//关闭Session
	public static void closeSession(){
		Session session=(Session)THREAD_LOCAL.get();
		THREAD_LOCAL.set(null);
		if(session!=null){
			session.close();//关闭session;
		}
	}
	
}
