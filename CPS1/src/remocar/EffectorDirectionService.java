package remocar;
/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.Service;

public interface EffectorDirectionService extends Service {
    void deliverDirection(double angle);
}

