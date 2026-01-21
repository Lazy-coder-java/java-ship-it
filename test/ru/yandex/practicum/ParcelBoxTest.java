package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

public class ParcelBoxTest {

    @Test
    public void addParcelWithinWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("A", 5, "Addr", 1));
        Assertions.assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void addParcelExceedWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("A", 11, "Addr", 1));
        Assertions.assertEquals(0, box.getAllParcels().size());
    }

    @Test
    public void addParcelBoundaryWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("A", 10, "Addr", 1));
        Assertions.assertEquals(1, box.getAllParcels().size());
    }
}
