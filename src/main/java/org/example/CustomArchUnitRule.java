package org.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class CustomArchUnitRule {
    public static void main(String[] args) {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.example");

        String logFilesDirectory = "C:\\Users\\nogga\\IdeaProjects\\example\\out";

        ArchRule rule = classes()
                .that(CustomPredicates.areAnnotatedWithExact())
                .should(CustomConditions.notHaveUnsafeInTxtFiles(logFilesDirectory));

        rule.check(importedClasses);
    }
}
