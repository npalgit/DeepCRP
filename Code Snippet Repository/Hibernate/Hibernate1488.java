	private void addNamedStoredProcedureQueryIfNeeded(NamedStoredProcedureQuery annotation, List<NamedStoredProcedureQuery> queries) {
		if ( annotation != null ) {
			String queryName = annotation.name();
			boolean present = false;
			for ( NamedStoredProcedureQuery current : queries ) {
				if ( current.name().equals( queryName ) ) {
					present = true;
					break;
				}
			}
			if ( !present ) {
				queries.add( annotation );
			}
		}
	}
