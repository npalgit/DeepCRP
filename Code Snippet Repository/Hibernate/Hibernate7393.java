	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryEntityLeftJoinFetchEmptyCollection() {
		AnEntity.PK id = doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = new AnEntity( new AnEntity.PK( "first", "last" ) );
					session.persist( anEntity );
					return anEntity.id;
				}
		);

		doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = session.createQuery(
							"from AnEntity e left join fetch e.names where e.id = :id",
							AnEntity.class
					).setParameter( "id", id ).uniqueResult();
					assertTrue( Hibernate.isInitialized( anEntity.names ) );
					assertTrue( anEntity.names.isEmpty() );
				}
		);
	}
