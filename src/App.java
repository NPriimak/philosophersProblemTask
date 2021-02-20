import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philospher[] philosphers = null;
        Chopstick[] chopsticks = null;


        try {
            philosphers = new Philospher[Constants.NUMBER_OF_PHILOSOPHER];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; ++i) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHER);

            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHER; ++i) {
                philosphers[i] = new Philospher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_PHILOSOPHER]);
                executorService.execute(philosphers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);


            for (Philospher philospher : philosphers){
                philospher.setFull(true);
            }
        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()){
                Thread.sleep(1000);
                }
            for (Philospher philospher: philosphers){
                System.out.println(philospher + " eat #" + philospher.getEatingCounter() + " times");
            }
        }

    }
}
