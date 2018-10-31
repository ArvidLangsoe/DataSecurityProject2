package printer.states;

import printer.IPrinter;
import printer.Printer;

public abstract class State  implements IPrinter {
    protected Printer printer;

    public State(Printer printer){
        this.printer=printer;
    }

}
