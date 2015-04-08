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

import java.text.DecimalFormat;
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
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class PressureViewPart implements MqttCallback {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PressureViewPart.class);

	public static final String BROKER = "tcp://10.0.0.123:1883";
	private static final String TOPIC = "steamengine/pressure";

	@Inject
	private VerticalLayout parentLayout;

	private MqttClient mqttClient;

	private double pressure;

	private ListSeries series;

	public PressureViewPart() {
	}

	@PostConstruct
	public void setup() {

		VerticalLayout layout = new VerticalLayout();
		parentLayout.addComponent(layout);
		layout.setSizeFull();

		layout.addComponent(getChart());

	}

	protected Component getChart() {
		final Chart chart = new Chart();
		chart.setWidth("500px");

		final Configuration configuration = chart.getConfiguration();
		configuration.getChart().setType(ChartType.GAUGE);
		configuration.getChart().setPlotBackgroundColor(null);
		configuration.getChart().setPlotBackgroundImage(null);
		configuration.getChart().setPlotBorderWidth(0);
		configuration.getChart().setPlotShadow(false);
		configuration.getChart().setAnimation(false);
		configuration.setTitle("Boiler Pressure");

		GradientColor gradient1 = GradientColor.createLinear(0, 0, 0, 1);
		gradient1.addColorStop(0, new SolidColor("#FFF"));
		gradient1.addColorStop(1, new SolidColor("#FFF"));

		GradientColor gradient2 = GradientColor.createLinear(0, 0, 0, 1);
		gradient2.addColorStop(0, new SolidColor("#FFF"));
		gradient2.addColorStop(1, new SolidColor("#FFF"));

		Background[] background = new Background[3];
		background[0] = new Background();
		background[0].setBackgroundColor(gradient1);
		background[0].setBorderWidth(0);
		background[0].setOuterRadius("109%");

		background[1] = new Background();
		background[1].setBackgroundColor(gradient2);
		background[1].setBorderWidth(1);
		background[1].setOuterRadius("107%");

		background[2] = new Background();
		background[2].setBackgroundColor(new SolidColor("#DDD"));
		background[2].setBorderWidth(0);
		background[2].setInnerRadius("103%");
		background[2].setOuterRadius("105%");

		configuration.getPane().setStartAngle(-150);
		configuration.getPane().setEndAngle(150);
		configuration.getPane().setBackground(background);

		YAxis yAxis = configuration.getyAxis();
		yAxis.setTitle(new Title("bar"));
		yAxis.setMin(0);
		yAxis.setMax(4.0);
		yAxis.setMinorTickInterval("auto");
		yAxis.setMinorTickWidth(1);
		yAxis.setMinorTickLength(10);
		yAxis.setMinorTickPosition(TickPosition.INSIDE);
		yAxis.setMinorTickColor(new SolidColor("#666"));
		yAxis.setGridLineWidth(0);
		yAxis.setTickPixelInterval(30);
		yAxis.setTickWidth(2);
		yAxis.setTickPosition(TickPosition.INSIDE);
		yAxis.setTickLength(10);
		yAxis.setTickColor(new SolidColor("#666"));

		yAxis.getLabels().setStep(2);
		yAxis.getLabels().setRotationPerpendicular();

		PlotBand[] plotBands = new PlotBand[3];
		plotBands[0] = new PlotBand(0, 3.0, new SolidColor("#55BF3B"));
		plotBands[1] = new PlotBand(3.0, 3.6, new SolidColor("#DDDF0D"));
		plotBands[2] = new PlotBand(3.6, 4.2, new SolidColor("#DF5353"));
		yAxis.setPlotBands(plotBands);

		series = new ListSeries("Pressure", 0.0);
		PlotOptionsGauge plotOptions = new PlotOptionsGauge();
		plotOptions.getTooltip().setValueSuffix(" bar");
		plotOptions.setAnimation(false);
		series.setPlotOptions(plotOptions);
		configuration.setSeries(series);

		createMqttClient();

		chart.drawChart(configuration);
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
			String pressureString = new String(message.getPayload());
			this.pressure = Double.valueOf(pressureString);
			parentLayout.getUI().access(new Runnable() {
				@Override
				public void run() {
					series.updatePoint(0, pressure);
				}
			});
		}
	}
}
