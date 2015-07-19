package com.br.pgmobil.mock;

import java.io.Serializable;
import java.util.Date;

import com.br.pgmobil.dto.PagamentoDTO;
import com.br.pgmobil.dto.PedidoDTO;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Util;


public class TransacaoMock implements Serializable{

	private static final long serialVersionUID = -2580503620006255483L;


	/**
	 * Meotodo que retorna a resposta do pagamento da transacao.
	 * @param transacao
	 * @return TransacaoDTO
	 */
	public static TransacaoDTO respostaAberturaTransacao(TransacaoDTO transacao){

		TransacaoDTO transacaoDTO = new TransacaoDTO(Util.gerarTid(), 
				new PedidoDTO(transacao.getDadoPedido().getValor(), new Date().toString(),transacao.getDadoPedido().getDescricao(), 
						null, transacao.getDadoPedido().getNumeroCartao(),null, null,null), 
						new PagamentoDTO(transacao.getFormaPagamento().getTipo(),transacao.getFormaPagamento().getQuantidade(), null,null));

		return transacaoDTO;
	}

	
	/**
	 * Metodo que retorna a resposta de cancelamento da transacao.
	 * @param transacao
	 * @return
	 */
	public TransacaoDTO respostaCancelamento(TransacaoDTO transacao){

		TransacaoDTO transacaoDTO = new TransacaoDTO(transacao.getTid(), 
				new PedidoDTO(transacao.getDadoPedido().getValor(), new Date().toString(), transacao.getDadoPedido().getDescricao(), 
						transacao.getDadoPedido().getNsu(), transacao.getDadoPedido().getNumeroCartao(),transacao.getDadoPedido().getCodigoAutorizacao(), transacao.getDadoPedido().getStatus(),null), 
						new PagamentoDTO(transacao.getFormaPagamento().getTipo(),transacao.getFormaPagamento().getQuantidade(), null,null));

		return transacaoDTO;
	}
	
}
