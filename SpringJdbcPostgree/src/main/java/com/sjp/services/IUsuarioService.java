package com.sjp.services;

import java.util.List;

import com.sjp.models.Usuario;

public interface IUsuarioService {
	  
	  	public void insert(Usuario user);
		public void insertBatch(List<Usuario> users);
		public Usuario getUsuarioID(long user_id);
		public String getNomePorId(long user_id);
		public List<Usuario> returnUsers();
}
