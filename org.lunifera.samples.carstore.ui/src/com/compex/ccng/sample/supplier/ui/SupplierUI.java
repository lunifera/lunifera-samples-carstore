/**
 * Copyright 2013 Lunifera GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.compex.ccng.sample.supplier.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.lunifera.dsl.dto.lib.impl.DtoServiceAccess;
import org.lunifera.dsl.dto.lib.services.IDTOService;
import org.lunifera.ecview.core.common.context.ContextException;
import org.lunifera.ecview.core.common.context.II18nService;
import org.lunifera.ecview.core.common.context.IViewContext;
import org.lunifera.ecview.core.common.model.core.YExposedAction;
import org.lunifera.ecview.core.common.model.core.YView;
import org.lunifera.ecview.xtext.builder.participant.IECViewAddonsMetadataService;
import org.lunifera.runtime.web.ecview.presentation.vaadin.VaadinRenderer;
import org.osgi.util.tracker.ServiceTracker;

import com.compex.ccng.sample.supplier.model.dtos.SupplierDto;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@Theme(Reindeer.THEME_NAME)
@Push
public class SupplierUI extends UI implements PropertyChangeListener {

	private static final String SUPPLIER_VIEW = "com.compex.ccng.sample.supplier.model.views.SupplierView";
	private static final String MAIN_BEANSLOT = "ds";
	private IViewContext viewContext;
	private IDTOService<SupplierDto> dtoService;
	private Button reloadAction;
	private Button saveAction;
	private Button deleteAction;
	private Button searchAction;

	@SuppressWarnings("restriction")
	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout spanningLayout = new VerticalLayout();
		spanningLayout.setSizeFull();
		setContent(spanningLayout);

		dtoService = DtoServiceAccess.getService(SupplierDto.class);
		VerticalLayout layout = new VerticalLayout();
		spanningLayout.addComponent(layout);
		layout.setSizeUndefined();
		layout.setWidth("100%");

		HorizontalLayout buttonBar = new HorizontalLayout();
		layout.addComponent(buttonBar);
		buttonBar.setMargin(true);
		buttonBar.setSpacing(true);
		NativeButton setupDB = new NativeButton("Setup DB");
		setupDB.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Activator.INSTANCE.setupDB();
			}
		});
		buttonBar.addComponent(setupDB);

		YView yView = findViewModel(SUPPLIER_VIEW);
		if (yView == null) {
			Notification.show("PersonView could not be found!",
					Type.ERROR_MESSAGE);
			return;
		}

		// render the Vaadin UI
		VaadinRenderer renderer = new VaadinRenderer();
		try {
			viewContext = renderer.render(layout, yView, null);
		} catch (ContextException e) {
			e.printStackTrace();
		}

		II18nService service = viewContext.getService(II18nService.ID);
		for (YExposedAction action : yView.getExposedActions()) {
			Button temp = new Button();
			buttonBar.addComponent(temp);
			temp.setCaption(service.getValue(action.getLabelI18nKey(),
					getLocale()));
			temp.setIcon(new ThemeResource(action.getIcon()));
			if (action.getId().equals("org.lunifera.actions.load")) {
				reloadAction = temp;
				reloadAction.setEnabled(false);
				temp.addClickListener(new LoadHandler());
			} else if (action.getId().equals("org.lunifera.actions.save")) {
				saveAction = temp;
				saveAction.setEnabled(false);
				temp.addClickListener(new SaveHandler());
			} else if (action.getId().equals("org.lunifera.actions.delete")) {
				deleteAction = temp;
				deleteAction.setEnabled(false);
				temp.addClickListener(new DeleteHandler());
			} else if (action.getId().equals("org.lunifera.actions.find")) {
				searchAction = temp;
				temp.addClickListener(new SearchHandler());
			}
		}
	}

	public void setBean(SupplierDto bean) {
		viewContext.setBean(MAIN_BEANSLOT, bean);

		reloadAction.setEnabled(bean != null);
		saveAction.setEnabled(bean != null);
		deleteAction.setEnabled(bean != null);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	/**
	 * Tries to find the view model using the ecview addons service.
	 * 
	 * @param uiName
	 * @return
	 */
	protected YView findViewModel(String uiName) {
		ServiceTracker<IECViewAddonsMetadataService, IECViewAddonsMetadataService> tracker = new ServiceTracker<IECViewAddonsMetadataService, IECViewAddonsMetadataService>(
				Activator.getContext(), IECViewAddonsMetadataService.class,
				null);
		tracker.open();
		try {
			IECViewAddonsMetadataService uiService = tracker
					.waitForService(5000);
			return uiService.getViewMetadata(uiName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			tracker.close();
		}
		return null;
	}

	private class LoadHandler implements Button.ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			SupplierDto dto = (SupplierDto) viewContext.getBean(MAIN_BEANSLOT);
			SupplierDto newLoaded = dtoService.get(dto.getId());
			setBean(newLoaded);
		}
	}

	private class SaveHandler implements Button.ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			SupplierDto dto = (SupplierDto) viewContext.getBean(MAIN_BEANSLOT);
			dtoService.update(dto);
			SupplierDto newInstance = dtoService.get(dto.getId());
			setBean(newInstance);
		}
	}

	private class DeleteHandler implements Button.ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			SupplierDto dto = (SupplierDto) viewContext.getBean(MAIN_BEANSLOT);
			dtoService.delete(dto);
			setBean(null);
		}
	}

	private class SearchHandler implements Button.ClickListener {
		private static final String SUPPLIER_SEARCHVIEW = "com.compex.ccng.sample.supplier.model.views.SupplierSearchView";
		private IViewContext searchViewContext;

		@Override
		public void buttonClick(ClickEvent event) {
			final Window dialog = new Window();
			dialog.setModal(true);
			dialog.setWidth("640px");
			II18nService service = viewContext.getService(II18nService.ID);
			dialog.setCaption(service
					.getValue(
							SUPPLIER_SEARCHVIEW,
							getLocale()));
			dialog.addCloseListener(new Window.CloseListener() {
				@Override
				public void windowClose(CloseEvent e) {
					searchViewContext.dispose();
					searchViewContext = null;
				}
			});

			VerticalLayout vl = new VerticalLayout();
			vl.setMargin(true);
			dialog.setContent(vl);

			YView searchView = findViewModel(SUPPLIER_SEARCHVIEW);
			if (searchView == null) {
				Notification.show("SupplierView could not be found!",
						Type.ERROR_MESSAGE);
				return;
			}

			// render the Vaadin UI
			VaadinRenderer renderer = new VaadinRenderer();
			try {
				searchViewContext = renderer.render(vl, searchView, null);
			} catch (ContextException e) {
				e.printStackTrace();
			}

			Button closeButton = new Button(
					service.getValue(
							"com.compex.ccng.sample.supplier.model.views.headersearch.select",
							getLocale()));
			closeButton.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					setBean((SupplierDto) searchViewContext
							.getBean("selection"));
					dialog.close();
				}
			});
			vl.addComponent(closeButton);
			getUI().addWindow(dialog);

		}
	}
}
