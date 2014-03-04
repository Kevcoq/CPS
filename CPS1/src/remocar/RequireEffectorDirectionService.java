package remocar;

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireEffectorDirectionService extends RequireService {
    void bindEffectorDirectionService(EffectorDirectionService direction) throws ServiceBindException;
}
