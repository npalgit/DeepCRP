    @Test
    public void testCROnlyRequest()
    throws Exception
    {
        String str = "--AaB03x\r"+
        "content-disposition: form-data; name=\"field1\"\r"+
        "\r"+
        "Joe Blow\r"+ 
        "--AaB03x\r"+
        "content-disposition: form-data; name=\"field2\"\r"+
        "\r"+
        "Other\r"+        
        "--AaB03x--\r";

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(str.getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertThat(parts.size(), is(2));
        
        assertThat(parts.size(), is(2));
        Part p1 = mpis.getPart("field1");
        assertThat(p1, notNullValue());
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IO.copy(p1.getInputStream(), baos);
        assertThat(baos.toString("UTF-8"), is("Joe Blow"));
        
        Part p2 = mpis.getPart("field2");
        assertThat(p2, notNullValue());
        baos = new ByteArrayOutputStream();
        IO.copy(p2.getInputStream(), baos);
        assertThat(baos.toString("UTF-8"), is("Other"));
    }
