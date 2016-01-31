/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.engine.junit5.execution;

import org.junit.gen5.api.*;
import org.junit.gen5.engine.*;
import org.junit.gen5.engine.reporting.*;
import org.mockito.*;

public class JUnit5EngineExecutionContextTests {

	//this verifies that the reporting mechanism within the EngineExecutionListener survives the builder
	//is this really what we want to check?
	@Test
	void getExecutionListener() {
		EngineExecutionListener engineExecutionListener = Mockito.spy(EngineExecutionListener.class);
		JUnit5EngineExecutionContext originalEngineExecutionContext = new JUnit5EngineExecutionContext(
			engineExecutionListener);
		JUnit5EngineExecutionContext newEngineExecutionContext = JUnit5EngineExecutionContext.builder(
			originalEngineExecutionContext).build();

		ReportEntry entry = ReportEntry.from("one", "two");
		newEngineExecutionContext.getExecutionListener().reportingEntryPublished(null, entry);
		Mockito.verify(engineExecutionListener, Mockito.times(1)).reportingEntryPublished(null, entry);
	}

}
