package contracts.base;

public class PostconditionError extends ContractError {
	private static final long serialVersionUID = 9050050491786738283L;

	public PostconditionError(String message) {
		super("Postcondition failed: "+message);
	}
}
