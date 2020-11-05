package ua.kpi;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public List<Place> places;
    public List<Transition> transitions;
    public List<Transition> nextTransitions = new ArrayList<>();

    public Model(List<Place> places, List<Transition> transitions) {
        this.places = places;
        this.transitions = transitions;
    }

    public void simulate(int iterations) {
        int iterator = 0;

        while (iterator < iterations) {

            places.forEach(Place::changeMinMaxMarkers);

            transitions.stream().filter(t -> t.setTransitionPossibility(places)).forEach(t -> nextTransitions.add(t));//  если переход можна здійснити - то додаєм в  список можливих переходов

            nextTransitions.forEach(t -> t.probability = 1.0 / nextTransitions.size());

            double r = Math.random();

            for (int i = 0; i < nextTransitions.size(); i++) {
                if (r < nextTransitions.get(i).probability) {
                    places = nextTransitions.get(i).performTransition(places);
                    break;
                } else
                    r -= nextTransitions.get(i).probability;
            }
            nextTransitions.clear();
            iterator++;
        }
        statistics(iterations);
    }

    public void statistics(int iterations) {
        System.out.println();
        System.out.println("STATISTICS\n_________________________________________________");
        System.out.printf("%-26s|%-4s|%-4s|%-10s\n", "name", "min", "max", "average");
        System.out.println("______________________________________________");
        for (Place p : places) {
            p.markersAvg /= iterations;
            System.out.printf("%-26s|%-4d|%-4d|%-10.3f\n", p.name, p.markersMin, p.markersMax, p.markersAvg);
        }
    }
}

