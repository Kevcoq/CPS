package contracts.base;

public class ContractError extends Error {
	private static final long serialVersionUID = -453187753454311764L;

	public ContractError(String message) {
		super(message);
	}
}
