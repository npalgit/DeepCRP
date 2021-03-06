	@Test
	public void testManyToOneDefaultFetch() {
		final AssociationType associationType = determineAssociationType( AnEntity.class, "otherEntityDefault" );
		final org.hibernate.FetchMode fetchMode = determineFetchMode( AnEntity.class, "otherEntityDefault" );
		assertSame( org.hibernate.FetchMode.JOIN, fetchMode );
		final FetchStyle fetchStyle = FetchStrategyHelper.determineFetchStyleByMetadata(
				fetchMode,
				associationType,
				sessionFactory()
		);
		// batch size is ignored with org.hibernate.FetchMode.JOIN
		assertSame( FetchStyle.JOIN, fetchStyle );
		final FetchTiming fetchTiming = FetchStrategyHelper.determineFetchTiming(
				fetchStyle,
				associationType,
				sessionFactory()
		);
		assertSame( FetchTiming.IMMEDIATE, fetchTiming );
	}
