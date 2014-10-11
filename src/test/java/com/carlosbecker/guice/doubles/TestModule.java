package com.carlosbecker.guice.doubles;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Worker.class).to(MyWorker.class);
	}
}
