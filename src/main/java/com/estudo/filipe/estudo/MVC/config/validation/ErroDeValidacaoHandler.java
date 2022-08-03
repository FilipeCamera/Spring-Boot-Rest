package com.estudo.filipe.estudo.MVC.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	final MessageSource messageSource;
	
	public ErroDeValidacaoHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoDto> handle(MethodArgumentNotValidException exception) {
		List<ErroValidacaoDto> errorsDto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(errors -> {
			String mensagem = messageSource.getMessage(errors, LocaleContextHolder.getLocale());
			ErroValidacaoDto error = new ErroValidacaoDto(errors.getField(), mensagem);
			
			errorsDto.add(error);
		});
		return errorsDto;
	}
}
