package com.musemo.controller;

import java.util.regex.Pattern;

/**
 *
 * @author Viom Shrestha
 */
public class ValidationUtil {
    private static final Pattern OBJECT_ID_PATTERN = Pattern.compile("^\\d{3}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    private static final Pattern OBJECT_TYPE_PATTERN = Pattern.compile("^(Painting|Sculpture|Artifact|Other)$");
    private static final Pattern STATUS_PATTERN = Pattern.compile("^(Permanent|Temporary)$");
    private static final Pattern CONDITION_PATTERN = Pattern.compile("^(Excellent||Good|Bad|Needs Restoration|Damaged)$");
    
    /**
     * Validates if a string text field is null or empty.
     *
     * @param value the string to validate
     * @return true if the string is null or empty, otherwise false
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Validates if string fields like object name, creator and origin contains only alphabets.
     *
     * @param name the name to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidName(String name) {
        return !isNullOrEmpty(name) && NAME_PATTERN.matcher(name).matches();
    }

    /**
     * Validates if the object ID is exactly 3 digits.
     *
     * @param objectId the Object ID to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidObjectId(int objectId) {
        return OBJECT_ID_PATTERN.matcher(String.valueOf(objectId)).matches();
    }

    /**
     * Validates if the object type is one of the allowed options.
     *
     * @param objectType the object type to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidObjectType(String objectType) {
        return !isNullOrEmpty(objectType) && OBJECT_TYPE_PATTERN.matcher(objectType).matches();
    }
    
    /**
     * Validates if the status is of the allowed options.
     *
     * @param status the object status to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidStatus(String status) {
        return !isNullOrEmpty(status) && STATUS_PATTERN.matcher(status).matches();
    }
    
    /**
     * Validates if the condition is of the allowed options.
     *
     * @param condition the object's condition to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidCondition(String condition) {
        return !isNullOrEmpty(condition) && CONDITION_PATTERN.matcher(condition).matches();
    }

    /**
     * Validates if the floor number is between 1 and 5 (inclusive).
     *
     * @param floorNo the floor number to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidFloor(short floorNo) {
        return floorNo>=1 && floorNo<=5;
    }

    /**
     * Validates if the room number is between 1 and 7 (inclusive).
     *
     * @param roomNo the room number to validate
     * @return true if valid, otherwise false
     */
    public static boolean isValidRoom(short roomNo) {
        return roomNo >= 1 && roomNo <= 7;
    }
}