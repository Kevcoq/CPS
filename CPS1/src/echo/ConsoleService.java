package echo;

import tamago.Service;

public interface ConsoleService extends Service {
    void print(String s);
    void println(String s);
}

