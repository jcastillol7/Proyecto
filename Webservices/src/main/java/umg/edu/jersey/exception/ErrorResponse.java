package umg.edu.jersey.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {
	
	private String errorCode;
	private String consumerMessage;
	private String applicationMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getConsumerMessage() {
		return consumerMessage;
	}

	public void setConsumerMessage(String consumerMessage) {
		this.consumerMessage = consumerMessage;
	}

	public String getApplicationMessage() {
		return applicationMessage;
	}

	public void setApplicationMessage(String applicationMessage) {
		this.applicationMessage = applicationMessage;
	}

}
