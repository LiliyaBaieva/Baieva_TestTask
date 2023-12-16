package com.globallogic.TestsUI;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AddItemToShoppingCartUITests extends TestsBaseUI{

    @Test(priority = 1)
    public void addComputerToShoppingCartTest(){
        app.getItems().openURL(app.BASE_URL);
        app.getItems().selectDesktopInComputer();
        app.getItems().setDisplaySize(4);
        app.getItems().SortPriceHighToLow();
        app.getItems().chooseItemNum(1);
        String itemName = app.getItems().getItemName();
        app.getItems().addItemToShoppingCart();

        Assert.assertTrue(app.getItems().isItemInShoppingCart(itemName));

    }

    @Test(priority = 2)
    //I tried to make the tests independent of each other, but the assignment stated:
    //Click "Add to cart" -> check the shopping cart has +1 item.
    public void checkItemAndPriceInShoppingCart(){
        String itemURL = app.BASE_URL + "/build-your-own-expensive-computer-2";
        app.getItems().openURL(itemURL);

        String processor = "Fast";
        int ram = 8;
        List<String> softwareList = Arrays.asList("Image Viewer","Office Suite", "Other Office Suite");
        int positionInCart = 2;

        String itemName = app.getItems().getItemName();
        app.getItems().setProcessor(processor);
        app.getItems().setRAM(ram);
        app.getItems().selectAllAvailableSoftware(softwareList);
        String itemPrice = app.getItems().getItemPrice();
        app.getItems().addItemToShoppingCart();

        Assert.assertTrue(app.getItems().isItemWithParamInShoppingCart(
                itemName, processor, ram, softwareList, positionInCart));
        Assert.assertEquals(app.getItems().getItemInShoppingCart(positionInCart), itemPrice);

        app.getItems().openShoppingCart();
        app.getItems().deleteItemFromShoppingCart(itemName, positionInCart);

    }
}
