package example.claudias_kugelweltkarte.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.claudias_kugelweltkarte.model.Cell;
import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.model.Phase;
import example.claudias_kugelweltkarte.repository.PhaseRepository;
import example.claudias_kugelweltkarte.service.MarkovService;
import example.claudias_kugelweltkarte.service.StabilizerService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller //Spring scannt die Klasse und registriert sie als WebController
@RequiredArgsConstructor
//Lombok generiert automatisch einen Konstruktor für die privaten Felder. Den benutzt Spring für die Dependency Injection.
public class MapController {
    private final MarkovService markovService;//Dependency Injection
    private final StabilizerService stabilizerService;
    private final PhaseRepository phaseRepository;

    //GET/map
    @GetMapping("/map") //Verarbeitet Html-Anfragen auf die angegebene URL
    public String showMap(Model model) { //Model ist das Spring Interface und fungiert als Container zwischen Controller und Templates.
        Grid grid = markovService.getGrid();
        model.addAttribute("grid", grid); //Erster Parameter benennt das Objekt, damit Thymeleaf es erkennt und der zweite Parameter ist das Objekt selbst.
        model.addAttribute("phase", markovService.getPhaseNumber());
        model.addAttribute("stabilizers", stabilizerService.getNumberOfStabilizers());
        model.addAttribute("isHistoricPhase", false);
        return "map";//Rückgabewert ist der Templatename.
    }

    @PostMapping("/map/next")
    public String nextPhase() {
        markovService.nextPhase();
        //model.addAttribute("grid", grid); <- model.addAttribute beim redirect unnötig.
        //Das Model wird nicht mtigenommen.
        return "redirect:/map";
    }

    @PostMapping("/map/stabilizers/decrement")
    public String decrementStabilizers() {
        stabilizerService.decrementStabilizers();
        return "redirect:/map";


    }

    @PostMapping("/map/stabilizers/increment")
    public String incrementStabilizers() {
        stabilizerService.incrementStabilizers();
        return "redirect:/map";
    }

    @PostMapping("/map/stabilize/{x}/{y}")
    public String setStabilizer(@PathVariable int x, @PathVariable int y) {
        Cell cell = markovService.getGrid().getCells()[x][y];
        if (cell.isStabilized()) {
            stabilizerService.incrementStabilizers();
        } else {
            stabilizerService.decrementStabilizers();
        }
        markovService.stabilize(x, y, markovService.getPhaseNumber());
        return "redirect:/map";
    }

    @GetMapping("/map/phase")
    public String findByPhaseNumber(Model model, @RequestParam int phaseNumber) {
        Phase phaseId = phaseRepository.findByPhaseNumber(phaseNumber);
        model.addAttribute("isHistoricPhase",true);
        model.addAttribute("phase", phaseNumber);
        if (phaseId == null) {
            model.addAttribute("error", "Karte nicht gefunden.");
            return "map";
        }
        try {
            Grid grid = phaseId.restore();
            model.addAttribute("grid", grid);
        } catch (JsonProcessingException e) {
            model.addAttribute("error", "Phase konnte nicht wiederhergestellt werden.");
        }
        return "map";
    }

}
