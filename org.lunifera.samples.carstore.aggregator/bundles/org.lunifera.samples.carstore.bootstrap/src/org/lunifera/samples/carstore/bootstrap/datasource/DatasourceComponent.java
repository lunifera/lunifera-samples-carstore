package org.lunifera.samples.carstore.bootstrap.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.jdbc.DataSourceFactory;

@Component(immediate = true)
public class DatasourceComponent {

	private DataSource ds;
	private DataSourceFactory dsf;
	private ServiceRegistration<DataSource> registry;

	@Activate
	protected void activate(ComponentContext context) {
		try {
			ds = dsf.createDataSource(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		registry = context.getBundleContext().registerService(DataSource.class,
				ds, null);
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	protected void setDatasourceFactory(DataSourceFactory dsf) {
		this.dsf = dsf;
	}

	@Deactivate
	protected void deactivate() {
		if (registry != null) {
			registry.unregister();
			registry = null;
		}
	}

}
