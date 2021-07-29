package com.example.widetech.data.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Records {
    @SerializedName("Record")
    private List<JsonObject> listRecord;

    public Records() {
        this.listRecord = new ArrayList<>();
    }

    public List<JsonObject> getListRecord() {
        return listRecord;
    }

    @Override
    public String toString() {
        return "Total Records -> " + (listRecord != null ? listRecord.size() : "");
    }
}
