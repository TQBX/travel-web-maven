package com.travel.myenum;

/**
 * @author Summerday
 *
 * 旅游主题枚举类
 */
public enum CategoryEnum {
    /**
     * 人气旅游
     */
    COUNT("count","findCount"),
    /**
     * 最新旅游
     */
    NEWEST("rdate","findDate"),
    /**
     * 主题旅游
     */
    THEME("isThemeTour","findTheme"),
    /**
     * 默认为人气旅游
     */
    DEFAULT("","findCount");
    private String category;
    private String methodName;
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getMethodName() {
        return methodName;
    }

    CategoryEnum(String category, String methodName) {
        this.category = category;
        this.methodName = methodName;
    }
}
