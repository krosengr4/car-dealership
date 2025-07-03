import Logic.Logic;
import Utilities.Utils;

public class Main {

    public static void main(String[] args) {
		Utils.designLine(85, true, "_");
		Utils.designLine(35, false, Utils.cowboy);
        System.out.println(Utils.cowboy + "\t\t\t\t\t\t" + Utils.cowboy + "WELCOME TO CODEO CARS" + Utils.cowboy + "\t\t\t\t\t\t\t   " + Utils.cowboy);
		Utils.designLine(35, true, Utils.cowboy);

		Logic.processMainMenu();

		System.out.println("\n\nThank you for using Codeo Cars!" + Utils.smileyFace + "\n");
		Utils.designLine(35, false, Utils.cowboy);
		System.out.println(Utils.cowboy + "\t\t\t\t\t\t" + "Y'ALL COME BACK NOW, YA HERE" + "\t\t\t\t\t\t   " + Utils.cowboy);
		Utils.designLine(35, false, Utils.cowboy);

    }

}
