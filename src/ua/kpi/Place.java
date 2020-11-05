package ua.kpi;

public class Place {
    public int markersCount;
    public int markersMin = 0;
    public int markersMax = 0;
    public double markersAvg = 0;
    public String name;


    public Place(String name, int markersCount) {
        this.name = name;
        this.markersCount = markersCount;
        if (markersMin < markersCount) {
            markersMin = markersCount;
        }
    }

    public void changeMinMaxMarkers() // cума маркерів
    {
        if (markersCount < markersMin)
            markersMin = markersCount;
        if (markersCount > markersMax)
            markersMax = markersCount;
        markersAvg += markersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return markersCount == place.markersCount &&
                markersMin == place.markersMin &&
                markersMax == place.markersMax &&
                Double.compare(place.markersAvg, markersAvg) == 0 &&
                name.equals(place.name);
    }

}

