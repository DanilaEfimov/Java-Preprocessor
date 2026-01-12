package parsing;

import common.Directive;
import java.util.ArrayList;
import java.util.Arrays;

import holders.Selector;
import macros.RawMacro;
import macros.directives.*;


public class Parser {

    private State state;
    private Directive current;

    public Parser(ArrayList<String> lines){
        this.state = new State(lines);
    }

    static public Directive parseDirective(String line){
        String[] words = line.split("\\s+");
        if(words.length == 0)
            return new Continue(1);

        String command = words[0];
        int commandCode = command.toLowerCase().hashCode();

        if(commandCode == Define.code()){
            String name = words[1];
            String definition = String.join(
                    " ", Arrays.copyOfRange(words, 2, words.length)
            );
            RawMacro macro = new RawMacro(name, new String[]{definition});
            return new Define(macro);
        }
        else if(commandCode == Undef.code()){
            String name = words[1];
            return new Undef(name);
        }

        return new Continue(1);
    }

    public void process(){
        while(!this.state.isFinished()){
            String line = this.state.peek();
            String directiveText = Selector.select(line);
            Directive directive = Parser.parseDirective(directiveText);
            directive.process(this.state);
        }
    }

}
