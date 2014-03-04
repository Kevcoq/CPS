
package maleva;

import tamago.*;

public class Action {
	
	public static final int NONE = 0;
	public static final int MOVE = 1;
	
	private int type;
	private int angle;
	
	public Action(int type, int angle) {
		this.type = type;
		this.angle = angle;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean isNone() {
		return type==NONE;
	}
	
	public boolean isMove() {
		return type==MOVE;
	}
	
	public String toString() {
		String str = "Action(type=";
		switch(type) {
		case NONE:
			str+="None";
			break;
		case MOVE:
			str+="Move";
			break;
			default:
				str+="Unknown";
		}
		if(type==MOVE)
			str+=",angle="+angle;
		return str+")";		
	}
}