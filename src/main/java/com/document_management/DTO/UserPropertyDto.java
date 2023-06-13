
 package com.document_management.DTO;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import lombok.*;
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


