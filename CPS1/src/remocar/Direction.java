package remocar;
/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.BasicComponent;
import tamago.ServiceBindException;

public class Direction extends BasicComponent
    implements DirectionService, 
	       RequireEffectorDirectionService {
    
    // Required services
    private EffectorDirectionService effector;

    // attributes
    private double angle;

    // properties
    private double angle_step = Math.PI / 8.0;

    // Interface fonctionnelle
    public Direction() {
        effector = null;
	angle = 0.0;
    }
    
    public void goLeft() {
	angle-=angle_step;
	if(angle<0.0)
	    angle = 2.0 * Math.PI + angle;
	System.err.println("[DIRECTION] : Going Left ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    public void goRight() {
	angle+=angle_step;
	if(angle>2.0 * Math.PI)
	    angle -= 2.0 * Math.PI;
	System.err.println("[DIRECTION] : Going Right ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    // Laison des fournisseurs
    public void bindEffectorDirectionService(EffectorDirectionService effector) throws ServiceBindException {
	this.effector = effector;
    }
    

    // Propriétés
    public void setAngleStep(double angle_step) {
	this.angle_step = angle_step;
    }

    public double getAngleStep() {
	return angle_step;
    }
}
