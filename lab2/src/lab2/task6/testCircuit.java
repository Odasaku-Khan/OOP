package lab2.task6;

public class testCircuit {
	public static void main(String[ ] args) {
        Circuit a = new Resistors(3.0);
        Circuit b = new Resistors(3.0);
        Circuit c = new Resistors(6.0);
        Circuit d = new Resistors(3.0);
        Circuit e = new Resistors(2.0);
        
        Circuit f = new Series(a, b);   
        Circuit g = new Parallel(c, d); 
        Circuit h = new Series(g, e);   
        Circuit circuit = new Parallel(h, f); 
        
        

        double R = circuit.getResistance();
        System.out.println("Resistance is: " + R);

        circuit.applyPotentialDiff(5.63); 
        
  
        
        System.out.println("Power: " + circuit.getPower());
        System.out.println("Current: " + circuit.getCurrent());
	}
}
