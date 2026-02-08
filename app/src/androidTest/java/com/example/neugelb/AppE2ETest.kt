package com.example.neugelb

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class AppE2ETest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appStartAndClickOnLastVisibleAndVerify() {
        composeTestRule.waitUntil(5000L){
            composeTestRule.onAllNodesWithTag("movie").onFirst().isDisplayed()
        }
        composeTestRule.onAllNodesWithTag("movie").onLast().performClick()
        composeTestRule.waitUntil(5000L){
            composeTestRule.onNodeWithTag("movie_title").isDisplayed()
        }
    }

    @Test
    fun appStartAndClickOnLastVisibleAndVerifyAndReturn() {
        composeTestRule.waitUntil(5000L){
            composeTestRule.onAllNodesWithTag("movie").onFirst().isDisplayed()
        }
        composeTestRule.onAllNodesWithTag("movie").onLast().performClick()
        composeTestRule.onNodeWithContentDescription("nav_back_icon").performClick()
        composeTestRule.onAllNodesWithTag("movie").onFirst().assertIsDisplayed()
    }

    @Test
    fun appStartAndClickOnLastVisibleAndVerifyAndReturnGoToSearch() {
        composeTestRule.waitUntil(5000L){
            composeTestRule.onAllNodesWithTag("movie").onFirst().isDisplayed()
        }
        composeTestRule.onAllNodesWithTag("movie").onLast().performClick()
        composeTestRule.waitUntil(5000L){
            composeTestRule.onNodeWithTag("movie_title").isDisplayed()
        }
        composeTestRule.onNodeWithContentDescription("nav_back_icon").performClick()
        composeTestRule.waitUntil(5000L){
            composeTestRule.onAllNodesWithTag("movie").onFirst().isDisplayed()
        }
        composeTestRule.onAllNodesWithTag("movie").onFirst().assertIsDisplayed()
        composeTestRule.onNodeWithTag("search_icon").performClick()
        composeTestRule.onNodeWithTag("search_field").performTextInput(listOf("Spider-man", "Superman", "Wolverine").random())
        composeTestRule.waitUntil(5000L){
            composeTestRule.onAllNodesWithTag("movie_name").onFirst().isDisplayed()
        }
        composeTestRule.onAllNodesWithTag("movie_name").onLast().performClick()
        composeTestRule.waitUntil(5000L){
            composeTestRule.onNodeWithTag("movie_title").isDisplayed()
        }
    }
}