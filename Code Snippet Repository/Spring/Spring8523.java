	@Test
	public void beforeAndAfterTestMethodForDirtiesContextDeclaredLocallyOnClassAfterClass() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredLocallyAfterClass.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("clean"));
		beforeListener.beforeTestMethod(testContext);
		afterListener.beforeTestMethod(testContext);
		afterListener.afterTestMethod(testContext);
		beforeListener.afterTestMethod(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
	}
