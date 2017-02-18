package cz.softdeluxe.pokus.view

import cz.softdeluxe.pokus.controller.DeliciousController
import cz.softdeluxe.pokus.model.Post
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.scene.web.WebView
import javafx.stage.Stage
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.javafx.JavaFx
import tornadofx.*
import java.util.concurrent.TimeUnit

class MainView : View() {

    override val root: BorderPane by fxml()

    val table: TableView<Post> by fxid()

    val controller: DeliciousController by inject()

    val testBtn: Button by fxid()
    val testLbl: Label by fxid()

    init {
        title = messages["title"]

        with (table) {
            // Create table columns and bind to the data model
            column(messages["id"], Post::title).prefWidth = 50.0
            column(messages["userId"], Post::userId).prefWidth = 50.0
            column(messages["title"], Post::title).prefWidth = 200.0
            column(messages["body"], Post::body).prefWidth = 300.0

            // Handle double click on row
            onUserSelect {
                browse(it)
            }

            // Load data from the controller
            asyncItems {
                controller.recentBookmarks()
            }
        }

        testBtn.setOnAction {
            async(JavaFx) {
                title = "start"

                delay(2, TimeUnit.SECONDS)

                title = "end"
            }
        }
    }

    /**
     * Open the selected bookmark in a new browser window
     */
    private fun browse(post: Post) = Stage().apply {
        log.info { "Browsing ${post.id}..." }

        val webView = WebView().apply {
            engine.load(post.id.toString())//FIXME
        }
        scene = Scene(webView)
        title = post.title
        show()
    }
}