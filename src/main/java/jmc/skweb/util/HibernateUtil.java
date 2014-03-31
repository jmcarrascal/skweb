package jmc.skweb.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.SettingsFactory;

public class HibernateUtil {
	private static SessionFactory sessionFactoryDesa;
	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			
			sessionFactoryDesa = new AnnotationConfiguration().configure("/hibernate.cfg.xml").buildSessionFactory();
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactoryDesa;
	}
}