package com.br.pgmobil.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class CarrinhoController {
	
	Long valor;
	String caminhoImg;
	FacesContext fc = FacesContext.getCurrentInstance();
	
	public String comprar(){
		
		Map<String,String> parametro1 = fc.getExternalContext().getRequestParameterMap();
		valor = Long.parseLong(parametro1.get("valorCompra"));

		Map<String,String> parametro2 = fc.getExternalContext().getRequestParameterMap();
		caminhoImg = parametro2.get("imagem");

		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);
		session.setAttribute("valorPago", valor);
		session.setAttribute("imagem", caminhoImg);

		return "detalhe?faces-redirect=true";	
	}

	
	public String getCaminhoImg() {
		return caminhoImg;
	}

	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
