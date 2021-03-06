    @Test
    @Stress("")
    public void testCase9_2_3() throws Exception
    {
        byte data[] = new byte[1 * MBYTE];
        Arrays.fill(data,(byte)0x23);
        ByteBuffer buf = ByteBuffer.wrap(data);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new BinaryFrame().setPayload(buf));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new BinaryFrame().setPayload(clone(buf)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect,4,TimeUnit.SECONDS);
        }
    }
