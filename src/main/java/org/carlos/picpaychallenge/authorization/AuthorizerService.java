package org.carlos.picpaychallenge.authorization;

import org.carlos.picpaychallenge.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);

    private RestClient restClient;


    public AuthorizerService(RestClient.Builder builder, @Value("${authorizer.service.url}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public void authorize(Transaction transaction) {
        LOGGER.info("Authorizing transaction - {}", transaction);
        ResponseEntity<Authorization> response = this.restClient.get()
                .retrieve()
                .toEntity(Authorization.class);

        Authorization body = response.getBody();
        if (response.getStatusCode().isError() || body == null || !body.isAuthorized()) {
            throw new UnauthorizedTransactionException("Unauthorized Transaction Exception - %s".formatted(transaction));
        }


        LOGGER.info("Transaction authorized - {}", transaction);
    }
}
