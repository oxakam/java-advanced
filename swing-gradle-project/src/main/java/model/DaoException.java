package model;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L; //added by RuntimeException

	/* constructor */
	public DaoException(SQLException e) {
		super(e);
	}
	
}
