package tests;

import org.junit.jupiter.api.*;
import pageObjects.CartPage;
import pageObjects.ProductPage;


public class CartTests  extends tests.BaseTest {

    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";

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
}