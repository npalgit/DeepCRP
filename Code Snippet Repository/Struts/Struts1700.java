    public void testIteratorWithBegin() throws Exception {
        // given
        final ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new FooAction());

        StringWriter out = new StringWriter();

        IteratorComponent ic = new IteratorComponent(stack);
        ic.setValue("items");
        ic.setVar("val");
        ic.setBegin("1");
        Property prop = new Property(stack);

        ic.getComponentStack().push(prop);
        ic.getComponentStack().push(prop);
        ic.getComponentStack().push(prop);

        String body = " ";

        // when
        assertTrue(ic.start(out));

        for (int i = 0; i < 3; i++) {
            prop.start(out);
            prop.end(out, body);
            ic.end(out, null);
        }

        // then
        assertEquals("item2 item3 item4 ", out.getBuffer().toString());
    }
