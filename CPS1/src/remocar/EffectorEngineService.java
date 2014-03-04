package remocar;
/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.Service;

public interface EffectorEngineService extends Service {
    void deliverPower(double power);
}

