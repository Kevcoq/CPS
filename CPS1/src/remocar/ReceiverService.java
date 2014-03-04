package remocar;
import tamago.Service;

public interface ReceiverService extends Service {
    void receive(Command command);
}

