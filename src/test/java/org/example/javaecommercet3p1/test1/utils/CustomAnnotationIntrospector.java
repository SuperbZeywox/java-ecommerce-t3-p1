package org.example.javaecommercet3p1.test1.utils;


import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class CustomAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m) {
//        return super.hasIgnoreMarker(m);
        return false;
    }


    //    @Override
//    public boolean hasIgnoreMarker(Annotated ann) {
//        // Custom logic to decide if a field or property should be ignored
//        if (ann.getName().equals("age")) {
//            return true;  // Ignore "age" field
//        }
//        return super.hasIgnoreMarker(ann);  // Default behavior for other fields
//    }

}
