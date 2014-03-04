package remocar;

public class Command {
    public static final int FORWARD = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 4;
    public static final int BACKWARD = 8;
    public static final int RESET = 0;

    private int ctrl;

    public Command(int ctrl) {
	this.ctrl = ctrl;
    }

    public boolean isForward() {
	return (ctrl & FORWARD)==FORWARD;
    }

    public boolean isLeft() {
	return (ctrl & LEFT)==LEFT;
    }

    public boolean isRight() {
	return (ctrl & RIGHT)==RIGHT;
    }

    public boolean isBackward() {
	return ((ctrl & BACKWARD)==BACKWARD);
    }

    public boolean isReset() {
	return ctrl == RESET;
    }

    public boolean isWrong() {
	return !isForward() && !isBackward() && !isLeft() && !isRight() && !isReset();
    }
    
    public String toString() {
	String ret = "CMD{";
	if(isForward())
	    ret += "FWD";
	if(isBackward())
	    ret += "BWD";
	if(isLeft())
	    ret += "LFT";
	if(isRight())
	    ret += "RGT";
	if(isReset())
	    ret += "RST";
	if(isWrong())
	    ret += "ERR";
	return ret+"}";
    }
	    
}
