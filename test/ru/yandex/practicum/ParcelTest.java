package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

public class ParcelTest {

    @Test
    public void standardCostCorrect() {
        StandardParcel p = new StandardParcel("Test", 5, "Addr", 1);
        Assertions.assertEquals(10, p.calculateDeliveryCost());
    }

    @Test
    public void fragileCostCorrect() {
        FragileParcel p = new FragileParcel("Test", 5, "Addr", 1);
        Assertions.assertEquals(20, p.calculateDeliveryCost());
    }

    @Test
    public void perishableCostCorrect() {
        PerishableParcel p = new PerishableParcel("Test", 5, "Addr", 1, 3);
        Assertions.assertEquals(15, p.calculateDeliveryCost());
    }

    @Test
    public void zeroWeightCostIsCorrect() {
        StandardParcel p = new StandardParcel("Empty", 0, "Addr", 1);
        Assertions.assertEquals(0, p.calculateDeliveryCost());
    }
}
