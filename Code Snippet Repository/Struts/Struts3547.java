	private void checkEntry(Map.Entry entry) {
		if(entry.getKey().equals("testAttribute1")) {
        	assertEquals("testValue1", entry.getValue());
        }
        else if(entry.getKey().equals("testAttribute2")) {
        	assertEquals("testValue2", entry.getValue());
        }
        else {
        	fail("Unexpected entry in etry set: " + entry);
        }
	}
