package lift.services;

public enum LiftStatus {
	IDLE,         // l'ascenseur est en attente
	MOVING_UP,    // l'ascenseur se déplace vers le haut
	MOVING_DOWN,  // l'ascenseur se déplace vers le bas
	STANDBY_UP,   // l'ascenseur est prêt pour la montée
	STANDBY_DOWN, // l'ascenseur est prêt pour la descente
	STOP_UP,      // l'ascenseur a stoppé en montée
	STOP_DOWN,    // l'ascenseur a stoppé en descente
}
