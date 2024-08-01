package org.example.ArchUnit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.example.Exact;


public class CustomPredicates {
    public static DescribedPredicate<JavaClass> areAnnotatedWithExact() {
        return new DescribedPredicate<JavaClass>("are annotated with @Exact") {
            @Override
            public boolean test(JavaClass javaClass) {
                return javaClass.isAnnotatedWith(Exact.class);
            }
        };
    }
}

