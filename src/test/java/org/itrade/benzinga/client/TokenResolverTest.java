package org.itrade.benzinga.client;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TokenResolverTest {

    private TokenResolver tokenResolver;

    @Before
    public void setUp() throws Exception {
        tokenResolver = new TokenResolver();
        tokenResolver.setFile(new File("/tmp/benzinga.token"));
    }

    @Test
    public void should_read_benzinga_test_token_from_file() {
        tokenResolver.setFile(new File("src/test/resources/benzinga.test.token"));
        String token = tokenResolver.readTokenFromFile();
        assertThat(token, equalTo("BenzingaTestToken"));
    }

    @Test
    public void should_return_null_when_no_token_file() {
        tokenResolver.setFile(new File("test"));
        String token = tokenResolver.readTokenFromFile();
        assertThat(token, nullValue());
    }
}