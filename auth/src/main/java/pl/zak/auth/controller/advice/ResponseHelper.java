package pl.zak.auth.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.zak.auth.dto.InfoDTO;

public class ResponseHelper {
    public static ResponseEntity<InfoDTO> response400(String e){
        return new ResponseEntity<>(new InfoDTO(e), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<InfoDTO> response200(String e) {
        return new ResponseEntity<>(new InfoDTO(e), HttpStatus.OK);
    }
}