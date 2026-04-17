package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.repository.StabilizerConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StabilizerServiceTest {

    @Mock
    StabilizerConfigRepository stabilizerConfigRepository;
    //Wert der Stabilisatoren angeben
    @Test
    void setNumberOfStabilizers(){
    StabilizerService service = new StabilizerService(stabilizerConfigRepository);
    service.setNumberOfStabilizers(5);
    assertEquals(5,service.getNumberOfStabilizers());
    }
    //Maximaler Wert der Stabilisatoren
    @Test
    void maximalNumberOfStabilizers() {
        StabilizerService service = new StabilizerService(stabilizerConfigRepository);
    assertThrows(IllegalArgumentException.class,() -> {
        service.setNumberOfStabilizers(11);
    });
    }
    //Minimal Wert der Stabilisatoren
    @Test
    void minimalNumberOfStabilizers(){
        StabilizerService service = new StabilizerService(stabilizerConfigRepository);
        assertThrows(IllegalArgumentException.class,() -> {
            service.setNumberOfStabilizers(-1);
        });
    }

    /*Stabilisator im Feld setzen
    @Test
    void activateStabilizer(){
        StabilizerService service = new StabilizerService(stabilizerConfigRepository);
        //Grid newGrid = new Grid(20,20 );
        service.setNumberOfStabilizers(1);
        assertTrue(service.activateStabilizer(10,10,newGrid));

    }*/
    //Wert der verbleibenden Stabilisatoren
    @Test
    void decrementStabilizers(){
        StabilizerService service = new StabilizerService(stabilizerConfigRepository);
       // Grid newGrid = new Grid(20,20);
        service.setNumberOfStabilizers(5);
        service.decrementStabilizers();
        assertEquals(4,service.getNumberOfStabilizers());

    }

    //Stabilisator entfernen
    @Test
    void incrementStabilizers(){
        StabilizerService service = new StabilizerService(stabilizerConfigRepository);
        //Grid newGrid = new Grid(20,20);
        service.setNumberOfStabilizers(5);
        service.incrementStabilizers();
        assertEquals(6, service.getNumberOfStabilizers());
    }

    //Bei 0 kann kein Stabilisator mehr gesetzt werden
    @Test
    void activateStabilizer_nonRemaining(){
    StabilizerService service = new StabilizerService(stabilizerConfigRepository);
    service.setNumberOfStabilizers(0);
    assertThrows(IllegalArgumentException.class, () -> {
        service.decrementStabilizers();
    });
    }

    }
