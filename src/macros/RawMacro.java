package macros;

import common.Config;
import common.StringLiteralSelector;
import common.SymbolSelector;

public class RawMacro extends Macro {

    public RawMacro(String name, String[] definition) {
        super(name, definition);
    }

    @Override
    public int find(String line) {
        return SymbolSelector.getIdentifierPosition(line, this.name);
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
