package org.lunifera.samples.carstore.ui.mobile;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.lunifera.dsl.dto.lib.impl.DtoServiceAccess;
import org.lunifera.dsl.dto.lib.services.IDTOService;
import org.lunifera.ecview.core.common.context.ContextException;
import org.lunifera.ecview.core.common.context.IViewContext;
import org.lunifera.ecview.core.common.model.core.YView;
import org.lunifera.ecview.core.extension.model.extension.YTable;
import org.lunifera.ecview.servlet.mobile.IMobileUiParticipant;
import org.lunifera.ecview.servlet.mobile.IMobileUiParticipantHandle;
import org.lunifera.ecview.xtext.builder.participant.IECViewAddonsMetadataService;
import org.lunifera.mobile.vaadin.ecview.model.VMNavigationBarButton;
import org.lunifera.mobile.vaadin.ecview.model.VaadinMobilePackage;
import org.lunifera.runtime.web.ecview.presentation.vaadin.VaadinRenderer;
import org.lunifera.samples.carstore.ui.mobile.impl.Activator;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractMobileViewHandle<D> implements
		IMobileUiParticipantHandle {

	private static final String ACTION_SAVE = "org.lunifera.ui.save";
	private static final String MASTERDETAIL__MASTER_TABLE = "org.lunifera.ui.masterdetail.mastertable";

	private final String viewId;
	private final Class<D> dtoClass;

	private IViewContext viewContext;
	private IDTOService<D> dtoService;

	private SaveActionObserver observer;

	private VerticalLayout content;

	protected AbstractMobileViewHandle(String viewId, Class<D> dtoClass) {
		this.viewId = viewId;
		this.dtoClass = dtoClass;
	}

	@Override
	public void dispose() {
		unregisterSaveHandler();

		try {
			viewContext.dispose();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String getTheme() {
		return "mobiletheme";
	}

	@SuppressWarnings("restriction")
	@Override
	public void handle(ComponentContainer mainLayout, String fragment) {

		content = new VerticalLayout();

		mainLayout.addComponent(content);
		content.setSizeFull();

		dtoService = DtoServiceAccess.getService(dtoClass);
		YView yView = findViewModel(viewId);
		if (yView == null) {
			Notification.show(viewId + " could not be found!",
					Type.ERROR_MESSAGE);
			return;
		}

		// render the Vaadin UI
		VaadinRenderer renderer = new VaadinRenderer();
		try {
			viewContext = renderer.render(content, yView, null);

			// access the navigation bar action and register a listener at it
			registerSaveHandler();
		} catch (ContextException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Register an observer at the EObject for the save-navigationBarAction
	 */
	private void registerSaveHandler() {
		VMNavigationBarButton vmButton = findAction(ACTION_SAVE);
		observer = new SaveActionObserver();
		vmButton.eAdapters().add(observer);
	}

	/**
	 * Register an observer at the EObject for the save-navigationBarAction
	 */
	private void unregisterSaveHandler() {
		// TODO Pirchner wrong ID!
		VMNavigationBarButton vmButton = findAction(ACTION_SAVE);
		vmButton.eAdapters().remove(observer);
	}

	private VMNavigationBarButton findAction(String id) {
		YView yView = (YView) viewContext.getViewEditpart().getModel();
		TreeIterator<EObject> contents = yView.eAllContents();
		while (contents.hasNext()) {
			EObject expected = contents.next();
			if (expected instanceof VMNavigationBarButton) {
				VMNavigationBarButton yButton = (VMNavigationBarButton) expected;
				if (id.equals(yButton.getId())) {
					return yButton;
				}
			}
		}
		return null;
	}

	private YTable findTable(String id) {
		YView yView = (YView) viewContext.getViewEditpart().getModel();
		TreeIterator<EObject> contents = yView.eAllContents();
		while (contents.hasNext()) {
			EObject expected = contents.next();
			if (expected instanceof VMNavigationBarButton) {
				YTable yTable = (YTable) expected;
				if (id.equals(yTable.getId())) {
					return yTable;
				}
			}
		}
		return null;
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
					.waitForService(500);
			return uiService.getViewMetadata(uiName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			tracker.close();
		}
		return null;
	}

	private class SaveActionObserver extends AdapterImpl {

		@Override
		public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {

			// if the navigation bar button was clicked
			if (msg.getFeature() == VaadinMobilePackage.Literals.VM_NAVIGATION_BAR_BUTTON__LAST_CLICK_TIME) {
				YTable yTable = findTable(MASTERDETAIL__MASTER_TABLE);
				@SuppressWarnings("unchecked")
				D dto = (D) yTable.getSelection();
				dtoService.update(dto);
			}
		}
	}
}
