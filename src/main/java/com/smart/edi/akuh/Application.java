package com.smart.edi.akuh;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Nairobi"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        NukeSSLCerts.nuke();
    }
}
