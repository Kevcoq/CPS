package remocar;

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireEffectorEngineService extends RequireService {
    void bindEffectorEngineService(EffectorEngineService direction) throws ServiceBindException;
}
