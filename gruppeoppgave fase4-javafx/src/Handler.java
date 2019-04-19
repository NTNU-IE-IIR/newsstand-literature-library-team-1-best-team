public abstract class Handler {

    private Register register;

    public Handler(){
        this.register = new Register();
    }

    protected Register getRegister() {
        return this.register;
    }
}
