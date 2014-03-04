package remocar;

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireEngineService extends RequireService {
    void bindEngineService(EngineService engine) throws ServiceBindException;
}
