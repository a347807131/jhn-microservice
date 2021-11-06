package fun.gatsby.jhn.microservice;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fun.gatsby.jhn.microservice");

        noClasses()
            .that()
            .resideInAnyPackage("fun.gatsby.jhn.microservice.service..")
            .or()
            .resideInAnyPackage("fun.gatsby.jhn.microservice.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fun.gatsby.jhn.microservice.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
