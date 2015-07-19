package com.br.pgmobil.ws.interfaces;

import com.br.pgmobil.dto.TransacaoDTO;

public interface ICartao {

	TransacaoDTO isBloqueado(TransacaoDTO transacao);
	TransacaoDTO isSaldoInsuficiente(TransacaoDTO transacao);
	TransacaoDTO isNegado(TransacaoDTO transacao);
}
