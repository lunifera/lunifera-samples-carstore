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
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class RotationViewPart implements MqttCallback {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RotationViewPart.class);

	public static final String BROKER = "tcp://10.0.0.123:1883";
	private static final String TOPIC = "steamengine/rotations";

	@Inject
	private VerticalLayout parentLayout;

	private int currentIndex;

	private MqttClient mqttClient;

	private double rps;

	protected boolean active;

	private DataSeries series;

	public RotationViewPart() {
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
		final Chart chart = new Chart();
		chart.setWidth("700px");

		final Configuration configuration = chart.getConfiguration();
		configuration.getChart().setType(ChartType.SPLINE);
		configuration.getTitle().setText("Rotations per second");

		Axis xAxis = configuration.getxAxis();
		xAxis.setType(AxisType.LINEAR);
		xAxis.setTickPixelInterval(70);

		YAxis yAxis = configuration.getyAxis();
		yAxis.setTitle(new Title("RPS"));
		yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));
		yAxis.setMin(0);

		configuration.getTooltip().setEnabled(false);
		configuration.getLegend().setEnabled(false);

		series = new DataSeries();
		PlotOptionsSpline options = new PlotOptionsSpline();
		options.setPointInterval(120);
		series.setPlotOptions(options);
		series.setName("RPS");
		for (int i = -19; i <= 0; i++) {
			series.add(new DataSeriesItem(i, 0));
		}

		configuration.setSeries(series);
		chart.drawChart(configuration);

		createMqttClient();

		currentIndex = 0;

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
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		synchronized (this) {
			String rpsString = new String(message.getPayload());
			this.rps = Double.valueOf(rpsString);
			parentLayout.getUI().access(new Runnable() {
				@Override
				public void run() {
					series.add(new DataSeriesItem(++currentIndex, rps), true,
							true);
				}
			});
		}
	}

	@Override
	public void connectionLost(Throwable arg0) {

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {

	}

}
