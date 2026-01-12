package parsing;

import common.CommentSelector;
import common.Config;
import common.Directive;
import java.util.ArrayList;
import java.util.Arrays;

import holders.Selector;
import macros.RawMacro;
import macros.directives.*;

import javax.xml.stream.events.Comment;


public class Parser {

    private final State state;
    private int savepoint;

    public Parser(ArrayList<String> lines){
        this.state = new State(lines);
        this.savepoint = 0;
    }

    public void startTransAction() {
        this.savepoint = this.state.getPosition();
    }

    public void rollBack() {
        this.state.setPosition(this.savepoint);
    }

    public void commit() {
        this.savepoint = this.state.getPosition();
    }

    public ArrayList<String> getMultiLineMacroDefinition(){
        this.startTransAction();

        ArrayList<String> res = new ArrayList<>();

        String line = CommentSelector.getSingleComment(this.state.peek()).trim();
        while(line.matches(Config.Jpp.macroDefSeparatorPattern)){
            res.add(line.substring(0, line.length() - Config.Jpp.macroDefSeparator.length()));
            line = CommentSelector.getSingleComment(this.state.next()).trim();
            if(this.state.isFinished())
                break;
        }

        this.rollBack();

        return res;
    }

    public Directive parseDirective(String line) {
        String[] words = line.split("\\s+");
        if (words.length == 0)
            return new Continue(1);

        String command = words[0];
        int commandCode = command.toLowerCase().hashCode();

        if (commandCode == Define.code()) {
            String name = words[1];
            ArrayList<String> definition = new ArrayList<>();

            String header = String.join(" ", Arrays.copyOfRange(words, 2, words.length));

            if (header.matches(Config.Jpp.macroDefSeparatorPattern) && header.length() > 1) {
                header = header.substring(0, header.length() - Config.Jpp.macroDefSeparator.length());
            } else {
                header = "";    // safe cut
            }

            definition.add(header);
            this.state.skip(1);
            definition.addAll(this.getMultiLineMacroDefinition());

            RawMacro macro = new RawMacro(name, definition.toArray(new String[0]));
            return new Define(macro);

        }
        else if (commandCode == Undef.code()) {
            String name = words[1];
            return new Undef(name);
        }

        return new Continue(1);
    }

    public void process(){
        while(!this.state.isFinished()){
            String line = this.state.peek();
            String directiveText = Selector.select(line);
            Directive directive = this.parseDirective(directiveText);
            directive.process(this.state);
        }
    }

}
