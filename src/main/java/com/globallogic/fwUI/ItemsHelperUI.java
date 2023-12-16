package com.globallogic.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ItemsHelperUI extends BaseHelperUI{

    public ItemsHelperUI(WebDriver driver) {
        super(driver);
    }


    public void selectDesktopInComputer() {
        clickOnComputers();
        click(By.cssSelector("div.item-box:nth-child(1) div.sub-category-item h2.title > a:nth-child(1)"));
    }

    private void clickOnComputers() {
        click(By.xpath("//div[@class = \"header-menu\"]/ul[1]/li[2]"));
    }

    public void setDisplaySize(int size) {
        click(By.id("products-pagesize"));
        click(By.xpath("//option[contains(., '" + size + "')]"));
    }

    public void SortPriceHighToLow() {
        click(By.xpath("//option[contains(text(),'Price: High to Low')]"));
    }

    public void chooseItemNum(int itemNum) {
        click(By.cssSelector(
                "div.item-box:nth-child(1) div.product-item div.details h2.product-title > a:nth-child(1)"));
    }

    public String getItemName() {
        return getElementText(By.tagName("h1"));
    }

    public void addItemToShoppingCart() {
        click(By.className("add-to-cart-button"));
        click(By.className("close"));
        pause(1000);
    }

    public boolean isItemInShoppingCart(String itemName) {
        openShoppingCart();
        return isElementPresent(By.xpath(
                "//td[3]/a[contains(text(), 'Build your own expensive computer')]"));
    }

    public void openShoppingCart() {
        click(By.id("topcartlink"));
    }


    public String getItemPrice() {
        return getElementText(By.cssSelector("div.product-price span"));
    }

    public void setProcessor(String processor) {
        click(By.xpath(
                "//dl[1]/dd[1]/ul[1]/li/label[contains(text(),'" + processor + "')]/../input"));
    }

    public void setRAM(int ram) {
        click(By.xpath(
                "//dl[1]/dd[2]/ul[1]/li/label[contains(text(),'" + ram + "')]/../input"));
    }

    public void selectAllAvailableSoftware(List<String> softwareList) {
        for(String software : softwareList){
            click(By.xpath("//dl[1]/dd[4]/ul[1]/li/label[contains(text(),'" + software + "')]/../input"));
        }
    }

    public boolean isItemWithParamInShoppingCart(String itemName, String processor, int ram,
                                                 List<String> softwareList, int positionInCart) {
        openShoppingCart();
        String itemInCartText = getElementText(By.xpath("//tbody/tr[" + positionInCart + "]/td[3]/div[1]"));

        boolean checkName = isItemInShoppingCart(itemName);
        boolean checkProcessor = itemInCartText.contains("Processor: " + processor);
        boolean checkRAM = itemInCartText.contains("RAM: " + ram);
        boolean checkAllSoftware = checkSoftwareList(softwareList, itemInCartText);

        if(checkName && checkProcessor && checkRAM && checkAllSoftware){
            return true;
        }

        return false;
    }

    private static boolean checkSoftwareList(List<String> softwareList, String itemInCartText) {
        boolean result = true;
        for(String software : softwareList){
            boolean checkSoftware = itemInCartText.contains("Software: " + software);
            if(!checkSoftware){
                result = false;
            }
        }
        return result;
    }


    public String getItemInShoppingCart(int positionInCart) {
        String elementText = getElementText(By.xpath("//tbody/tr[" + positionInCart + "]/td[6]/span[2]"));
        System.out.println("******" + elementText);
        return elementText;
    }

    public void deleteItemFromShoppingCart(String itemName, int positionInCart) {
        click(By.xpath("//tbody[1]/tr[" + positionInCart + "]/td[1]/input[1]"));
        click(By.cssSelector("input[name='updatecart']"));
    }
}
