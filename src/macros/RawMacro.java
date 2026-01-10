package macros;

import common.Config;
import common.StringLiteralSelector;

public class RawMacro extends Macro {

    public RawMacro(String name, String[] definition) {
        super(name, definition);
    }

    public int find(String line) {
        int from = 0;

        while (true) {
            int idx = StringLiteralSelector.getFreePosition(
                    line.substring(from), this.name
            );

            if (idx == Config.npos)
                return Config.npos;

            int abs = from + idx;
            int end = abs + name.length();

            boolean leftOk =
                    abs == 0 ||
                    !Character.isJavaIdentifierPart(line.charAt(abs - 1));

            boolean rightOk =
                    end >= line.length() ||
                    !Character.isJavaIdentifierPart(line.charAt(end));

            if (leftOk && rightOk)
                return abs;

            from = end;
        }
    }

    @Override
    public String[] solution(String line) {
        String res = line;

        String expansion = String.join(Config.General.lineSeparator, this.definition);
        int idx = this.find(res);
        while (idx != Config.npos) {
            res = res.substring(0, idx)
                    + expansion
                    + res.substring(idx + this.name.length());
            idx += expansion.length();              // recursion guard
            idx = this.find(res.substring(idx));    // solving remaining
        }

        return res.split(Config.General.lineSeparator);
    }

}
