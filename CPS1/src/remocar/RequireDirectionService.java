package remocar;
/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireDirectionService extends RequireService {
    void bindDirectionService(DirectionService direction) throws ServiceBindException;
}

