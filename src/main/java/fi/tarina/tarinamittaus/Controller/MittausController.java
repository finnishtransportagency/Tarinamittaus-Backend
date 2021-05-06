package fi.tarina.tarinamittaus.Controller;

import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Service.MittausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "mittaus/")
public class MittausController {

    private MittausService mittausService;

    @Autowired
    public MittausController(MittausService mittausService) {
        this.mittausService = mittausService;
    }

    @GetMapping
    public List<Mittaus> getMittausList() {
        return mittausService.getMittausList();
    }


    @PostMapping
    public ResponseEntity<Mittaus> saveMittaus(@Valid @RequestBody Mittaus mittausRequest) {
        try {
            Mittaus savedMittaus = mittausService.saveMittaus(mittausRequest);
            return new ResponseEntity<>(savedMittaus, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
