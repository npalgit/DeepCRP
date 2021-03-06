	@Test
	void beforeEachAndAfterEachCallbacksDeclaredOnSuperclassAndSubclass() {
		LauncherDiscoveryRequest request = request().selectors(selectClass(ChildTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		assertEquals(asList(
			"fooBeforeEachCallback",
			"barBeforeEachCallback",
				"testChild",
			"barAfterEachCallback",
			"fooAfterEachCallback"
		), callSequence, "wrong call sequence");
		// @formatter:on
	}
