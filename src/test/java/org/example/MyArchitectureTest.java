package org.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


public class MyArchitectureTest {

    @Test
    public void implementationsOfMethodShouldBeInOrgExamplePackage() {
        JavaClasses classes = new ClassFileImporter().importPackages("org.example");

        ArchRule rule = classes()
                .that().implement(Incrementation.class)
                .should().resideInAPackage("org.example")
                .because("Implementations of the Method interface should reside in the 'org.example' package to ensure proper organization.");

        rule.check(classes);
    }
}

