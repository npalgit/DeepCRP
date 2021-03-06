    public void testDynamicAttributes() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");
        testAction.setList(new String[][]{
                {"hello", "world"},
                {"foo", "bar"}
        });

        RadioTag tag = new RadioTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("");
        tag.setList("list");
        tag.setListKey("top[0]");
        tag.setListValue("top[1]");
        tag.setDynamicAttribute(null, "dojo", "checked: %{top[0]}");

        tag.doStartTag();
        tag.doEndTag();

        verify(RadioTag.class.getResource("Radio-7.txt"));
    }
