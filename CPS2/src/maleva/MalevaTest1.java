package maleva;

public class MalevaTest1 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			Agent me1 = new Mechant("darcula");
			Agent me2 = new Mechant("darkvador");
			Agent ge1 = new Mechant("Maya l'abeille");
			
			Environnement env = new Environnement(640,400);
			
			me1.initialize();
			me2.initialize();
			ge1.initialize();
			
			me1.configure(env,30,30);
			me2.configure(env,500,370);
			ge1.configure(env,320,200);
						
			for(int i =0;i<100;i++) {
				me1.step();
				me2.step();
				ge1.step();
			}
			
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
}
