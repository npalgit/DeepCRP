	@Test
	public void withMultiValueChecked() throws Exception {
		this.tag.setPath("stringArray");
		this.tag.setValue("foo");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element checkboxElement = (Element) document.getRootElement().elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("checkbox", checkboxElement.attribute("type").getValue());
		assertEquals("stringArray", checkboxElement.attribute("name").getValue());
		assertEquals("checked", checkboxElement.attribute("checked").getValue());
		assertEquals("foo", checkboxElement.attribute("value").getValue());
	}
