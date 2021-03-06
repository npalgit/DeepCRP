    @Test
    public void testCase2_10() throws Exception
    {
        // send 10 pings each with unique payload
        // send close
        // expect 10 pongs with our unique payload
        // expect close

        int pingCount = 10;

        List<WebSocketFrame> send = new ArrayList<>();
        List<WebSocketFrame> expect = new ArrayList<>();

        for (int i = 0; i < pingCount; i++)
        {
            String payload = String.format("ping-%d[%X]",i,i);
            send.add(new PingFrame().setPayload(payload));
            expect.add(new PongFrame().setPayload(payload));
        }
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
