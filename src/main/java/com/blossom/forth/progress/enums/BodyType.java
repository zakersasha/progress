package com.blossom.forth.progress.enums;

public enum BodyType {
    /**
     * Грудь
     */
    CHEST ("Грудь"),
    /**
     * Спина
     */
    BACK ("Спина"),
    /**
     * Ноги
     */
    LEGS ("Ноги"),
    /**
     * Плечи
     */
    SHOULDERS ("Плечи"),
    /**
     * Трицепс
     */
    TRICEPS ("Трицепс"),
    /**
     * Бицепс
     */
    BICEPS ("Бицепс");

    public String getName() {
        return name;
    }

    private final String name;

    BodyType(String name) {
        this.name = name;
    }



}
