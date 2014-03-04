
package maleva;

public class AgentRef {

    /* fields */
    private String id;
    private int posx;
    private int posy;
    private int dist;
    private int angle;

    public AgentRef(String id, int posx, int posy, int angle, int dist) {
	this.id = id;
	this.posx = posx;
	this.posy = posy;
	this.angle = angle;
	this.dist = dist;
    }

    public String getID() {
	return id;
    }

    public int getPosX() {
	return posx;
    }

    public int getPosY() {
	return posy;
    }

    public int getAngle() {
	return angle;
    }

    public int getDist() {
	return dist;
    }
}
