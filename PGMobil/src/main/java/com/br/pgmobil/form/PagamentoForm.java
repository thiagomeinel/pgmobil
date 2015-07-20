package com.br.pgmobil.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.br.pgmobil.dto.TransacaoDTO;

public class PagamentoForm implements Serializable{

	private static final long serialVersionUID = -5382663975652855577L;
	private String nome;
	private String numeroCartao;
	private Long valor;
	private Date dataHora;
	private String tipo;
	private int quantidade;
	private String caminhoImg;
	private List<TransacaoDTO> transacoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public List<TransacaoDTO> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(List<TransacaoDTO> transacoes) {
		this.transacoes = transacoes;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getCaminhoImg() {
		return caminhoImg;
	}
	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}
	
	
}
