package com.estudo.filipe.estudo.MVC.controller;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.filipe.estudo.MVC.controller.dto.UsuarioDto;
import com.estudo.filipe.estudo.MVC.model.Usuario;
import com.estudo.filipe.estudo.MVC.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid UsuarioDto usuarioDto) {
		if(usuarioService.existsByEmail(usuarioDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Este usuário já existe!");
		}
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> getAllUsuarios(@PageableDefault(page = 0, size = 10, sort ="îd", direction = Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDto usuarioDto) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado!");
		}
		
//		var usuarioUpdate =  usuarioOptional.get();
		
//		usuarioUpdate.setNome(usuarioDto.getNome());
//		usuarioUpdate.setEmail(usuarioDto.getEmail());
//		usuarioUpdate.setSenha(usuarioDto.getSenha());
		
		var usuarioUpdate = new Usuario();
		
		BeanUtils.copyProperties(usuarioDto, usuarioUpdate);
		
		usuarioUpdate.setId(usuarioOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioUpdate));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado!");
		}
		
		usuarioService.delete(usuarioOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("usuário deletado!");
		
		
	}
		
	
}
