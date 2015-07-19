package com.br.pgmobil.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Util;

@ManagedBean
@SessionScoped
public class DataBase {

	private static Map<String,TransacaoDTO> TRANS_MAP = new HashMap<String, TransacaoDTO>();
				
		public static TransacaoDTO getById(String id){
			return TRANS_MAP.get(id);
		}
		
		public static List<TransacaoDTO> getAll(){
			List<TransacaoDTO> transacoes = new ArrayList<TransacaoDTO>(TRANS_MAP.values());
			return transacoes;
		}
		
		public static void saveOrUpdate(TransacaoDTO transacao){
			if(transacao.getTid() == null || transacao.getTid().isEmpty()){
				
				TRANS_MAP.put(Util.gerarTid(), transacao); 
			}else{
				TRANS_MAP.put(transacao.getTid(), transacao); 
			}
		}		
		
}
