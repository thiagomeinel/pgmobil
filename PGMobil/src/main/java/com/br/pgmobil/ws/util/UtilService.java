package com.br.pgmobil.ws.util;

import java.util.ArrayList;
import java.util.List;

import com.br.pgmobil.consumer.enums.EnumBandeira;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.controller.DataBase;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.factory.ComunicadorFactory;
import com.br.pgmobil.factory.MasterComunicadorFactory;
import com.br.pgmobil.factory.VisaComunicadorFactory;
import com.br.pgmobil.util.Util;
import com.br.pgmobil.ws.emissor.IEmissor;
import com.br.pgmobil.ws.receptor.IReceptor;



/**
 * Classe utilitaria para processar validar dados emitidos.
 * @author tmeinel
 *
 */
public class UtilService {

	/**
	 * Metodo reponsavel por processar o pedido emitido pelo servidor.
	 * @param transacao
	 * @return TransacaoDTO
	 * @throws ServiceException 
	 */
	public static TransacaoDTO processarPedido(TransacaoDTO transacao, String acao) throws ServiceException{
		
		ComunicadorFactory comunicadorFactory = null;
		TransacaoDTO transacaoDto = null;

		try {
			
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
			
			IReceptor receptor = comunicadorFactory.criarReceptor();
			receptor.recebe(transacaoDto);

		}catch (Exception e) {
			throw new ServiceException(e);
		}


		return transacaoDto;

	}
	
	public static List<TransacaoDTO> consultar() throws ServiceException{
		
		List<TransacaoDTO> transacoes = new ArrayList<TransacaoDTO>();
		try {
			transacoes = DataBase.getAll();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return transacoes;
	}
	
	
	public static TransacaoDTO listarPorId(String id) throws ServiceException{
		TransacaoDTO transacao = null;
		try {
			
			transacao = DataBase.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return transacao;
	}
}
