package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

    private final int maxWeight;
    private final List<T> parcels = new ArrayList<>();
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.weight > maxWeight) {
            System.out.println("Внимание! Вес коробки превышен. Посылка не добавлена.");
            return;
        }
        parcels.add(parcel);
        currentWeight += parcel.weight;
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}
