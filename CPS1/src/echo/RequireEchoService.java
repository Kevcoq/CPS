package echo;

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireEchoService extends RequireService {
    public void bindEchoService(EchoService echo) throws ServiceBindException;
}

