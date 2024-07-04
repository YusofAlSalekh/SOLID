package org.example;

import java.util.List;

public interface MethodBodyStrategy {
    String generateMethodBody(List<VerifyDataClass> verifyDataClasses) throws Exception;
}
