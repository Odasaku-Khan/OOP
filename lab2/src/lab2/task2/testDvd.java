package lab2.task2;

public class testDvd {
	public static void main(String[] args) {
		Dvd FightClub = new Dvd("Fight Club", "Alibek", 2005, 19.99, 2572.5f);
		
		System.out.println(FightClub);
		
		System.out.println(FightClub.diff());
		
	}
}
