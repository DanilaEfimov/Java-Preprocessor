package macros;

import common.Config;
import common.SymbolSelector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgMacro extends Macro {

    /**
     * macro parameters count have to be greater or equal 1
     */
    private final RawMacro[] parameters;

    public ArgMacro(String name, String[] definition, String[] argNames) {
        super(name, definition);
        this.parameters = new RawMacro[argNames.length];
        for(int i = 0; i < argNames.length; i++) {
            this.parameters[i] = new RawMacro(
                    argNames[i], new String[0]
            );
        }
    }

    private String[] parseArgs(String line, int namePos){
        String substring = line.substring(namePos);

        Pattern pattern = Pattern.compile(Pattern.quote(this.name) + "\\s*\\((.*?)\\)");
        Matcher matcher = pattern.matcher(substring);

        if (matcher.find()) {
            String argsString = matcher.group(1);
            return argsString.split("\\s*,\\s*");
        }

        return new String[0];
    }

    @Override
    public int find(String line) {
        int offset = 0;
        int idx = SymbolSelector.getIdentifierPosition(line, this.name);

        while (idx != Config.npos) {
            String[] args = this.parseArgs(line, idx);

            if(args.length != this.parameters.length)
                offset = idx + this.name.length();
            else
                return offset;

            idx = SymbolSelector.getIdentifierPosition(line.substring(offset), this.name);
        }

        return Config.npos;
    }

    public String getExpansion(String[] args) {
        if(args.length != this.parameters.length)
            throw new IllegalArgumentException("ArgMacro.getExpansion: missmatch argument count");

        for(int i = 0; i < args.length; i++) {
            this.parameters[i].setDefinition(new String[]{args[i]});
        }

        String res = String.join(Config.General.lineSeparator, this.definition);
        for(RawMacro macro : this.parameters){
            macro.solution(res);
        }

        return res;
    }

    @Override
    public String[] solution(String line) {
        String res = line;

        String expansion;
        int idx = this.find(res);
        while (idx != Config.npos) {
            String[] args = this.parseArgs(res, idx);
            expansion = this.getExpansion(args);
            res = res.substring(0, idx)
                    + this.getExpansion(args)
                    + res.substring(idx + this.name.length());
            idx += expansion.length();              // recursion guard
            idx = this.find(res.substring(idx));    // solving remaining
        }

        return res.split(Config.General.lineSeparator);
    }
}
