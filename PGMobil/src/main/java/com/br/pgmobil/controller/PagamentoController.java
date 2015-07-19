package com.br.pgmobil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.br.pgmobil.consumer.IPagamentoConsumer;
import com.br.pgmobil.consumer.PagamentoConsumer;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.PagamentoDTO;
import com.br.pgmobil.dto.PedidoDTO;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.form.PagamentoForm;
import com.br.pgmobil.ws.enums.EnumStatusCartao;


/**
 * 
 * @author tmeinel
 *
 */
@ManagedBean
@ViewScoped
public class PagamentoController implements Serializable {

	private static final long serialVersionUID = -67512491244934747L;
	static Logger log = Logger.getLogger(PagamentoController.class);
	
	FacesContext context = FacesContext.getCurrentInstance();
	PagamentoForm form = null;
	
	private Long valor;
	private String caminhoImg;

	@PostConstruct
	public void init(){
		form = getForm();
		form.setTransacoes(new ArrayList<TransacaoDTO>());
		form.setValor(recuperarValores());
		form.setCaminhoImg(caminhoImg);
	}
	
	public Long recuperarValores(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();  
	    HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);
	    valor = (Long) session.getAttribute("valorPago");
	    caminhoImg = (String) session.getAttribute("imagem");
	    session.removeAttribute("valorPago");
	    session.removeAttribute("imagem");
	    
	    return valor;
	}

	/**
	 * Metodo reponsavel por enviar
	 * um requisição e solicitar o a abertura de transacao
	 */
	@SuppressWarnings("deprecation")
	public void finalizarCompra(){
		
		try {
			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(form.getValor(),new Date().toLocaleString(),"loja de informatica",null,form.getNumeroCartao(),null,null,null),
					new PagamentoDTO(form.getTipo(), form.getQuantidade(), null, null));
			
			PagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
			
			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			transacaoDto = validarPagamento(transacaoDto);
			
			switch (EnumStatusCartao.valueOf(transacaoDto.getDadoPedido().getStatus())) {
			
			case AUTORIZADO:
					
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Seu pagamento foi autorizado",""));	
				
				break;
			case SALDO_INSUFICIENTE:
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"O saldo do seu cartao é inferior ao valor da compra.",""));	
				
				break;
			case NEGADO:
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sua solicitação foi negado por nosso servidor.",""));	
				
				break;

			default:
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERRO INTERNO NO SISTEMA",""));	
				
				break;
			}

			
		} catch (ServiceException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		}
	}
	
	
	public String cancelarCompra(){
		
		return "carrinho?faces-redirect=true";
	}
	
	
	/**
	 * Metodo responsavel por validar os dados da compra no cartao.
	 * @param transacao
	 * @return TransacaoDTO
	 */
	private TransacaoDTO validarPagamento(TransacaoDTO transacao){
		TransacaoDTO retornoTransacao = null;
		try {
			
			IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
			retornoTransacao = pagamentoConsumer.validarPagamento(transacao);
			
			
		} catch (ServiceException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		}
		
		return retornoTransacao;
	}
	
	

	public PagamentoForm getForm() {
		if(form == null){
			form = new PagamentoForm();
		}
		return form;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getCaminhoImg() {
		return caminhoImg;
	}

	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}

	

}
