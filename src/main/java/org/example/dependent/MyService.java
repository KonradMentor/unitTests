package org.example.dependent;

public class MyService {
    private static final String CLIENT_IS_NOT_HEALTHY = "Client is not healthy!";
    private final Client client;

    public MyService(Client client) {
        this.client = client;
    }

    public String performAction() {
        if (!client.checkHealth()) {
            throw new RuntimeException(CLIENT_IS_NOT_HEALTHY);
        }
        return client.call();
    }

    public boolean checkClientIsHealthy() {
        return client.checkHealth();
    }

    public void clean() {
        client.performClean();
    }
}
