package tpv.apitvpin.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nick")
    private String nick;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellidos")
    private String apellidos;
    @JsonProperty("password")
    private String password;
    @JsonProperty("fechaNacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("poblacion")
    private String poblacion;
    @JsonProperty("provincia")
    private String provincia;
    @JsonProperty("codigoPostal")
    private String codigoPostal;
    @JsonProperty("numeroTelefono")
    private String numeroTelefono;
    @JsonProperty("email")
    private String email;
}
