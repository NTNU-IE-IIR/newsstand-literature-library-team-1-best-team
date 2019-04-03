import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterTest {
    private Register register;

    @Before
    public void setUp() throws Exception {
        this.register = new Register();
    }

    @Test
    public void addLiterature() {
        Literature literature = new Book("Mord og mysterier", "Egmond", "03.04.18",130, "kr", "book");
        boolean result = register.addLiterature(literature);
        Assert.assertEquals(result, true);
    }

    @Test
    public void findLiteratureByTitle() {
        Newspaper n1 = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr", "Newspaper");
        this.register.addLiterature(n1);
        Assert.assertNotEquals(null, this.register.findLiteratureByTitle("VG"));
    }

    @Test
    public void findLiterautreByType() {
        Literature l1 = new Book("Halla", "pdfjdfgpo", "29.10.20", 30, "kr", "Book");
        this.register.addLiterature(l1);
        Assert.assertNotEquals(null, this.register.findLiterautreByType("Book"));
    }

    @Test
    public void getLiteratureByTypeAsString() {
        Assert.assertEquals(this.register.getLiteratureByTypeAsString("dfoigdfoi"), "");
        Assert.assertNotEquals("", this.register.getLiteratureByTypeAsString("Book"));
    }
}