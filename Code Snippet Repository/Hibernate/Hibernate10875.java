	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof QuotedFieldsEntity) ) {
			return false;
		}

		QuotedFieldsEntity that = (QuotedFieldsEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( data1 != null ? !data1.equals( that.data1 ) : that.data1 != null ) {
			return false;
		}
		if ( data2 != null ? !data2.equals( that.data2 ) : that.data2 != null ) {
			return false;
		}

		return true;
	}