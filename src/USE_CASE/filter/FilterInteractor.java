package USE_CASE.filter;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.FilterEventsDAO;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;

public class FilterInteractor implements FilterInputBoundary {
    final FilterOutputBoundary filterPresenter;

    public FilterInteractor(FilterOutputBoundary filterOutputBoundary) {
        this.filterPresenter = filterOutputBoundary;
    }

    public void execute(FilterInputData filterInputData) {
        FilterEventsDAO databaseDAO = new DatabaseDAO();
        ArrayList<Double> range = getRange(filterInputData.getGeoPosition());
        // Get range

        List<Event> foundEvents = databaseDAO.FilterEvents(
                filterInputData.getTypeInput(),
                Double.toString(range.get(0)),
                Double.toString(range.get(1)),
                Double.toString(range.get(2)),
                Double.toString(range.get(3)));

        if{foundEvents.isEmpty();}{
            this.filterPresenter.prepareFailView("No events match your filters");
        }
        FilterOutputData filterOutputData = new FilterOutputData(foundEvents);
        this.filterPresenter.prepareSuccessView(filterOutputData);

    }

    private ArrayList<Double> getRange(GeoPosition geoPosition){
        double latitude = geoPosition.getLatitude();
        double longitude = geoPosition.getLongitude();

        double delta = 5./111;

        double maxLatitude = latitude + delta;
        double minLatitude = latitude - delta;
        double maxLongitude = longitude + delta;
        double minLongitude = longitude - delta;

        ArrayList<Double> range = new ArrayList<Double>();
        range.add(minLatitude);
        range.add(maxLatitude);
        range.add(minLongitude);
        range.add(maxLongitude);

        return range;
    }
}
