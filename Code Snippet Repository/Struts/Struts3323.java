    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.response = new MockHttpServletResponse();
        this.request = new MockHttpServletRequest();
        this.context = ActionContext.getContext();
        this.context.put(StrutsStatics.HTTP_RESPONSE, this.response);
        this.context.put(StrutsStatics.HTTP_REQUEST, this.request);
        this.stack = context.getValueStack();
        this.servletContext = new MockServletContext();
        this.context.put(StrutsStatics.SERVLET_CONTEXT, this.servletContext);
        this.invocation = new MockActionInvocation();
        this.invocation.setInvocationContext(this.context);
        this.invocation.setStack(this.stack);
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setConfig(new ActionConfig.Builder(null, null, null).build());
        this.invocation.setProxy(mockActionProxy);
    }
