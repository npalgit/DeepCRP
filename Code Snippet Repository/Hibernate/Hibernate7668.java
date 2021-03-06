	@Test
	public void testAccessAsIncorrectSubclass() {
		Session s = openSession();
		s.beginTransaction();
		Employee e = new Employee();
		e.setId( 4 );
		e.setName( "Steve" );
		e.setSex( 'M' );
		e.setTitle( "grand poobah" );
		s.save( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Customer c = ( Customer ) s.get( Customer.class, e.getId() );
		s.getTransaction().commit();
		s.close();
		assertNull( c );

		s = openSession();
		s.beginTransaction();
		e = ( Employee ) s.get( Employee.class, e.getId() );
		c = ( Customer ) s.get( Customer.class, e.getId() );
		s.getTransaction().commit();
		s.close();
		assertNotNull( e );
		assertNull( c );

		s = openSession();
		s.beginTransaction();
		s.delete( e );
		s.getTransaction().commit();
		s.close();
	}
