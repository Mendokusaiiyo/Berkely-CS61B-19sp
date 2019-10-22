import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> BST1 = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> y1Values = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        for (int i = 0; i < 5000; i += 1) {
            BST1.add(r.nextInt(1000000000));
        }
        xValues.add(BST1.size());
        y1Values.add(BST1.averageDepth());
        y2Values.add(ExperimentHelper.optimalAverageDepth(5000));
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Numbers of Items").yAxisTitle("Average Depth").build();
        chart.addSeries("Average Depth of a random BST", xValues, y1Values);
        chart.addSeries("Average Depth of the optimal BST", xValues, y2Values);
        new SwingWrapper(chart).displayChart();
    }


    public static void experiment2() {
        BST<Integer> BST2 = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        // N = 5000
        for (int i = 0; i < 5000; i += 1) {
            BST2.add(r.nextInt(Integer.MAX_VALUE));
        }
        // M = 500
        for (int i = 0; i < 500; i += 1) {
            xValues.add(i);
            yValues.add(BST2.averageDepth());
            BST2.deleteTakingSuccessor(BST2.getRandomKey());
            BST2.add(r.nextInt(Integer.MAX_VALUE));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Numbers of Operations").yAxisTitle("Average Depth").build();
        chart.addSeries("Average depth after deleting and inserting items randomly", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> BST2 = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        // N = 5000
        for (int i = 0; i < 5000; i += 1) {
            BST2.add(r.nextInt(Integer.MAX_VALUE));
        }
        // M = 500
        for (int i = 0; i < 500; i += 1) {
            xValues.add(i);
            yValues.add(BST2.averageDepth());
            BST2.deleteTakingRandom(BST2.getRandomKey());
            BST2.add(r.nextInt(Integer.MAX_VALUE));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Numbers of Operations").yAxisTitle("Average Depth").build();
        chart.addSeries("Average depth after deleting and inserting items randomly", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
