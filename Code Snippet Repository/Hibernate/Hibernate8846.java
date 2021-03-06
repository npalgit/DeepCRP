	@Test
	@TestForIssue( jiraKey = "" )
	public void testSetting() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.USE_NATIONALIZED_CHARACTER_DATA, true )
				.build();

		try {
			final MetadataSources ms = new MetadataSources( ssr );
			ms.addAnnotatedClass( NationalizedBySettingEntity.class );

			final Metadata metadata = ms.buildMetadata();
			final PersistentClass pc = metadata.getEntityBinding( NationalizedBySettingEntity.class.getName() );
			final Property nameAttribute = pc.getProperty( "name" );
			if(metadata.getDatabase().getDialect() instanceof PostgreSQL81Dialect ){
				// See issue HHH-10693
				assertSame( StringType.INSTANCE, nameAttribute.getType() );
			}else {
				assertSame( StringNVarcharType.INSTANCE, nameAttribute.getType() );
			}

		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
