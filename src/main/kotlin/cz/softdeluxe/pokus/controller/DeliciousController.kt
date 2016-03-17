package cz.softdeluxe.pokus.controller

import cz.softdeluxe.pokus.model.DeliciousBookmark
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.list
import tornadofx.toModel

class DeliciousController: Controller() {

    val api: Rest by inject()

    init {
        api.baseURI = "http://feeds.delicious.com/v2/json/"
    }

    fun recentBookmarks(): ObservableList<DeliciousBookmark> = api.get("recent").list().toModel()
}

