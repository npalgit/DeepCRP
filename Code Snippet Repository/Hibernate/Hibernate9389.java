	@Test
	@TestForIssue(jiraKey = "")
	public void testJoinedSubclassForeignKeyNameIsNotAutoGeneratedWhenProvided() throws Exception {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( Environment.HBM2DDL_AUTO, "none" )
				.build();
		try {
			File output = File.createTempFile( "update_script", ".sql" );
			output.deleteOnExit();

			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addResource( "org/hibernate/test/schemaupdate/manytomany/UserGroup.hbm.xml" )
					.buildMetadata();
			metadata.validate();

			new SchemaExport()
					.setOutputFile( output.getAbsolutePath() )
					.setDelimiter( ";" )
					.setFormat( true )
					.create( EnumSet.of( TargetType.SCRIPT ), metadata );

			String fileContent = new String( Files.readAllBytes( output.toPath() ) );
			assertThat( fileContent.toLowerCase().contains( "fk_user_group" ), is( true ) );
			assertThat( fileContent.toLowerCase().contains( "fk_group_user" ), is( true ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}