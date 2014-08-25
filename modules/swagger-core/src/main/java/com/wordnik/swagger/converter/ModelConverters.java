package com.wordnik.swagger.converter;

import com.wordnik.swagger.util.Json;
import com.wordnik.swagger.models.*;
import com.wordnik.swagger.models.properties.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.swagger.ModelResolver;

import java.util.*;

public class ModelConverters {
  static ObjectMapper mapper = Json.mapper();

  public static Property readAsProperty(Class cls) {
    try {
      Property property = new ModelResolver(mapper).resolveProperty(cls);
      return property;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Map<String, Model> read(Class<?> cls) {
    Map<String, Model> output = new HashMap<String, Model>();
    if(shouldProcess(cls)) {
      ModelResolver resolver = new ModelResolver(mapper);
      Model model = resolver.resolve(cls);
      if(model instanceof ModelImpl)
        output.put(((ModelImpl)model).getName(), model);
    }
    return output;
  }

  public static Map<String, Model> readAll(Class<?> cls) {
    Map<String, Model> output = new HashMap<String, Model>();
    if(shouldProcess(cls)) {
      ModelResolver resolver = new ModelResolver(mapper);
      resolver.resolve(cls);
      return resolver.getDetectedTypes();
    }
    else return output;
  }

  static boolean shouldProcess(Class<?> cls) {
    if(cls.getName().startsWith("java.lang"))
      return false;
    return true;
  }
}