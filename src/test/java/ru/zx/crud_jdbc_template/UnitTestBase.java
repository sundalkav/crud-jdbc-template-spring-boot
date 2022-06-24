package ru.zx.crud_jdbc_template;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.zx.crud_jdbc_template.initializer.Postgres;

@ActiveProfiles("active")
@SpringBootTest
//@ContextConfiguration(initializers = {
//        Postgres.Initializer.class
//})
public abstract class UnitTestBase {

//    @BeforeAll
//    static void initContainer() {
//        Postgres.container.start();
//    }

}
