package com.br.pgmobil.factory;

import com.br.pgmobil.ws.emissor.IEmissor;
import com.br.pgmobil.ws.receptor.IReceptor;


public interface ComunicadorFactory {

	IEmissor criarEmissor();
	IReceptor criarReceptor();
}
