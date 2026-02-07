package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObjects.CartPage;
import pageObjects.MainPage;
import pageObjects.ProductPage;
import pageObjects.WishListPage;

public class WishListTests extends tests.BaseTest {

    @Test
    public void productAddedToWishlistShouldWishlistHaveOneItem() {
        ProductPage productPage = new ProductPage(driver).go(calculusSlug);

        WishListPage wishListPage = productPage.addToWishList().storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(),
                "Wishlist should contain 1 product after adding.");
    }

    @Test
    public void noProductAddedToWishlistShouldWishlistBeEmpty() {
        MainPage mainPage = new MainPage(driver);
        WishListPage wishListPage = mainPage.go().storeHeader.goToWishList();

        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(),
                "Wishlist should be empty when no products are added.");
    }

    @Test
    public void removeAddedProductFromWishlist() {
        ProductPage productPage = new ProductPage(driver).go(historyOfAstronomySlug);
        String productName = productPage.getNameOfProduct();
        WishListPage wishListPage = productPage.addToWishList().storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(),
                "Wishlist should contain 1 product after adding");

        wishListPage.removeAddedProductFromWishList(productName);

        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(),
                "Wishlist should be empty after removing the product.");
    }

    @Test
    public void shouldAddProductToWishlistThenAddToCartAndVerifyInCart() {
        ProductPage productPage = new ProductPage(driver).go(historyOfAstronomySlug);
        String expectedProductTitle = productPage.getNameOfProduct();
        String expectedPrice = productPage.getProductPrice();

        productPage.addToWishList();
        WishListPage wishListPage = productPage.storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(),
                "Wishlist should contain 1 product after adding.");

        CartPage cartPage = wishListPage.addProductToCartFromWishList(expectedProductTitle);

        Assertions.assertEquals(1, cartPage.getNumberOfProducts(),
                "Cart should contain 1 product after adding from wishlist.");

        String actualProductTitle = cartPage.getProductTitle();
        Assertions.assertEquals(expectedProductTitle, actualProductTitle,
                "Product title in cart does not match. Expected: " + expectedProductTitle +
                        " but got: " + actualProductTitle);

        String actualPrice = cartPage.getTotalPrice();
        Assertions.assertEquals(expectedPrice, actualPrice,
                "Product price in cart does not match. Expected: " + expectedPrice +
                        " but got: " + actualPrice);

        String actualQuantity = cartPage.getProductQuantity();
        Assertions.assertEquals("1", actualQuantity,
                "Product quantity in cart should be 1. Got: " + actualQuantity);
    }
}
