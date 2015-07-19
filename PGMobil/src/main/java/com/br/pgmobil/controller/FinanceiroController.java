package com.br.pgmobil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.br.pgmobil.consumer.FinanceiroConsumer;
import com.br.pgmobil.consumer.IFinanceiroConsumer;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.form.FinanceiroForm;
import com.br.pgmobil.ws.enums.EnumStatusCartao;

@ManagedBean
@RequestScoped
public class FinanceiroController implements Serializable{

	private static final long serialVersionUID = 5198618838527874770L;
	static Logger log = Logger.getLogger(FinanceiroController.class);

	FinanceiroForm form;
	TransacaoDTO transacaoSelecionada;
	FacesContext context = FacesContext.getCurrentInstance();
	
	private String idTid;
	
	@PostConstruct
	public void init(){
		getForm();
		consultarTransacaoes();
	}
	
	/***
	 * Metodo responsavel por cancelar uma compra.
	 * @param tid - indentificador unico da transacao
	 */
	public void cancelarCompra(){
		TransacaoDTO transacao = null;
		try {

			transacao = DataBase.getById(idTid);
			
			IFinanceiroConsumer financeiroConsumer = new FinanceiroConsumer();
			TransacaoDTO transacaoDto = financeiroConsumer.cancelar(transacao);
			transacao = validarCancelamento(transacaoDto);

			switch (EnumStatusCartao.valueOf(transacao.getDadoPedido().getStatusCancelamento())) {

			case AUTORIZADO:
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Seu cancelamento da transacao foi autorizado",""));	

				break;
				
			case NEGADO:
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"O cancelamento da transacao foi negado pela financeira.",""));	

				break;

			default:
				break;
			}

		} catch (ServiceException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	/**
	 * Metodo responsavel por validar o cancelamento da compra.
	 * caso a compra efetuada esteja no pedrido de 24h a mesma podera ser cancelada caso contratrio não.
	 * @param transacao
	 * @return TransacaoDTO
	 */
	private TransacaoDTO validarCancelamento(TransacaoDTO transacao){
		TransacaoDTO retornoTransacao = null;
		try {
			
			IFinanceiroConsumer financeiroConsumer = new FinanceiroConsumer();
			retornoTransacao = financeiroConsumer.validarCancelamento(transacao);
			
		} catch (ServiceException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		}
			
		return retornoTransacao;
	}


	/**
	 * Metodo que retorna todas as transaçoes efetuadas.
	 */
	public void consultarTransacaoes(){
		List<TransacaoDTO> transacoes = null;
		try {
			
			IFinanceiroConsumer financeiroConsumer = new FinanceiroConsumer();
			transacoes = financeiroConsumer.consular();
			form.setTransacoes(transacoes);
			
		} catch (ServiceException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	public FinanceiroForm getForm() {
		if(form == null){
			form = new FinanceiroForm();
		}
		return form;
	}

	public TransacaoDTO getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(TransacaoDTO transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

	public String getIdTid() {
		return idTid;
	}

	public void setIdTid(String idTid) {
		this.idTid = idTid;
	}
	
	
	
}
