package macros;

public class RawMacro extends Macro {

    RawMacro(String name, String[] definition) {
        super(name, definition);
    }

    @Override
    public String[] solution(String line) {
        return new String[0];
    }
}
