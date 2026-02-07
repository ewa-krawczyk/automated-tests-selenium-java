package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import pageObjects.ProductPage;
import pageObjects.WishListPage;

public class WishListTests extends tests.BaseTest {

    private WishListPage wishListPage;

    @BeforeEach
    public void navigateToWishList() {
        wishListPage = new MainPage(driver).go().storeHeader.goToWishList();
    }

    @Test
    public void shouldAddProductToWishlistAndContainOneItem() {
        ProductPage productPage = new ProductPage(driver).go(calculusSlug);

        wishListPage = productPage.addToWishList().storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(),
                "Wishlist should contain 1 product after adding.");
    }

    @Test
    public void shouldWishlistBeEmptyWhenNoProductsAdded() {
        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(),
                "Wishlist should be empty when no products are added.");
    }

    @Test
    public void shouldRemoveProductFromWishlistAndBeEmpty() {
        ProductPage productPage = new ProductPage(driver).go(historyOfAstronomySlug);
        String productName = productPage.getNameOfProduct();

        wishListPage = productPage.addToWishList().storeHeader.goToWishList();
        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(),
                "Wishlist should contain 1 product after adding.");

        wishListPage.removeAddedProductFromWishList(productName);

        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(),
                "Wishlist should be empty after removing the product.");
        // Verify that the specific product was removed (not just count)
        Assertions.assertFalse(wishListPage.containsProduct(productName),
                "Product '" + productName + "' should be removed from wishlist.");
    }
}
