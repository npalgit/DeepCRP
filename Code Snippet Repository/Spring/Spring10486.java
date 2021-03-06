	@Test
	public void multipart() throws Exception {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("name 1", "value 1");
		parts.add("name 2", "value 2+1");
		parts.add("name 2", "value 2+2");
		Resource logo = new ClassPathResource("/org/springframework/http/converter/logo.jpg");
		parts.add("logo", logo);

		HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(parts);
		Future<URI> future = template.postForLocation(baseUrl + "/multipart", requestBody);
		future.get();
	}
