	@Test
	public void shouldHandleResourceByteRange() throws Exception {
		ResponseEntity<Resource> returnValue = ResponseEntity
				.ok(new ByteArrayResource("Content".getBytes(StandardCharsets.UTF_8)));
		servletRequest.addHeader("Range", "bytes=0-5");

		given(resourceRegionMessageConverter.canWrite(any(), eq(null))).willReturn(true);
		given(resourceRegionMessageConverter.canWrite(any(), eq(MediaType.APPLICATION_OCTET_STREAM))).willReturn(true);

		processor.handleReturnValue(returnValue, returnTypeResponseEntityResource, mavContainer, webRequest);

		then(resourceRegionMessageConverter).should(times(1)).write(
				anyCollection(), eq(MediaType.APPLICATION_OCTET_STREAM),
				argThat(outputMessage -> outputMessage.getHeaders().getFirst(HttpHeaders.ACCEPT_RANGES) == "bytes"));
		assertEquals(206, servletResponse.getStatus());
	}
