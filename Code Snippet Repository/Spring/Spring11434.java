	@Test
	public void toEntityListTypeReference() throws Exception {
		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("foo".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		when(mockResponse.getHeaders()).thenReturn(httpHeaders);
		when(mockResponse.getStatusCode()).thenReturn(HttpStatus.OK);
		when(mockResponse.getBody()).thenReturn(body);

		List<HttpMessageReader<?>> messageReaders = Collections
				.singletonList(new DecoderHttpMessageReader<>(StringDecoder.allMimeTypes(true)));
		when(mockExchangeStrategies.messageReaders()).thenReturn(messageReaders);

		ResponseEntity<List<String>> result = defaultClientResponse.toEntityList(
				new ParameterizedTypeReference<String>() {
				}).block();
		assertEquals(Collections.singletonList("foo"), result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(MediaType.TEXT_PLAIN, result.getHeaders().getContentType());
	}
