public class Command {
    private String commandWord;
    private String SecondWord;

    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.SecondWord = secondWord;
    }
    public String getCommandWord()
    {
        return commandWord;
    }
    public String getSecondWord()
    {
        return SecondWord;
    }
    public boolean isUnknown()
    {
        return (commandWord == null);
    }
    public boolean hasSecondWord()
    {
        return (SecondWord != null);
    }
}
