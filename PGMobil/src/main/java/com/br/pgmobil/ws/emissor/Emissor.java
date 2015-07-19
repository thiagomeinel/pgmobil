package com.br.pgmobil.ws.emissor;

import com.br.pgmobil.consumer.enums.EnumBandeira;

public class Emissor{

	public IEmissor criarEmissor (String tipoDoEmissor ){
		IEmissor emissor = null;
		
		switch (EnumBandeira.valueOf(tipoDoEmissor)) {
		
		case BRANDX:
			emissor = new EmissorBrandX();
			break;
			
		case BRANDY:
			emissor = new EmissorBrandY();
			break;

		default:
			break;
		}
		
		return emissor;
	}
}
