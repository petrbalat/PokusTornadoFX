package cz.softdeluxe.pokus.controller

import cz.softdeluxe.pokus.model.Post
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

class DeliciousController: Controller() {

    val api: Rest by inject()

    init {
        api.baseURI = "https://jsonplaceholder.typicode.com/"
    }

    fun recentBookmarks(): ObservableList<Post> = api.get("posts").list().toModel()
}

