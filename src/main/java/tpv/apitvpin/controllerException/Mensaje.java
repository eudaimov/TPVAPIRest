package tpv.apitvpin.controllerException;

import lombok.Data;

@Data
public class Mensaje {
    private long timestamp;
    private String error;
    private int status;
    private String exception;
    private String message;
}
