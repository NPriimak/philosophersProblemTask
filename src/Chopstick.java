import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philospher philospher, State state) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philospher + "picked up " + state.toString() + " " + this);
            return true;
        }
        return false;
    }

    public void putDown(Philospher philospher, State state){
        lock.unlock();
        System.out.println(philospher + " puts down " + state.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}
