import io.appium.java_client.android.AndroidDriver
import org.junit.Assert
import org.openqa.selenium.WebElement

object DisplayedViews {
    @JvmStatic
    fun viewIsDisplayedAssert(view:WebElement){
        Assert.assertTrue(view.isDisplayed)
    }
}