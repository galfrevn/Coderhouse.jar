package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.Client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testCreateAndFindClient() {
        Client client = new Client();

        client.setName("John");
        client.setLastName("Doe");
        client.setDocument("12345678901");

        clientRepository.save(client);

        Client createdClient = clientRepository.findById(1).orElse(null);
        assertThat(createdClient).isNotNull();
        assertThat(createdClient.getName()).isEqualTo(client.getName());
        assertThat(createdClient.getLastName()).isEqualTo(client.getLastName());
        assertThat(createdClient.getDocument()).isEqualTo(client.getDocument());
    }

    @Test
    public void testDeleteClient() {
        clientRepository.deleteById(1);

        Client client = clientRepository.findById(1).orElse(null);
        assertThat(client).isNull();
    }
}


