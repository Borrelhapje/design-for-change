package nl.ou.domain;

public class SystemExitStrategy implements StopStrategy {

    public void stop() {
        System.exit(0);
    }
    
}
