package parsing;

import java.util.ArrayList;


public class State {

    private int position;
    private ArrayList<String> lines;
    private boolean finished;

    State(ArrayList<String> lines) {
        this.lines = lines;
        this.position = 0;
        this.finished = this.lines.isEmpty();
    }

    public void insert(ArrayList<String> arr){
        this.lines.addAll(this.position, arr);
    }

    public void skip(int step) {
        if(this.position + step >= this.getCount())
            this.finished = true;

        this.position = Math.min(this.position + step, this.getCount() - 1);
    }

    public boolean isFinished() {
        return this.finished;
    }

    public String peek() {
        return this.lines.get(this.position);
    }

    public String next() {
        this.skip(1);
        return this.peek();
    }

    public int getPosition() {
        return this.position;
    }

    public int getCount() {
        return this.lines.size();
    }

    public ArrayList<String> getLines() {
        return this.lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
