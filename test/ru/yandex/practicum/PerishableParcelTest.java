package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

public class PerishableParcelTest {

    @Test
    public void notExpiredBeforeDeadline() {
        PerishableParcel p = new PerishableParcel("Milk", 2, "Addr", 5, 3);
        Assertions.assertFalse(p.isExpired(7));
    }

    @Test
    public void deliveryOnLastAllowedDay() {
        PerishableParcel p = new PerishableParcel("Milk", 2, "Addr", 5, 3);
        Assertions.assertFalse(p.isExpired(8));
    }

    @Test
    public void expiredAfterDeadline() {
        PerishableParcel p = new PerishableParcel("Milk", 2, "Addr", 5, 3);
        Assertions.assertTrue(p.isExpired(9));
    }
}
