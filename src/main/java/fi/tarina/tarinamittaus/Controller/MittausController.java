package fi.tarina.tarinamittaus.Controller;

import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
import fi.tarina.tarinamittaus.Service.MittausService;
import fi.tarina.tarinamittaus.Specification.MittausSearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/mittaus")
public class MittausController {

    private MittausService mittausService;

    @Autowired
    public MittausController(MittausService mittausService) {
        this.mittausService = mittausService;
    }

    @GetMapping
    public List<Mittaus> find(
            MittausSearchParameters parameters
                             ) {
        try {
            return mittausService.searchMittausListByKeyword(parameters);
        } catch (Exception e) {
            // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            throw new CustomBadRequestException();
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Mittaus> getById(@PathVariable("id") Integer id) {
        try {
            Mittaus mittaus = mittausService.getMittaus(id);
            return new ResponseEntity<>(mittaus, HttpStatus.OK);
        } catch (Exception e) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            throw new CustomNotFoundException();
        }
    }


    @PostMapping
    public ResponseEntity<Mittaus> saveMittaus(@Valid @RequestBody Mittaus mittausRequest) {
        try {
            Mittaus savedMittaus = mittausService.saveMittaus(mittausRequest);
            return new ResponseEntity<>(savedMittaus, HttpStatus.CREATED);
        } catch (Exception e) {
            // throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            throw new CustomBadRequestException();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Mittaus> deleteMittaus(@PathVariable("id") Integer id) {
        try {
            this.mittausService.deleteMittaus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            throw new CustomNotFoundException();
        }
    }

    @PutMapping
    public void updateMittaus(@RequestBody MittausDto dto) {
        try {
            mittausService.updateMittaus(dto);
        } catch (Exception e) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            throw new CustomNotFoundException();
        }
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("This works", HttpStatus.OK);
    }
}
