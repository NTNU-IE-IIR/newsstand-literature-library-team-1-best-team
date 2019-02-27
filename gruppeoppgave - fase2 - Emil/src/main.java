public class main {
    public static void main(String[] args) {
        Register newspaperList = new Register();
        Newspaper VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr");
        Newspaper VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", 40, "kr");
        Newspaper Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", 60, "kr");
        Newspaper NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", 100, "kr");
        newspaperList.addNewspaper(VG);
        newspaperList.addNewspaper(VG2);
        newspaperList.addNewspaper(Aftenposten);
        newspaperList.addNewspaper(NRK);
        //newspaperList.printNewspaperList();
        Newspaper ns = newspaperList.findNewspaperByTitle("VG");
        System.out.println(ns.getTitle());
    }
}
