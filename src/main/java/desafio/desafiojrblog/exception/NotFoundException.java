package desafio.desafiojrblog.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ServiceException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Not found!");
    }
}
