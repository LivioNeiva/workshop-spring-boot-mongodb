package com.livioneiva.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livioneiva.workshopmongo.services.exceptions.ObjectNotFoundException;

//essa annotations informa q uma classe é responsavel por tratar possiveis erros nas minhas requisições
@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	 é um metodo para tratar a classe ObjectNotFoundException, retorna erro quando nao passamos id existente
	 ObjectNotFoundException é o tipo da excections eu vou tratar
	 HttpServletRequest = informa o caminho q o id nao foi encontrado ou caminho q gerou exception
	 */
	//@ExceptionHandler = quando ocorrer essa excetion ObjectNotFoundException, será feito o tratamento a baixo
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		//System.currentTimeMillis() = pega o istante atual do sistema
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não Encontrado", e.getMessage(), request.getRequestURI());
		//status = vai informar o status NOT_FOUND
		return ResponseEntity.status(status).body(err);
		
	}
}
