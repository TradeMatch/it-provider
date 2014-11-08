package org.itrade.benzinga.client;

import org.itrade.benzinga.BenzingaException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@ConfigurationProperties(prefix="benzinga.token")
public class TokenResolver {

    private String key;
    private File file;

    public String resolveToken() {
        if (!StringUtils.isEmpty(key)) {
            return key;
        } else {
            String tokenFromFile = readTokenFromFile();
            if (StringUtils.isEmpty(tokenFromFile)) {
                throw new BenzingaException("Benzinga token empty");
            }
            return tokenFromFile;
        }
    }

    protected String readTokenFromFile() {
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine();
        } catch (IOException e) {
            throw new BenzingaException("Error when reading token from file", e);
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
