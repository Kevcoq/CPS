package maleva;

public class MalevaTest2 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			int coeff = 3;
			
			Agent me1 = new Mechant("darcula");
			Agent me2 = new Mechant("darkvador");
			Agent ge1 = new Trouillard("Maya l'abeille");
			
			
			Environnement env = new Environnement(640/coeff,400/coeff);
			
			me1.initialize();
			me2.initialize();
			ge1.initialize();
			
			me1.configure(env,30/coeff,30/coeff);
			me2.configure(env,500/coeff,370/coeff);
			ge1.configure(env,320/coeff,200/coeff);
						
			for(int i =0;i<1000;i++) {
				me1.step();
				me2.step();
				ge1.step();
			}
			
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
}
