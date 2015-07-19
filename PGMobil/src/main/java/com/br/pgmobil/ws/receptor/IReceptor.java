package com.br.pgmobil.ws.receptor;

import com.br.pgmobil.dto.TransacaoDTO;



public interface IReceptor {

	TransacaoDTO recebe(TransacaoDTO transacaoDTO);
}
