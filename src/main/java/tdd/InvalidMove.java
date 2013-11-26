package tdd;

public class InvalidMove extends Throwable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMove() {
    }

	
	public InvalidMove(String message) {
		super(message);
	}
	
}
