import org.junit.*;

public class LiteratureTest {
    private Literature literature;

    @Before
    public void setUp() throws  Exception{
         this.literature = new Book("Mord og mysterier", "Egmond", "03.04.18","130", "kr", "book");
    }

    @Test
    public void getTitle() {
        Assert.assertEquals(this.literature.getTitle(), "Mord og mysterier");
    }

    @Test
    public void getPublisher() {
        Assert.assertEquals(this.literature.getPublisher(), "Egmond");
    }

    @Test
    public void getPublishedDate() {
        Assert.assertEquals(this.literature.getPublishedDate(), "03.04.18");
    }

    @Test
    public void getCurrency() {
        Assert.assertEquals(this.literature.getCurrency(), "kr");
    }

    @Test
    public void getTypeOfLiterature() {
        Assert.assertEquals(this.literature.getTypeOfLiterature(), "book");
    }

    @Test
    public void getDescriptionOfLiteratureAsString() {
        Assert.assertEquals(this.literature.getDescriptionOfLiteratureAsString(), "Mord og mysterier, Egmond, 03.04.18, 130.0kr\n");
    }
}