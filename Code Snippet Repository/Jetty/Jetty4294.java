    @Test
    public void testIsNotGzipCompressedByExcludedContentTypeWithCharset() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().addExcludedMimeTypes("text/plain");

        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("test_quotes.txt",filesize);
        tester.addMimeType("txt","text/plain;charset=UTF-8");

        // Add content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();
            HttpTester.Response http = assertIsResponseNotGzipCompressed(tester,"GET","test_quotes.txt",filesize,HttpStatus.OK_200);
            Assert.assertNull(http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
