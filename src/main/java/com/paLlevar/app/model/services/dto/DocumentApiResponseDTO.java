package com.paLlevar.app.model.services.dto;

import java.util.HashMap;
import java.util.Map;

public class DocumentApiResponseDTO {
    private Map<String,Object> data;
    private boolean isValid;

    public DocumentApiResponseDTO() {
        this.isValid = false;
        this.data = new HashMap<>();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
