package observers.displays;

import subjects.WeatherData;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Roman Horilyi on 31.08.2016.
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    Observable observable;
    private float maxTemp = 200;
    private float minTemp = 0.0f;
    private float tempSum;
    private int numReadings;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) observable;
            float temperature = weatherData.getTemperature();
            tempSum += temperature;
            numReadings++;

            if (temperature > maxTemp) {
                maxTemp = temperature;
            }

            if (temperature < minTemp) {
                minTemp = temperature;
            }

            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature: " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}
