package desafio.desafiojrblog.logging;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Log
@Component
public class Logger {
    
    private HashMap<Object, Object> map = new HashMap<>();

    public static Logger builder() {
        return new Logger();
    }

    public void info() {
        log.info(new Gson().toJson(map));
    }

    public void servere() {
        log.severe(new Gson().toJson(map));
    }

    public void fine() {
        log.fine(new Gson().toJson(map));
    }

    public Logger add(Object key, Object value) {
        map.put(key, value);
        return this;
    }

    public Logger add(LoggerBasicKeyEnum key, Object value) {
        map.put(key, value);
        return this;
    }
}
