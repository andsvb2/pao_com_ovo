package run;

import view.TelaMenu;

public class Main {
    public static void main(String[] args) {
        PopulateProducts.main(new String[]{});
        PopulateEmployees.main(new String[]{});
        new TelaMenu();
    }
}