	@Test
	public void testSetParameterValueWithNullAndUnknownType() throws SQLException {
		StatementCreatorUtils.shouldIgnoreGetParameterType = true;
		Connection con = mock(Connection.class);
		DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
		given(preparedStatement.getConnection()).willReturn(con);
		given(dbmd.getDatabaseProductName()).willReturn("Oracle");
		given(dbmd.getDriverName()).willReturn("Oracle Driver");
		given(con.getMetaData()).willReturn(dbmd);
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, SqlTypeValue.TYPE_UNKNOWN, null, null);
		verify(preparedStatement).setNull(1, Types.NULL);
		StatementCreatorUtils.shouldIgnoreGetParameterType = false;
	}
