package remocar;
import tamago.RequireService;
import tamago.ServiceBindException;

public interface RequireReceiverService extends RequireService {
    void bindReceiverService(ReceiverService receiver) throws ServiceBindException;
}

