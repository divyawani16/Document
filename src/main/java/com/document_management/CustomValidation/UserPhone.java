//package com.document_management.CustomValidation;
//
//
//
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class UserPhone implements ConstraintValidator<PhoneNoValidation, String> {
//
//
//    List<String> proStatus = Arrays.asList("90","30");
//
//    @Override
//    public void initialize(PhoneNoValidation constraintAnnotation) {
//// ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
//        return proStatus.contains(phoneNumber);
//
//    }
//}