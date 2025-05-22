package pageUIs;

public class LeftMenuPageUI {
    public static final String LEFT_MENU = "xpath=//div[contains(@class,'left-sidebar')]";

    public static final String PROCUREMENT_MENU = "xpath=//span[@class='text-xs mt-1 text-center' and text()='Procurement']/parent::div/parent::div";
    public static final String REQUISITION_MENU = "xpath=//div[@class='flex-1']//div[text()='Requisition']";
    public static final String SPARE_REQUISITION_MENU = "xpath=//div[@class='flex-1']//div[text()='Requisition']/parent::div/parent::div/following-sibling::div//a[text()='Spare Parts']";
    public static final String CATEGORY_HOME_MAIN ="xpath=//div[text()='Home']/parent::div//following-sibling::div[contains(@class,'gap-3')]";
}
