package matrixExceptions;

public class InconsistentDimension extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InconsistentDimension() {
		// TODO Auto-generated constructor stub
		super("Matrices dimensions are inconsistent");
	}

	public InconsistentDimension(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InconsistentDimension(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InconsistentDimension(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InconsistentDimension(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
