	@Test
	public void earlyRemove() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		cache.put(key, value);

		service.earlyRemove(keyItem);

		assertNull(cache.get(key));
	}
