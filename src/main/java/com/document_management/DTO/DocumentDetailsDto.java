package com.document_management.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor

@AllArgsConstructor
public class DocumentDetailsDto {
    private String documentName;
    private String userName;
    private String PropertyName;
    private String docTypeName;
    private String docMimeTypeName;
//
//    private String documentName;
//    private String userName;
//
//    public String getDocumentName() {
//        return documentName;
//    }
//    public void setDocumentName(String documentName) {
//        this.documentName = documentName;
//    }
//    public String getUserName() {
//        return userName;
//    }
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPropertyName() {
//        return propertyName;
//    }
//
//    public void setPropertyName(String propertyName) {
//        this.propertyName = propertyName;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
//    private String propertyName;
//    private LocalDateTime createdDate;
//    private String stageName;
}
