package bridge.contracts;

public class Contractor {
	private final int abortCount;
	private int count;
	private static Contractor defaultInstance = null;
	
	public Contractor(int abortCount) {
		this.abortCount = abortCount;
		this.count = 0;
	}
	
	public static Contractor defaultContractor() {
		if(defaultInstance==null) {
			defaultInstance = new Contractor(10); // erreur aprs 10 ruptures de contrat
		}
		return defaultInstance;
	}
	
	private void checkAbort() {
		count++;
		if(count==abortCount) {
			count = 0;
			throw new ContractError("Too many contracts failed");
		}		
	}
	
	public void invariantError(String service, String message) {
		System.err.println("Service '"+service+"': invariant error");
		System.err.println("  ==> "+message);
		checkAbort();
	}

	public void preconditionError(String service, String method, String message) {
		System.err.println("Service '"+service+"' method '"+method+"': precondition error");
		System.err.println("  ==> "+message);
		checkAbort();
	}

	public void postconditionError(String service, String method, String message) {
		System.err.println("Service '"+service+"' method '"+method+"': postcondition error");
		System.err.println("  ==> "+message);
		checkAbort();
	}
}
