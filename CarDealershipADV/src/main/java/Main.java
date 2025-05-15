import com.pluralsight.utils.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\t\t___________" + Symbols.cowboy + "WELCOME_TO_CODEO_CARS" + Symbols.cowboy + "___________");
        Design.designLine(70, true);

        UserInterface userInterface = new UserInterface();
        userInterface.display();

        System.out.println("\n\nThank you for using Codeo Cars!\nSee you next time!" + Symbols.smileyFace);

    }

}
