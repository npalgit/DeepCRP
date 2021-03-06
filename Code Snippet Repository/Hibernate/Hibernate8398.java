	@Test
	public void testManyToOneGeneratedIdsOnSave() {
		// NOTES: Child defines a many-to-one back to its Parent.  This
		// association does not define persist cascading (which is natural;
		// a child should not be able to create its parent).
		try {
			Session s = openSession();
			s.beginTransaction();
			Parent p = new Parent( "parent" );
			Child c = new Child( "child" );
			c.setParent( p );
			s.save( c );
			try {
				s.getTransaction().commit();
				fail( "expecting TransientObjectException on flush" );
			}
			catch (IllegalStateException e) {
				assertTyping( TransientObjectException.class, e.getCause() );
				log.trace( "handled expected exception", e );
				s.getTransaction().rollback();
			}
			finally {
				s.close();
			}
		}
		finally {
			cleanupData();
		}
	}
