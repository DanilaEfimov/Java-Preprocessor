package common;

import parsing.State;


public interface Directive {

    static public int code() {
        return 0;
    }

    /**
     * Processing a source code lines.<br>
     * @param lines source code
     * @return processed code
     */
    public State process(State lines);

}
