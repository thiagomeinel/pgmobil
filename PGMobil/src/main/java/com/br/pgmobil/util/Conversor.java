package com.br.pgmobil.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Conversor<T> {
	
	Gson gson = new Gson();

	public T fromJson(String mensagem){
		Gson gson = new Gson();
		Type transacaoType = new TypeToken<T>() {}.getType();
		T entidade  = gson.fromJson(mensagem, transacaoType);
		return entidade; 
	}
	
	
	public List<T> fromJson(List<T> lista, String mensagem){
		Gson gson = new Gson();
		Type transacaoType = new TypeToken<T>() {}.getType();
		lista = gson.fromJson(mensagem, transacaoType);
		return lista;
	}
}
