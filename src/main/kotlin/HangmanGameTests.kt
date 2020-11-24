import DisplayedViews.viewIsDisplayedAssert
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsString
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
        cap.setCapability("appPackage", "com.example.android_hangman_game")
        cap.setCapability("appActivity", ".PlayerInitialiseActivity")
        cap.setCapability("noReset", true)
        driver = AndroidDriver(URL("http://localhost:4723/wd/hub"), cap)

    }

    @Test
    fun emulatorConnectionTest() {
        Assert.assertNotNull(driver.context)
        Thread.sleep(5000)
        driver.apply {
            val appPAth = currentPackage + currentActivity()
            println(appPAth)
            Assert.assertEquals("com.example.android_hangman_game.PlayerInitialiseActivity", appPAth)
        }
    }

    @Test
    fun playerInitialiseActivityTest() {
        Thread.sleep(5000)
        driver.apply {
            val nameEditTextField = findElementById("com.example.android_hangman_game:id/playerNameEditTextID")
            val incognitoWordEditTextField =
                findElementById("com.example.android_hangman_game:id/incognitoWordEditTextID")
            val startGameButton = findElementById("com.example.android_hangman_game:id/startGameButtonID")
            viewIsDisplayedAssert(nameEditTextField)
            viewIsDisplayedAssert(incognitoWordEditTextField)
            viewIsDisplayedAssert(startGameButton)
        }
        println(driver.currentActivity())
    }

    @Test
    fun gameActivityTest() {
        Thread.sleep(5000)
        driver.apply {
            val nameEditTextField = findElementById("com.example.android_hangman_game:id/playerNameEditTextID")
            val incognitoWordEditTextField =
                findElementById("com.example.android_hangman_game:id/incognitoWordEditTextID")
            val startGameButton = findElementById("com.example.android_hangman_game:id/startGameButtonID")

/////////////// ფილდების შევსება და ახალ ფეიჟზე გადასვლა /////////////////
            nameEditTextField.sendKeys("Test")
            incognitoWordEditTextField.sendKeys("Test")
            startGameButton.click()
////////////// playerInitialiseActivity- ზე გადასვლა და ტესტირება  ///////////////////////////////////////////
            Thread.sleep(2000)
            val inputCharFieldEditText = findElementById("com.example.android_hangman_game:id/EditTextID")
            val tryButton = findElementById("com.example.android_hangman_game:id/TryButtonID")
            val livesTextView = findElementById("com.example.android_hangman_game:id/livesTextViewID")
            "TEST".forEach {
               // if (findElementById("com.example.android_hangman_game:id/imageViewWinOrLoseID").isDisplayed){
               try{
                   assertThat(livesTextView.text, containsString("8"))
                   inputCharFieldEditText.sendKeys(it.toString())
                   tryButton.click()
               }catch (e: Exception) {
                   e.printStackTrace()
               }
                //}
            }
            viewIsDisplayedAssert(findElementById("com.example.android_hangman_game:id/imageViewWinOrLoseID"))
//          assertThat(livesTextView.text, endsWith("8"))

            "qwryuiopadfghjklzxcvbnm".forEach {
                try{
                inputCharFieldEditText.sendKeys(it.toString())
                tryButton.click()
                }catch (e: Exception) {  e.printStackTrace()
                }
            }
            assertThat(livesTextView.text, endsWith("0"))


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