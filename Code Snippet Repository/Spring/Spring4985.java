	@Test
	public void createCollectionIsNotTypeSafeForEnumSet() {
		Collection<Integer> ints = createCollection(EnumSet.class, Color.class, 3);

		// Use a try-catch block to ensure that the exception is thrown as a result of the
		// next line and not as a result of the previous line.
		try {
			// Note that ints is of type Collection<Integer>, but the collection returned
			// by createCollection() is of type Collection<Color>. Thus, 42 cannot be cast
			// to a Color.
			ints.add(42);
			fail("Should have thrown a ClassCastException");
		}
		catch (ClassCastException e) {
			/* expected */
		}
	}
