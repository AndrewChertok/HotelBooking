package com.hotelbooking.util;

import com.hotelbooking.model.RoomType;

import java.beans.PropertyEditorSupport;


public class RoomTypeEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String type = text.toUpperCase();
        RoomType roomType = RoomType.valueOf(type);
        setValue(roomType);
    }
}
