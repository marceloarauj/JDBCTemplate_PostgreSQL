package com.sjp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.sjp.models.Usuario;

// camada de acesso aos dados 
@Repository
public class UsuarioDaoImpl extends JdbcDaoSupport implements UsuarioDAO {

	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void iniciar() {
		setDataSource(dataSource);
	}
	
	// insert único no banco PostgreSQL
	@Override
	public void insert(Usuario user) {
		
		String comand = "INSERT INTO usuarios(user_id,nome,cpf) VALUES(?,?,?)";
		
		getJdbcTemplate().update
		      (comand, new Object[] {user.getId(),user.getNome(),user.getCpf()});
		
	}

	// insert de listas no banco PostgreSQL
	
	@Override
	public void insertBatch(List<Usuario> users) {
		
		String comand = "insert into usuarios(user_id,nome,cpf) value(?,?,?)";
		
		getJdbcTemplate().batchUpdate(comand,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				Usuario user = users.get(i);
				
				ps.setInt(1, user.getId());
				ps.setString(2, user.getNome());
				ps.setString(3, user.getCpf());
			}
			
			@Override
			public int getBatchSize() {
				
				return users.size();
			}
		});
		
	}
	
	// obter usuario por id
	@Override
	public Usuario getUsuarioID(long user_id) {
		
		String comandSql = "select * from usuario where user_id =?";
		
		return (Usuario)getJdbcTemplate().queryForObject(comandSql, new Object[] {user_id},
				new RowMapper<Usuario>() {
			
			@Override
			public Usuario mapRow(ResultSet rs, int rwNumber) throws SQLException{
				
				Usuario user = new Usuario();
				user.setId(rs.getInt("user_id"));
				user.setNome(rs.getString("nome"));
				user.setNome(rs.getString("cpf"));
				
				return user;
			}
		});
		
		
	}

	// pegar nome por id 
	@Override
	public String getNomePorId(long user_id) {
		
		String comand = "select nome from usuarios where user_id = ? ";
		String res = getJdbcTemplate().queryForObject
				(comand,new Object[] {user_id}, String.class);
		
		return res;
	}

	
	//retorna todos os usuarios do banco
	@Override
	public List<Usuario> returnUsers() {
		
		String comand = "select * from usuarios";
		
		List<Map<String,Object>> linha = getJdbcTemplate().queryForList(comand);
		
		List<Usuario> total = new ArrayList<Usuario>();
		
		for(Map<String,Object> row:linha ) {
			
			Usuario user = new Usuario();
			
			user.setId((int)row.get("user_id"));
			user.setNome((String)row.get("nome"));
			user.setCpf((String) row.get("cpf"));
			
			total.add(user);
		}
		
		return total;
	}

}
