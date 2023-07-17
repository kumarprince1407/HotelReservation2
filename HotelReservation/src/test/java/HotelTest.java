import org.junit.Test;
import com.bridgelabz.hotel_reservation.HotelReservation;
import org.junit.jupiter.api.Assertions;

public class HotelTest {

    @Test
    public void findCheapest(){
        Assertions.assertEquals("Bridgewood",HotelReservation.findCheapestHotel());
    }

}
