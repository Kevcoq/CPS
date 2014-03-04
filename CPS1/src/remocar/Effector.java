package remocar;
/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.BasicComponent;

public class Effector 
    extends BasicComponent
    implements EffectorDirectionService,
               EffectorEngineService {

    // Properties
    private double power_distance_ratio = 1/100.0*10; 
    private double last_angle;

    // Instantiation
    public Effector() {
    }
    

    public void deliverPower(double power) {
	double dist = power * power_distance_ratio;
	double dx = dist * Math.cos(last_angle) - dist * Math.sin(last_angle) ;
	double dy = dist * Math.sin(last_angle) + dist * Math.cos(last_angle) ;
	goForward(dx,dy);
    }

    public void deliverDirection(double angle) {
	last_angle = angle;
    }

    protected void goForward(double dx, double dy) {
	System.out.println("[EFFECTOR] : Moved X="+dx+" cm , Y="+dy+" cm, DIST="+Math.sqrt(dx*dx+dy*dy)+" cm"); 
    }

    // Propri�t�s

    public double getPowerDistanceRatio() {
	return power_distance_ratio;
    }

    public void setPowerDistanceRatio(double power_distance_ratio) {
	this.power_distance_ratio = power_distance_ratio;
    }

}

	       
