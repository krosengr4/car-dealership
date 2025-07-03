import Logic.Logic;
import Utilities.Utils;

public class Main {

    public static void main(String[] args) {
		Utils.designLine(85, true, "_");
		Utils.designLine(34, false, Utils.cowboy);
        System.out.println(Utils.cowboy + "\t\t\t\t\t\t" + Utils.car + " WELCOME TO CODEO CARS " + Utils.car + "\t\t\t\t\t\t\t   " + Utils.cowboy);
		Utils.designLine(34, true, Utils.cowboy);

		Logic.processMainMenu();

		System.out.println("\n\nThank you for using Codeo Cars!" + Utils.smileyFace + Utils.car + "\n");
		Utils.designLine(33, false, Utils.cowboy);
		System.out.println(Utils.cowboy + "\t\t\t\t\t\t" + " Y'ALL COME BACK NOW, YA HERE " + "\t\t\t\t\t\t   " + Utils.cowboy);
		Utils.designLine(33, false, Utils.cowboy);

    }

}
