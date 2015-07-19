package com.br.pgmobil.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.br.pgmobil.dto.Usuario;

@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -6847771330747521396L;
	
	Usuario usuario = new Usuario();
	
	public String entrar(){
				
		if(usuario.getLogin().equals("m4u") && usuario.getSenha().equals("m4u")){

			return "index?faces-redirect=true";
		}else if(usuario.getLogin().equals("admin") && usuario.getSenha().equals("admin")){	

			return "financeira?faces-redirect=true";
			
		}else{
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
