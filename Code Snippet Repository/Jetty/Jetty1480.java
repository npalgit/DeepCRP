    @Test
    public void testResponseEOFContent() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 200 \r\n"
                        + "Content-Type: text/plain\r\n"
                        + "\r\n"
                        + "0123456789\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.atEOF();
        parser.parseNext(buffer);

        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("200", _uriOrStatus);
        Assert.assertEquals(null, _versionOrReason);
        Assert.assertEquals(12, _content.length());
        Assert.assertEquals("0123456789\r\n", _content);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
