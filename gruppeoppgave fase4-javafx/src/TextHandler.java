import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextHandler  {

    public TextHandler() {

    }

    public Text welcomeText() {
        Text text = new Text("Welcome to the store!");
        text.setFont(Font.font("Sans-serif", FontWeight.NORMAL, 30));
        return text;
    }
}
