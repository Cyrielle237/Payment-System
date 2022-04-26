package paymentSystem.entity.exceptions;

public class BackendException extends RuntimeException {

	public BackendException(){
		super();
	}

	public BackendException(String message){
		super(message);
	}

	public BackendException(String message, Throwable e){
		super();
	}

}
