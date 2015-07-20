package com.br.pgmobil.ws.servico;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.br.pgmobil.consumer.enums.EnumBandeira;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.controller.DataBase;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.factory.ComunicadorFactory;
import com.br.pgmobil.factory.MasterComunicadorFactory;
import com.br.pgmobil.factory.VisaComunicadorFactory;
import com.br.pgmobil.mock.CartaoMock;
import com.br.pgmobil.util.Util;
import com.br.pgmobil.ws.emissor.IEmissor;
import com.br.pgmobil.ws.enums.EnumCartao;
import com.br.pgmobil.ws.enums.EnumStatusCartao;
import com.br.pgmobil.ws.interfaces.IProcessamentoController;
import com.br.pgmobil.ws.receptor.IReceptor;

public class ProcessamentoController implements IProcessamentoController{

	/**
	 * Metodo reponsavel por processar o pedido emitido pelo servidor.
	 * @param transacao
	 * @return TransacaoDTO
	 * @throws ServiceException 
	 */	
	public TransacaoDTO processarPedido(TransacaoDTO transacao, ComunicadorFactory comunicadorFactory,String acao) throws ServiceException{

		TransacaoDTO transacaoDto = null;
		String bandeira = Util.buscarBandeira(transacao.getDadoPedido().getNumeroCartao());

		switch (EnumBandeira.valueOf(bandeira)) {

		case BRANDX:
			comunicadorFactory = new VisaComunicadorFactory();
			break;
		case BRANDY:
			comunicadorFactory = new MasterComunicadorFactory();
			break;

		default:
			break;
		}

		IEmissor emissor = comunicadorFactory.criarEmissor();
		transacaoDto = emissor.enviar(transacao,acao);
		
		validarDadosCartao(transacao);
		
		IReceptor receptor = comunicadorFactory.criarReceptor();
		receptor.recebe(transacaoDto);

		return transacaoDto;
	}
	
	/**
	 * Meotodo que valida os dados informado.
	 */
	private TransacaoDTO validarDadosCartao(TransacaoDTO transacao) throws ServiceException{	
		
	try {
			
			if(transacao.getDadoPedido().getNumeroCartao().equals(CartaoMock.NUMERO_CARTAO_BLOQUEADO)){
				
				transacao.getDadoPedido().setStatus(EnumStatusCartao.NAO_AUTORIZADO.getCodigo());
				
			} else if (transacao.getDadoPedido().getValor()> EnumCartao.LIMITE_CARTAO_BRANDX.getCodigo()){
				
				transacao.getDadoPedido().setStatus(EnumStatusCartao.SALDO_INSUFICIENTE.getCodigo());
				
			}else{
				
				transacao.getDadoPedido().setStatus(EnumStatusCartao.AUTORIZADO.getCodigo());
				transacao.getDadoPedido().setCodigoAutorizacao(Util.geraCodigoAutorizacao());
				transacao.getDadoPedido().setNsu(Util.geraCodigoNSU());
			}
		} catch (Exception e) {
			
			throw new ServiceException(e);
		}
		
		
		return transacao;
	}

	
	@Override
	public TransacaoDTO cancelarPedido(TransacaoDTO transacao) throws ServiceException {

		TransacaoDTO retorno = new TransacaoDTO();
		try {
			Long tempoLimite = Util.getMinutos(Util.converterStringToDate(transacao.getDadoPedido().getDataHora(), "DD/MM/YY HH:mm:ss"),new Date());
			retorno = DataBase.getById(transacao.getTid());
			if(tempoLimite > 10L){
				
				retorno.getDadoPedido().setStatusCancelamento(EnumStatusCartao.NEGADO.getCodigo());
				
			}else{

				retorno.getDadoPedido().setStatusCancelamento(EnumStatusCartao.AUTORIZADO.getCodigo());
			}
			
		} catch (ParseException e) {
			throw new ServiceException(e);
		}

		return retorno;
	}

	@Override
	public List<TransacaoDTO> ConsultarPedido(){
		List<TransacaoDTO> transacoes = new ArrayList<TransacaoDTO>();
		transacoes = DataBase.getAll();
		
		return transacoes;
	}

	@Override
	public TransacaoDTO processarPedido(TransacaoDTO transacao) throws ServiceException {
		
		transacao = validarDadosCartao(transacao);		
		
		return transacao;
	}
	
	

}
