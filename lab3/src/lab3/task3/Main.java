package lab3.task3;

public class Main {

	public static void main(String[] args) {
		Mycollection<String> myList=new arrayList<>();
		Mycollection<String> mySet=new Hash<>();
		myList.add("Apple");
		myList.remove("Apple");
		myList.add("Banana");
		System.out.println("Array list "+ myList);
		mySet.add("Orange");
		mySet.remove("Orange");
		mySet.add("Lemon");
		System.out.println("Hash set "+ mySet);
	}
}
