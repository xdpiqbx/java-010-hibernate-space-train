package cw.train.tests;

import com.fasterxml.jackson.core.Base64Variant;
import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class AddressConverter  implements AttributeConverter<List<String>, String> {
  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    return new Gson().toJson(attribute);
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    return new Gson().fromJson(dbData, List.class);
  }
}
