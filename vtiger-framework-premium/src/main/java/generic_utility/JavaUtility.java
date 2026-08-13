package generic_utility;

public class JavaUtility {
	public static int generateRandomNumber() {
		double n1 = Math.random();
		double n2 = n1 * 1000;
		int random = (int) n2;

//		int random = ((int) Math.random() * 999);

		return random;
	}

}
