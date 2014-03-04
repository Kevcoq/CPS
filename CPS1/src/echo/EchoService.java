package echo;

import tamago.Service;

public interface EchoService extends Service {
    void echo(String message);
}

