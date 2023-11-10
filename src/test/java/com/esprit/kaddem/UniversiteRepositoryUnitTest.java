package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class UniversiteRepositoryUnitTest {

    @Mock
    UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        universiteRepository.save(new Universite(1,"Manar"));
        universiteRepository.save(new Universite(2,"Sfax"));
    }

    @AfterEach
    public void destroy() {
        universiteRepository.deleteAll();
    }

    @Test
    public void testGetAllUniversites() {

    }

    @Test
    public void testGetInvaliduniversite() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            universiteRepository.findById(1).get();
        });
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }



    @Test
    public void testDeleteUniversite() {
        Universite saved = new Universite(5, "ron");
        universiteRepository.save(saved);
        universiteRepository.delete(saved);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            universiteRepository.findById(5).get();
        });
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }
}