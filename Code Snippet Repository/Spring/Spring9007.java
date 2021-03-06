	@Test
	public void jtaTransactionManagerWithExistingTransactionAndSynchronizationNever() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);

		JtaTransactionManager ptm = newJtaTransactionManager(ut);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		ptm.setTransactionSynchronization(JtaTransactionManager.SYNCHRONIZATION_NEVER);
		ptm.afterPropertiesSet();

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
				status.setRollbackOnly();
			}
		});
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());

		verify(ut).setRollbackOnly();
	}
