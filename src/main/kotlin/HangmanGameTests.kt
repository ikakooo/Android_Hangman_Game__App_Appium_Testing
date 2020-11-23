import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class HangmanGameTests {
        lateinit var driver: AndroidDriver<WebElement>

        @Before
        fun driverAndEmulatorSetup() {
            val cap = DesiredCapabilities()
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554")
            // cap.setCapability(CapabilityType.PLATFORM_NAME,"android")
            cap.setCapability("platformName", "android")
            cap.setCapability("appPackage", "com.example.chucknorrisjokes")
            cap.setCapability("appActivity", ".ui.SplashScreenActivity")
            cap.setCapability("noReset", true)
            driver = AndroidDriver(URL("http://localhost:4723/wd/hub"), cap)

        }

        @Test
        fun splashScreenTest() {
            Assert.assertNotNull(driver.context)
            driver.apply {
               // activityChanged(3000)
                println(currentActivity())
            }

        }








//        @Test
//        fun showJokesActivityTest() {
//            driver.apply {
//                activityChanged(4400)
//                val openFavoriteActivityButton = findElementById("com.example.chucknorrisjokes:id/favorite_ImageView_ID")
//                val openCategoryActivityButton = findElementById("com.example.chucknorrisjokes:id/choseCategory_Button_ID")
//                val randomButton = findElementById("com.example.chucknorrisjokes:id/random_Button_ID")
//                val jokeTextContent = findElementById("com.example.chucknorrisjokes:id/Text_viewID")
//                val likingButton = findElementById("com.example.chucknorrisjokes:id/favorite_Button_ID")
///////////// ჩანს თუ არა აქტივიტის ყველა ვიუ იუაიზე ///////////
//                viewIsDisplayedAssert(likingButton)
//                likingButton.click()
//                viewIsDisplayedAssert(likingButton)
//                viewIsDisplayedAssert(openFavoriteActivityButton)
//                viewIsDisplayedAssert(openCategoryActivityButton)
//                viewIsDisplayedAssert(jokeTextContent)
//                viewIsDisplayedAssert(randomButton)
/////////////// ხუმრობების ფავორიტებში დამატება
//                addInFavoritesJokes(likingButton,randomButton,7)
//                // randomButtonWillChangeJoke(randomButton, jokeTextContent, 3)
//
//            }
//        }
//
//
//        @Test
//        fun markJokeCategoryActivityTest() {
//
//            driver.apply {
//                activityChanged(4400)
//                val openCategoryActivityButton = findElementById("com.example.chucknorrisjokes:id/choseCategory_Button_ID")
//                activityChangedOnButtonClick(openCategoryActivityButton, categoryItemXPath)
//            }
//
//
//        }
//
//        @Test
//        fun favoritesActivityTest() {
//            driver.apply { activityChanged(4400)
//                val openFavoriteActivityButton = findElementById("com.example.chucknorrisjokes:id/favorite_ImageView_ID")
//                activityChangedOnButtonClick(openFavoriteActivityButton,null)
//                itemClickWillDisappearItem(openFavoriteActivityButton)
//            }
//        }

        // driver.startActivity(Activity("com.example.chucknorrisjokes", "MarkJokeCategoryActivity").setAppWaitPackage("com.example.chucknorrisjokes").setAppWaitActivity(".ui.MarkJokeCategoryActivity"))
//    @AfterClass
//    fun quiteTests() {
//        driver.close()
//        driver.quit()
//    }



}