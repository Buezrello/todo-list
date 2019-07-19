package igor.learning.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {
    @Override
    public String getHelloMessage(String user) {
        return "Hello " + user;
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to this Demo application.";
    }
}

