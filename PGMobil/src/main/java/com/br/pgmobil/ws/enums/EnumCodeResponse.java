package com.br.pgmobil.ws.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumCodeResponse {

	OK("OK",200),
	CREATED("CREATED",201),
	ACCEPTED("Accepted ",202),
	PARTIAL_INFORMATION("Partial Information",203),
	NO_RESPONSE("No Response",204),
	MOVED("Moved",301),
	FOUND("Found",302),
	METHOD("Method ",303),
	NOT_MODFIED("Not Modified",304),
	BAD_REQUEST("Bad request",400),
	UNAUTHORIZED("Unauthorized",401),
	PAYMENTREQUIRED("PaymentRequired",402),
	FORBIDDEN("Forbidden",403),
	NOT_FOUND("Not found",404),
	INTERNAL_ERRO("Internal Error",500),
	NOT_IMPLEMENTED("Not implemented",501),
	SERVICE_TEMPORARILY_OVERLOADED("Service temporarily overloaded",502),
	GATWAY_TIMEOUT("Gateway timeout",503);
	
	private String key;
	private Integer code;
	
	
	private static final Map<String, EnumCodeResponse> LOOKUP = new HashMap<String, EnumCodeResponse>();
	static {
		for (EnumCodeResponse item : EnumCodeResponse.values()) {
			LOOKUP.put(item.key, item);
		}
	}
	
	private EnumCodeResponse(String key , Integer code) {
		this.key = key;
		this. code = code;
	}	
	
	public static EnumCodeResponse getEnumCodeResponseByKey(String key){
		return LOOKUP.get(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
