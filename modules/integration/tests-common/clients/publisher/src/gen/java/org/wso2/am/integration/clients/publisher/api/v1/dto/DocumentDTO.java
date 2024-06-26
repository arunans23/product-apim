/*
 * WSO2 API Manager - Publisher API
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Publisher**.  # Authentication The Publisher REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_publisher\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_publisher\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for publisher REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:api_view apim:api_create\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:api_create apim:api_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9) 
 *
 * The version of the OpenAPI document: v4
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.publisher.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* DocumentDTO
*/

public class DocumentDTO {
        public static final String SERIALIZED_NAME_DOCUMENT_ID = "documentId";
        @SerializedName(SERIALIZED_NAME_DOCUMENT_ID)
            private String documentId;

        public static final String SERIALIZED_NAME_NAME = "name";
        @SerializedName(SERIALIZED_NAME_NAME)
            private String name;

            /**
* Gets or Sets type
*/
    @JsonAdapter(TypeEnum.Adapter.class)
public enum TypeEnum {
        HOWTO("HOWTO"),
        
        SAMPLES("SAMPLES"),
        
        PUBLIC_FORUM("PUBLIC_FORUM"),
        
        SUPPORT_FORUM("SUPPORT_FORUM"),
        
        API_MESSAGE_FORMAT("API_MESSAGE_FORMAT"),
        
        SWAGGER_DOC("SWAGGER_DOC"),
        
        OTHER("OTHER");

private String value;

TypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static TypeEnum fromValue(String value) {
    for (TypeEnum b : TypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<TypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public TypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return TypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_TYPE = "type";
        @SerializedName(SERIALIZED_NAME_TYPE)
            private TypeEnum type;

        public static final String SERIALIZED_NAME_SUMMARY = "summary";
        @SerializedName(SERIALIZED_NAME_SUMMARY)
            private String summary;

            /**
* Gets or Sets sourceType
*/
    @JsonAdapter(SourceTypeEnum.Adapter.class)
public enum SourceTypeEnum {
        INLINE("INLINE"),
        
        MARKDOWN("MARKDOWN"),
        
        URL("URL"),
        
        FILE("FILE");

private String value;

SourceTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static SourceTypeEnum fromValue(String value) {
    for (SourceTypeEnum b : SourceTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<SourceTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final SourceTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SourceTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return SourceTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_SOURCE_TYPE = "sourceType";
        @SerializedName(SERIALIZED_NAME_SOURCE_TYPE)
            private SourceTypeEnum sourceType;

        public static final String SERIALIZED_NAME_SOURCE_URL = "sourceUrl";
        @SerializedName(SERIALIZED_NAME_SOURCE_URL)
            private String sourceUrl;

        public static final String SERIALIZED_NAME_FILE_NAME = "fileName";
        @SerializedName(SERIALIZED_NAME_FILE_NAME)
            private String fileName;

        public static final String SERIALIZED_NAME_INLINE_CONTENT = "inlineContent";
        @SerializedName(SERIALIZED_NAME_INLINE_CONTENT)
            private String inlineContent;

        public static final String SERIALIZED_NAME_OTHER_TYPE_NAME = "otherTypeName";
        @SerializedName(SERIALIZED_NAME_OTHER_TYPE_NAME)
            private String otherTypeName;

            /**
* Gets or Sets visibility
*/
    @JsonAdapter(VisibilityEnum.Adapter.class)
public enum VisibilityEnum {
        OWNER_ONLY("OWNER_ONLY"),
        
        PRIVATE("PRIVATE"),
        
        API_LEVEL("API_LEVEL");

private String value;

VisibilityEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static VisibilityEnum fromValue(String value) {
    for (VisibilityEnum b : VisibilityEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<VisibilityEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final VisibilityEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public VisibilityEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return VisibilityEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_VISIBILITY = "visibility";
        @SerializedName(SERIALIZED_NAME_VISIBILITY)
            private VisibilityEnum visibility;

        public static final String SERIALIZED_NAME_CREATED_TIME = "createdTime";
        @SerializedName(SERIALIZED_NAME_CREATED_TIME)
            private String createdTime;

        public static final String SERIALIZED_NAME_CREATED_BY = "createdBy";
        @SerializedName(SERIALIZED_NAME_CREATED_BY)
            private String createdBy;

        public static final String SERIALIZED_NAME_LAST_UPDATED_TIME = "lastUpdatedTime";
        @SerializedName(SERIALIZED_NAME_LAST_UPDATED_TIME)
            private String lastUpdatedTime;

        public static final String SERIALIZED_NAME_LAST_UPDATED_BY = "lastUpdatedBy";
        @SerializedName(SERIALIZED_NAME_LAST_UPDATED_BY)
            private String lastUpdatedBy;


        public DocumentDTO documentId(String documentId) {
        
        this.documentId = documentId;
        return this;
        }

    /**
        * Get documentId
    * @return documentId
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", value = "")
    
    public String getDocumentId() {
        return documentId;
    }


    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }


        public DocumentDTO name(String name) {
        
        this.name = name;
        return this;
        }

    /**
        * Get name
    * @return name
    **/
      @ApiModelProperty(example = "PizzaShackDoc", required = true, value = "")
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


        public DocumentDTO type(TypeEnum type) {
        
        this.type = type;
        return this;
        }

    /**
        * Get type
    * @return type
    **/
      @ApiModelProperty(example = "HOWTO", required = true, value = "")
    
    public TypeEnum getType() {
        return type;
    }


    public void setType(TypeEnum type) {
        this.type = type;
    }


        public DocumentDTO summary(String summary) {
        
        this.summary = summary;
        return this;
        }

    /**
        * Get summary
    * @return summary
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "Summary of PizzaShackAPI Documentation", value = "")
    
    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


        public DocumentDTO sourceType(SourceTypeEnum sourceType) {
        
        this.sourceType = sourceType;
        return this;
        }

    /**
        * Get sourceType
    * @return sourceType
    **/
      @ApiModelProperty(example = "INLINE", required = true, value = "")
    
    public SourceTypeEnum getSourceType() {
        return sourceType;
    }


    public void setSourceType(SourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }


        public DocumentDTO sourceUrl(String sourceUrl) {
        
        this.sourceUrl = sourceUrl;
        return this;
        }

    /**
        * Get sourceUrl
    * @return sourceUrl
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getSourceUrl() {
        return sourceUrl;
    }


    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


        public DocumentDTO fileName(String fileName) {
        
        this.fileName = fileName;
        return this;
        }

    /**
        * Get fileName
    * @return fileName
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


        public DocumentDTO inlineContent(String inlineContent) {
        
        this.inlineContent = inlineContent;
        return this;
        }

    /**
        * Get inlineContent
    * @return inlineContent
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "This is doc content. This can have many lines.", value = "")
    
    public String getInlineContent() {
        return inlineContent;
    }


    public void setInlineContent(String inlineContent) {
        this.inlineContent = inlineContent;
    }


        public DocumentDTO otherTypeName(String otherTypeName) {
        
        this.otherTypeName = otherTypeName;
        return this;
        }

    /**
        * Get otherTypeName
    * @return otherTypeName
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getOtherTypeName() {
        return otherTypeName;
    }


    public void setOtherTypeName(String otherTypeName) {
        this.otherTypeName = otherTypeName;
    }


        public DocumentDTO visibility(VisibilityEnum visibility) {
        
        this.visibility = visibility;
        return this;
        }

    /**
        * Get visibility
    * @return visibility
    **/
      @ApiModelProperty(example = "API_LEVEL", required = true, value = "")
    
    public VisibilityEnum getVisibility() {
        return visibility;
    }


    public void setVisibility(VisibilityEnum visibility) {
        this.visibility = visibility;
    }


        public DocumentDTO createdTime(String createdTime) {
        
        this.createdTime = createdTime;
        return this;
        }

    /**
        * Get createdTime
    * @return createdTime
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }


        public DocumentDTO createdBy(String createdBy) {
        
        this.createdBy = createdBy;
        return this;
        }

    /**
        * Get createdBy
    * @return createdBy
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "admin", value = "")
    
    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


        public DocumentDTO lastUpdatedTime(String lastUpdatedTime) {
        
        this.lastUpdatedTime = lastUpdatedTime;
        return this;
        }

    /**
        * Get lastUpdatedTime
    * @return lastUpdatedTime
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }


    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }


        public DocumentDTO lastUpdatedBy(String lastUpdatedBy) {
        
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
        }

    /**
        * Get lastUpdatedBy
    * @return lastUpdatedBy
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "admin", value = "")
    
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }


    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            DocumentDTO document = (DocumentDTO) o;
            return Objects.equals(this.documentId, document.documentId) &&
            Objects.equals(this.name, document.name) &&
            Objects.equals(this.type, document.type) &&
            Objects.equals(this.summary, document.summary) &&
            Objects.equals(this.sourceType, document.sourceType) &&
            Objects.equals(this.sourceUrl, document.sourceUrl) &&
            Objects.equals(this.fileName, document.fileName) &&
            Objects.equals(this.inlineContent, document.inlineContent) &&
            Objects.equals(this.otherTypeName, document.otherTypeName) &&
            Objects.equals(this.visibility, document.visibility) &&
            Objects.equals(this.createdTime, document.createdTime) &&
            Objects.equals(this.createdBy, document.createdBy) &&
            Objects.equals(this.lastUpdatedTime, document.lastUpdatedTime) &&
            Objects.equals(this.lastUpdatedBy, document.lastUpdatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, name, type, summary, sourceType, sourceUrl, fileName, inlineContent, otherTypeName, visibility, createdTime, createdBy, lastUpdatedTime, lastUpdatedBy);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class DocumentDTO {\n");
    sb.append("    documentId: ").append(toIndentedString(documentId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
    sb.append("    sourceUrl: ").append(toIndentedString(sourceUrl)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    inlineContent: ").append(toIndentedString(inlineContent)).append("\n");
    sb.append("    otherTypeName: ").append(toIndentedString(otherTypeName)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    lastUpdatedTime: ").append(toIndentedString(lastUpdatedTime)).append("\n");
    sb.append("    lastUpdatedBy: ").append(toIndentedString(lastUpdatedBy)).append("\n");
sb.append("}");
return sb.toString();
}

/**
* Convert the given object to string with each line indented by 4 spaces
* (except the first line).
*/
private String toIndentedString(Object o) {
if (o == null) {
return "null";
}
return o.toString().replace("\n", "\n    ");
}

}

