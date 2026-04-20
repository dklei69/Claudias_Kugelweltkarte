package example.claudias_kugelweltkarte.controller;

import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.model.Phase;
import example.claudias_kugelweltkarte.repository.PhaseRepository;
import example.claudias_kugelweltkarte.service.MarkovService;
import example.claudias_kugelweltkarte.service.StabilizerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;


@WebMvcTest(MapController.class)
class MapControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MarkovService markovService;
    @MockitoBean
    private StabilizerService stabilizerService;
    @MockitoBean
    PhaseRepository phaseRepository;


    @Test
    void getHtmlMap() throws Exception{
        Grid grid = new Grid();
        when(markovService.getGrid()).thenReturn(grid);
        mockMvc.perform(get("/map"))
                .andExpect(status().isOk())
                .andExpect(view().name("map"))
                .andExpect(model().attributeExists("grid"));

    }
    @Test
    void postNewMapToMap() throws Exception{
        mockMvc.perform(post("/map/next"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/map"));
        verify(markovService).nextPhase();

    }

    @Test
    void postDecrementedStabilizerToMap() throws Exception{

        mockMvc.perform(post("/map/stabilizers/decrement"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/map"));
        verify(stabilizerService).decrementStabilizers();
    }

    @Test
    void postIncrementedStabilizerToMap() throws Exception{
        mockMvc.perform(post("/map/stabilizers/increment"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/map"));
        verify(stabilizerService).incrementStabilizers();
    }
    @Test
    void postSetStabilizerToMap() throws Exception{
        mockMvc.perform(post("/map/stabilize/5/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/map"));
        verify(markovService).stabilize(5,10,0);
    }
    @Test
    void getPhaseNumber() throws Exception{
        Grid grid = new Grid();
        Phase phase = new Phase();
        phase.setGridState(phase.serialized(grid));
        when(phaseRepository.findByPhaseNumber(1)).thenReturn(phase);
        mockMvc.perform(get("/map/phase").param("phaseNumber","1"))
                .andExpect(status().isOk())
                .andExpect(view().name("map"))
                .andExpect(model().attributeExists("grid"))
                .andExpect(model().attributeExists("phase"));

    }
}

