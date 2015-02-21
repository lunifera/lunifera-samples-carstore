package org.lunifera.samples.carstore.ui.mobile;

import org.lunifera.ecview.servlet.mobile.IMobileUiParticipant;
import org.lunifera.ecview.servlet.mobile.IMobileUiParticipantHandle;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.osgi.service.component.annotations.Component;

import com.vaadin.ui.UI;

public class PaymentTermsViewHandle extends
		AbstractMobileViewHandle<PaymentTermDto> implements
		IMobileUiParticipantHandle {

	private static final String VIEW_ID = "org.lunifera.samples.carstore.ui.mobile.PaymentTermsView";

	protected PaymentTermsViewHandle() {
		super(VIEW_ID, PaymentTermDto.class);
	}

	@Component(property = { "uriFragment=org.lunifera.samples.carstore.ui.mobile.PaymentTermsView" })
	public static class UiProvider implements IMobileUiParticipant {

		@Override
		public IMobileUiParticipantHandle createHandle(UI ui, String fragment) {
			return new PaymentTermsViewHandle();
		}
	}
}
