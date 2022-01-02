package desafio.desafiojrblog.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends ServiceException {
    public SystemException(String message) {
        super(message);
    }

    public SystemException() {
        super("Server error!");
    }
}
