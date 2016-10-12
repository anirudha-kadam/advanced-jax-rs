package org.anirudha.webservices.messanger.model;

public class ErrorMessage {
	
	
	private String errorMessage;
	private int errorCode;
	private String documenatation;
	
	public ErrorMessage(){
		
	}

	public ErrorMessage(String errorMessage, int errorCode, String documenatation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documenatation = documenatation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDocumenatation() {
		return documenatation;
	}

	public void setDocumenatation(String documenatation) {
		this.documenatation = documenatation;
	}
	
	

}
