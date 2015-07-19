package com.br.pgmobil.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.br.pgmobil.dto.TransacaoDTO;

/**
 * Classe criada para simular a base de dados do servidor de Cartao de Credito.
 * @author tmeinel
 *
 */
public class DataBaseSimulator {

	private static Map<Integer,TransacaoDTO> TRANS_MAP = new HashMap<Integer, TransacaoDTO>();
	private static Integer LIMITE_CARTAO_VISA = 10000;
	private static Integer LIMITE_CARTAO_MASTER = 5000;
	private static String NUMERO_CARTAO_VISA = "5899161494723904";
	
	static{
		
/*		TransacaoMock transacaoMock = new TransacaoMock();
		
		TRANS_MAP.put(1, transacaoMock.pagamentoAutorizado());
		TRANS_MAP.put(2, transacaoMock.pagamentoNaoAutorizado());
		TRANS_MAP.put(3, transacaoMock.pagamentoPaceladoADM());
		TRANS_MAP.put(4, transacaoMock.pagamentoParceladoLoja());*/
	}
			
		public static TransacaoDTO getById(Integer id){
			return TRANS_MAP.get(id);
		}
		
		public static List<TransacaoDTO> getAll(){
			List<TransacaoDTO> transacoes = new ArrayList<TransacaoDTO>(TRANS_MAP.values());
			return transacoes;
		}
		
		public static Integer getLimitVisa(){
			return LIMITE_CARTAO_VISA;
		}
		
		public static Integer getLimiteMaster(){
			return LIMITE_CARTAO_MASTER;
		}
		
		public static String getNumeroCartao(){
			return NUMERO_CARTAO_VISA;
		}
}
