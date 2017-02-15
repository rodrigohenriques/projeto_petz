package br.com.projeto.pets.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Resposta {

	/**
	 * variavel de resposta do serviço para consulta
	 */
	private boolean success;
	/**
	 * conteiner padrão de resposta do serviço
	 */
	private String messageInfo;
	/**
	 * conteiner padrão de resposta do json do serviço
	 */
	private JsonElement conteudo;


	public Resposta() {
		super();
	}


	public Resposta(boolean success, String messageInfo) {
		super();
		this.success = success;
		this.messageInfo = messageInfo;
	}


	public Resposta(boolean success, String messageInfo, JsonArray conteudo) {
		this.success = success;
		this.messageInfo = messageInfo;
		this.conteudo = conteudo;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessageInfo() {
		return messageInfo;
	}


	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}


	public JsonElement getConteudo() {
		return conteudo;
	}


	public void setConteudo(JsonElement conteudo) {
		this.conteudo = conteudo;
	}


	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Resposta{");
		sb.append(", success=").append(success);
		sb.append(", messageInfo='").append(messageInfo).append('\'');
		sb.append(", conteudo=").append(conteudo);
		sb.append('}');
		return sb.toString();
	}
}
