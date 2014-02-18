package bridge.contracts;

public class ContractError extends Error {
	private static final long serialVersionUID = -6084661798957012198L;

	public ContractError(String message) {
		super(message);
	}
}
