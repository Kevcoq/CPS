package echo;

import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireConsoleService extends RequireService {
    public void bindConsoleService(ConsoleService console) throws ServiceBindException;
}

