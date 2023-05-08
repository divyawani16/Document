
 package com.document_management.DTO;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import io.swagger.models.auth.In;
        import lombok.*;
        import org.mapstruct.Mapping;

 @Data
 @Getter
 @Setter
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @JsonInclude(JsonInclude.Include.NON_NULL)
 public class UserPropertyDto {

     private Integer userPropertyId;
     private Integer userId;
     private Integer propertyId;
     private Integer roleId;

 }


