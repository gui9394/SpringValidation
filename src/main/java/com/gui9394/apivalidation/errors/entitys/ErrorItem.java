package com.gui9394.apivalidation.errors.entitys;

public class ErrorItem {

	private String titulo;

	private String mensagem;

	private int status;

	public ErrorItem(String titulo, String mensagem, int status) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
