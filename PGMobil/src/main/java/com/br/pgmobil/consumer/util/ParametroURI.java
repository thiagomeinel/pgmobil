package com.br.pgmobil.consumer.util;

import java.util.Map;
import java.util.Set;



public class ParametroURI {
	
	
	public static void insertParameterToUrl(Map<String,String> parametro2, String target) {
		
		if(parametro2!=null && !parametro2.isEmpty()){
			
			Set<String> keys = parametro2.keySet();
			
			for (String chave : keys) {
				
				if(target.contains(chave)){
					target = target.replace(chave,parametro2.get(chave)); 
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		//insertParameterToUrl(U, target);
	}
}

