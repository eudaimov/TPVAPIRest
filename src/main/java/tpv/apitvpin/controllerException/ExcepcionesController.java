package tpv.apitvpin.controllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ExcepcionesController extends RuntimeException{

}
