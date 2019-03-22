package com.sjp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.dao.UsuarioDAO;
import com.sjp.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	UsuarioDAO usuarioDao;
	
	@Override
	public void insert(Usuario user) {
		
		usuarioDao.insert(user);
		
	}

	@Override
	public void insertBatch(List<Usuario> users) {
		usuarioDao.insertBatch(users);
		
	}

	@Override
	public Usuario getUsuarioID(long user_id) {
		
		return null;
	}

	@Override
	public String getNomePorId(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> returnUsers() {
		
		return null;
	}

}
