package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import pageObjects.ProductPage;
import pageObjects.WishListPage;

public class WishListTests extends tests.BaseTest {

    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomySlug = "/product/history-of-astronomy-by-george-forbes/";
    String makersOfElectricitySlug = "/product/makers-of-electricity-by-brother-potamian-and-james-j-walsh/";


    @Test
    public void productAddedToWishlistShouldWishlistHaveOneItem() {
        ProductPage productPage = new ProductPage(driver).go(calculusSlug);

        WishListPage wishListPage = productPage.addToWishList().storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(), "Number of products in wishlist is not what expected.");
    }

    @Test
    public void noProductAddedToWishlistShouldWishlistBeEmpty() {
        MainPage mainPage = new MainPage(driver);
        WishListPage wishListPage = mainPage.go().storeHeader.goToWishList();

        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(), "Number of products in wishlist is not what expected.");
    }

    @Test
    public void removeAddedProductFromWishlist() {
        ProductPage productPage = new ProductPage(driver).go(historyOfAstronomySlug);
        String product2 =  productPage.getNameOfProduct();
        WishListPage wishListPage = productPage.addToWishList().storeHeader.goToWishList();

        Assertions.assertEquals(1, wishListPage.getNumberOfProducts(), "Number of products in wishlist is not what " +
                "expected.");

        wishListPage.removeAddedProductFromWishList(product2);

        Assertions.assertEquals(0, wishListPage.getNumberOfProducts(), "Number of products in wishlist is not what " +
                "expected.");
    }
}
