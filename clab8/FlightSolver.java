import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    // PriorityQueue<Integer> flightsHeap;
    // private int passengersCount;
    int maxPassengersCount;

    /** This is not good
    public FlightSolver(ArrayList<Flight> flights) {
        flightsHeap = new PriorityQueue<>();
        ArrayList<Flight> copyOfFlights = new ArrayList<>();
        for (Flight flight : flights) {
            copyOfFlights.add(flight);
        }
        for (Flight f : flights) {
            passengersCount = f.passengers();
            copyOfFlights.remove(f);
            for (Flight cf : copyOfFlights) {
                if (cf.startTime() <= f.endTime()) {
                    passengersCount += cf.passengers();
                }

            }
            flightsHeap.add(-(passengersCount));
        }
    } */


    public FlightSolver(ArrayList<Flight> flights) {
        PriorityQueue<Flight> startTimePQ = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.startTime(), o2.startTime());
            }
        });
        PriorityQueue<Flight> endTimePQ = new PriorityQueue<>((Flight o1, Flight o2) ->
                o1.endTime() - o2.endTime());
        startTimePQ.addAll(flights);
        endTimePQ.addAll(flights);
        int passengersCount = 0;

        while (startTimePQ.size() != 0) {
            if (startTimePQ.peek().startTime() <= endTimePQ.peek().endTime()) {
                passengersCount += startTimePQ.poll().passengers();
                if (passengersCount > maxPassengersCount) {
                    maxPassengersCount = passengersCount;
                }
            }
            else {
                passengersCount -= endTimePQ.poll().passengers();
            }
        }
    }

    public int solve() {
        return maxPassengersCount;
    }

}
