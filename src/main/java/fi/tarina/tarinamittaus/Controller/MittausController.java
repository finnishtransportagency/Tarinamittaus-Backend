package fi.tarina.tarinamittaus.Controller;

import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Service.MittausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void registerNewMittaus(@RequestBody Mittaus mittaus) {
        mittausService.addNewMittaus(mittaus);
    }

    @PostMapping(value = "/testi")
    public Mittaus saveMittausTesti(@RequestBody Mittaus mittaus) {
        return mittausService.saveMittaus(mittaus);
    }

}
