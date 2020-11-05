package ua.kpi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transition {
    public List<Arc> arcsIn = new ArrayList<>();
    public List<Arc> arcsOut = new ArrayList<>();
    public String name = "";
    public double probability = 0; //ймовірність переходу

    public Transition(String name) {
        this.name = name;
    }

    public boolean setTransitionPossibility(List<Place> places) {
        boolean f = true;

        List<Place> connectedPlaces = places.stream() // всі дуги які входять в перехід
                .filter(x ->
                        arcsIn.stream().map(r -> r.placeFrom)
                                .collect(Collectors.toList())
                                .contains(x))
                .collect(Collectors.toList());

        for (int i = 0; i < connectedPlaces.size(); i++) {
            if (connectedPlaces.get(i).markersCount < arcsIn.get(i).n) { // якщо к-ть маркерів менше чим кратність дуги то переход неможливий
                f = false;
                break;
            }
        }
        return f;
    }

    public List<Place> performTransition(List<Place> places) {

        // при запуску переходу
        // від вхідних позицій віднімаєм маркери
        for (Arc a : arcsIn) {
            places.stream().filter(x -> x.name.equals(a.placeFrom.name)).collect(Collectors.toList()).get(0).markersCount -= a.n;
        }

        // у вихідні додаєм
        for (Arc a : arcsOut) {
            places.stream().filter(x -> x.name.equals(a.placeTo.name)).collect(Collectors.toList()).get(0).markersCount += a.n;
        }
        return places;
    }
}

