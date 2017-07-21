package com.thirtySix.model.id.generator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * Custom uuid generator.
 * 
 * @author ShaoYang.Lin
 */
public class UuidGenerator extends UUIDGenerator {

	private String entityName;

	@Override
	public void configure(final Type type, final Properties params,
			final ServiceRegistry serviceRegistry) throws MappingException {
		this.entityName = params.getProperty(ENTITY_NAME);
		super.configure(type, params, serviceRegistry);
	}

	@Override
	public Serializable generate(final SessionImplementor session,
			final Object object) throws HibernateException {

		final Serializable id = session
				.getEntityPersister(this.entityName, object)
				.getIdentifier(object, session);

		return id == null ? super.generate(session, object) : id;
	}

}
