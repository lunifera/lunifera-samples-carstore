/**
 * Copyright (c) 2011 - 2014, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * 		Florian Pirchner - Initial implementation
 */
package org.lunifera.samples.carstore.extension.iot.views;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.lunifera.vaaclipse.addons.common.api.di.Callback;
import org.lunifera.vaaclipse.addons.common.api.di.Load;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.CrosshairStyle;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class TemperatureViewPart implements MqttCallback {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TemperatureViewPart.class);

	public static final String BROKER = "tcp://10.0.0.123:1883";
	private static final String TOPIC = "steamengine/temperature";

	@Inject
	private VerticalLayout parentLayout;

	private MqttClient mqttClient;

	private double temperature;

	protected boolean active;

	private ListSeries ls;

	public TemperatureViewPart() {
	}

	@PostConstruct
	public void setup() {
		active = true;

		VerticalLayout layout = new VerticalLayout();
		parentLayout.addComponent(layout);
		layout.setSizeFull();

		layout.addComponent(getChart());

	}

	protected Component getChart() {
		Chart chart = new Chart();

		Configuration config = chart.getConfiguration();
		config.setTitle("Temperature");
		
		config.getTooltip().setCrosshairs(
				new CrosshairStyle(10, SolidColor.BLACK, DashStyle.SOLID, 0),
				new CrosshairStyle(5, "#880000", DashStyle.DOT, 1));
		YAxis yAxis = config.getyAxis();
		yAxis.setMin(75);
		
		ls = new ListSeries();
		ls.setName("Celsius");
		config.setSeries(ls);

		chart.drawChart(config);

		createMqttClient();

		// updater = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// while (active) {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// }
		//
		// if (active && parentLayout.getUI() != null) {
		// parentLayout.getUI().access(new Runnable() {
		// @Override
		// public void run() {
		// ls.addData(temperature, true, false);
		// }
		// });
		// }
		// }
		// }
		// });
		// updater.start();

		return chart;
	}

	protected void createMqttClient() {
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			mqttClient = new MqttClient(BROKER, UUID.randomUUID().toString()
					.substring(0, 23), persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			mqttClient.connect(connOpts);
			mqttClient.setCallback(this);
			mqttClient.subscribe(TOPIC);
		} catch (MqttException me) {
			me.printStackTrace();
		}
	}

	@Load
	public void reload() {
	}

	/**
	 * If a command was executed, the original actionId from the ECView view
	 * (YView) will be passed here.
	 * 
	 * @param actionId
	 */
	@Callback
	public void commandExecuted() {
	}

	@PreDestroy
	public void dispose() {
		try {
			active = false;
			mqttClient.disconnect();
			mqttClient.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable arg0) {

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		synchronized (this) {
			String temperatureString = new String(message.getPayload());
			this.temperature = Double.valueOf(temperatureString);
			parentLayout.getUI().access(new Runnable() {
				@Override
				public void run() {
					ls.addData(temperature, true, false);
				}
			});
		}
	}

}
