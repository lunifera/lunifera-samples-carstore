package org.lunifera.samples.carstore.tests.dtos;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.impl.DtoServiceAccess;
import org.lunifera.dsl.dto.lib.services.IDTOService;
import org.lunifera.samples.carstore.tests.AbstractTestClass;

@SuppressWarnings("restriction")
public abstract class AbstractDtoTest extends AbstractTestClass {

	/**
	 * Returns the {@link IMapperAccess} which offers access to "dto to entity"
	 * and "entity to dto" mappers.
	 * 
	 * @return
	 */
	protected IMapperAccess getMapperAccess() {
		return getService(IMapperAccess.class);
	}

	/**
	 * Returns the {@link IDTOService} for the given dtoClass.
	 * 
	 * @param dtoClass
	 * @return
	 */
	protected <D> IDTOService<D> getDtoService(Class<D> dtoClass) {
		return DtoServiceAccess.getService(dtoClass);
	}

	/**
	 * Returns a proper mapper that can map between the dtoClass and the
	 * entityClass.
	 * 
	 * @param dtoClass
	 * @param entityClass
	 * @return
	 */
	protected <D, E> IMapper<D, E> getDtoMapper(Class<D> dtoClass,
			Class<E> entityClass) {
		return getMapperAccess().getMapper(dtoClass, entityClass);
	}

}
