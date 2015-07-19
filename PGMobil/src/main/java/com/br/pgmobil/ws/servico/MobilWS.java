package com.br.pgmobil.ws.servico;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.controller.DataBase;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Util;
import com.br.pgmobil.ws.enums.EnumStatus;
import com.br.pgmobil.ws.enums.EnumStatusCartao;
import com.br.pgmobil.ws.util.UtilService;
import com.google.gson.Gson;

/**
 * Classe responsavel por se comunicar com o 
 * servidor da instituicao de cartao de credito, atraves de protocolo http(s).
 * 
 * @author tmeinel
 *
 */
@Path("/pgmobil")
public class MobilWS {
		
	Gson gson;
/*	
    @PersistenceContext
    private EntityManager entityManager;*/
	
	/**
	 * Metodo responsavel por abrir uma nova transacao.
	 * @param transacao
	 * @return String - JSON
	 * @throws ServiceException 
	 * @throws InterruptedException 
	 */
	@POST
	@Path("pagamento.efetuar")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String abrirTransacao(TransacaoDTO transacao) throws ServiceException{ 
		
		Gson gson = null;
		try {
			
			transacao.setTid(Util.gerarTid());
			transacao = UtilService.processarPedido(transacao,EnumStatus.PAGAR.getCodigo());
			
			transacao.getDadoPedido().setNumeroCartao(Util.mascarar(transacao.getDadoPedido().getNumeroCartao()));
			if(transacao.getDadoPedido().getStatus().equals(EnumStatusCartao.AUTORIZADO.getCodigo())){
				
				transacao.getDadoPedido().setCodigoAutorizacao(Util.geraCodigoAutorizacao());
				transacao.getDadoPedido().setNsu(Util.geraCodigoNSU());
				
				Thread.sleep(2000);
				DataBase.saveOrUpdate(transacao);
			}
			gson = new Gson();
			
		} catch (InterruptedException e) {
			throw new ServiceException(e);
		
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
		return gson.toJson(transacao);
	}
	
	/**
	 * Metodo responsavel por confirmar o pagamento 
	 * ex: numero do cartao,bandeira,se o cartao esta bloqueado.
	 * @param transacao
	 * @return String - JSON
	 * @throws ServiceException 
	 * @throws InterruptedException 
	 */
	@POST
	@Path("pagamento.validar")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String confirmarPagamento(TransacaoDTO transacao) throws ServiceException { 
		
		try {
			
			DataBase.saveOrUpdate(transacao);
			gson = new Gson();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return gson.toJson(transacao);
	}
	
	
	/**
	 * Metodo responsavel por listar todas as transacoes de pagamento criada.
	 * @return String - JSON
	 * @throws ServiceException 
	 */
	@GET
	@Path("pagamento.consultar")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String consultar() throws ServiceException{
		
		Gson gson = null;
		List<TransacaoDTO> transacoes;
		
		try {
			
			transacoes = UtilService.consultar();
			gson = new Gson();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return gson.toJson(transacoes);

	}
	
	/**
	 * Meotodo responsavel por buscar uma transacao com status AUTORIZADO.
	 * @param transacao
	 * @return String - JSON
	 */
	@POST
	@Path("pagamento.cancelar")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String cancelar(TransacaoDTO transacao){
		
		Gson gson = null;
		try {
						
			transacao = UtilService.processarPedido(transacao,EnumStatus.CANCELAR.getCodigo());
			gson = new Gson();
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return gson.toJson(transacao);
	}
	
	/**
	 * Metodo responsavel por validar o cancelamento, 
	 * verifica se o mesmo se encontra dentro do prazo limite estalecido.
	 * @param transacao
	 * @return String - JSON
	 */
	@POST
	@Path("pagamento.validarCancelar")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String validarCancelar(TransacaoDTO transacao){
		
		Gson gson = null;
		transacao = DataBase.getById(transacao.getTid());
		gson = new Gson();
		
		return gson.toJson(transacao);
	}
}
