package parsing;

import common.Config;
import common.Directive;

public class Continue implements Directive {

    public final int count;

    public Continue(int count) {
        this.count = count;
    }

    @Override
    public State process(State lines) {
        lines.skip(this.count);
        return lines;
    }

}
