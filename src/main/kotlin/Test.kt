import DisplayedViews.viewIsDisplayedAssert
import io.appium.java_client.android.Activity
import io.appium.java_client.android.AndroidDriver
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.openqa.selenium.WebElement

object Test {
    @JvmStatic
    fun AndroidDriver<WebElement>.inputtingCharsTest(){
        apply {
            val nameEditTextField = findElementById("com.example.android_hangman_game:id/playerNameEditTextID")
            val incognitoWordEditTextField =
                findElementById("com.example.android_hangman_game:id/incognitoWordEditTextID")
            val startGameButton = findElementById("com.example.android_hangman_game:id/startGameButtonID")

/////////////// ფილდების შევსება და ახალ ფეიჟზე გადასვლა /////////////////
            nameEditTextField.sendKeys("TESO")
            incognitoWordEditTextField.sendKeys("TESO")
            startGameButton.click()
////////////// playerInitialiseActivity- ზე გადასვლა და ტესტირება  ///////////////////////////////////////////
            Thread.sleep(2000)
            val inputCharFieldEditText = findElementById("com.example.android_hangman_game:id/EditTextID")
            val tryButton = findElementById("com.example.android_hangman_game:id/TryButtonID")
            val livesTextView = findElementById("com.example.android_hangman_game:id/livesTextViewID")

            "TESO".forEach {
                println(">$it<")
                // if (findElementById("com.example.android_hangman_game:id/imageViewWinOrLoseID").isDisplayed){
                try{
                    assertThat(livesTextView.text, Matchers.containsString("8"))
                    inputCharFieldEditText.sendKeys(it.toString())
                    tryButton.click()
                }catch (e: Exception) {
                    e.printStackTrace()
                }
                //}
            }
            navigate().back()
           assertThat(livesTextView.text, Matchers.containsString("8"))

            "qwryuiopadfghjklzxcvbnm".forEach {
                try{
                    inputCharFieldEditText.sendKeys(it.toString())
                    tryButton.click()
                }catch (e: Exception) {  e.printStackTrace()
                }
            }


            viewIsDisplayedAssert(findElementById("com.example.android_hangman_game:id/imageViewWinOrLoseID"))
//          assertThat(livesTextView.text, endsWith("8"))


           assertThat(livesTextView.text, CoreMatchers.endsWith("0"))


        }
    }
}