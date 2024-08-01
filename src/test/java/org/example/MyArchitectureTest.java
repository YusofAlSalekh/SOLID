package org.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.example.ArchUnit.CustomConditions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MyArchitectureTest {
    private static String packageName;
    private static String outputDir;

    @BeforeAll
    public static void setUp() {
        packageName = "org.example";
        outputDir = "build/classes/java/main";
    }

    @Test
    public void implementationsOfMethodShouldBeInOrgExamplePackage() {
        JavaClasses classes = new ClassFileImporter().importPackages("org.example");

        ArchRule rule = classes()
                .that().implement(Incrementation.class)
                .should().resideInAPackage("org.example")
                .because("Implementations of the Method interface should reside in the 'org.example' package to ensure proper organization.");

        rule.check(classes);
    }

    @Test
    public void implementationsOfIncrementationShouldNotHaveUnsafeInTxtFiles() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(packageName);

        ArchRule txtFileCheckRule = classes()
                .that().implement(Incrementation.class)
                .should(CustomConditions.notHaveUnsafeInTxtFiles(packageName, outputDir, Incrementation.class))
                .because("Despite the logic of the implementing classes, they should give the same output");

        txtFileCheckRule.check(importedClasses);
    }

    @Test
    public void implementationsOfSorterShouldNotHaveUnsafeInTxtFiles() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(packageName);

        ArchRule txtFileCheckRule = classes()
                .that().implement(Sorter.class)
                .should(CustomConditions.notHaveUnsafeInTxtFiles(packageName, outputDir, Sorter.class))
                .because("Despite the logic of the implementing classes, they should give the same output");

        txtFileCheckRule.check(importedClasses);
    }
}

