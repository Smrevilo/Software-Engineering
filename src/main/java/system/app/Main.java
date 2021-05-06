package system.app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		/*Scanner in = new Scanner(System.in);
		Scanner scanner = new Scanner(in.nextLine().replace('/', ' '));
		System.out.println(scanner.nextInt());
		System.out.println(scanner.nextInt());
		System.out.println(scanner.nextInt());*/
		PKV pkv = new PKV();
		pkv.startApp();
		//GUILogin gui = new GUILogin(pkv);
	}

}
