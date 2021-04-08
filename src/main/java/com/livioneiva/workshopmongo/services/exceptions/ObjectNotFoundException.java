package com.livioneiva.workshopmongo.services.exceptions;

import com.livioneiva.workshopmongo.domain.User;

public class ObjectNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//estou sobrecarregando pelo constutor a classe RunTimeException
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
