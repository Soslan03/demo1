package com.example.badtiev_sobes.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private Integer batchSaveSize=5000;
    private SqlCredentials sqlCredentials;

    private Integer offsetInPastVehicleActive = 12;

    private String prefixAutocheck;

    private List<MappingServer> mappingServers;

    @Data
    public static class MappingServer {
        private String consulName;
        private String autocheckName;
    }

    @Data
    public static class SqlCredentials {
        private String login;
        private String pass;
        private String dbName;
    }
}
