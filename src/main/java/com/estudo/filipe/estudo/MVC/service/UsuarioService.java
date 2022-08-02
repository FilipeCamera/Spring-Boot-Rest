package com.estudo.filipe.estudo.MVC.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.filipe.estudo.MVC.model.Usuario;
import com.estudo.filipe.estudo.MVC.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll(pageable);
	}

	public Optional<Usuario> findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}
	
	@Transactional
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
		// TODO Auto-generated method stub
		
	}
}
