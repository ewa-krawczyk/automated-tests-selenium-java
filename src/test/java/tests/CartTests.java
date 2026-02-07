package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObjects.CartPage;
import pageObjects.MainPage;
import pageObjects.ProductPage;

public class CartTests extends tests.BaseTest {

    String historyOfAstronomySlug = "/history-of-astronomy-by-george-forbes/";

    @Test
    public void noProductAddedToCartShouldCartBeEmpty() {
        CartPage cartPage = new CartPage(driver).go();

        Assertions.assertEquals(0, cartPage.getNumberOfProducts(),
                "Number of products in cart is not 0.");
    }

    @Test
    public void productAddedToCartShouldCartHaveOneProduct() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage.go(calculusSlug).addToCart().goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void twoProductsAddedToCartShouldCartHaveTwoProducts() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart()
                .go(historyOfAstronomySlug).addToCart().goToCart();

        int numberOfProducts = cartPage.getNumberOfProducts();
        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void changingQuantityInCartShouldChangeTotalPrice() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(3);

        Assertions.assertEquals("39,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }

    @Test
    public void changingQuantityInCartToNegativeShouldNotUpdateTotalPrice() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("13,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }

    @Test
    public void changingQuantityToZeroShouldRemoveProduct() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(0);

        Assertions.assertEquals(0, cartPage.getNumberOfProducts(),
                "Product should be removed when quantity is set to 0");
    }

    @Test
    public void changingQuantityToLargeNumberShouldUpdatePrice() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(100);

        Assertions.assertEquals("1300,00 €",
                cartPage.getTotalPrice(),
                "Total price should update correctly for large quantities");
    }

    @Test
    public void clickingOnEmptyCartShouldDisplayEmptyCartMessage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.go().storeHeader.clickOnCart();

        mainPage.validateMessageOnEmptyCart();
    }
}