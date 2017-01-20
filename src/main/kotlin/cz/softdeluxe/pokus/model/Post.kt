package cz.softdeluxe.pokus.model

import tornadofx.*
import javax.json.JsonObject

class Post : JsonModel {

    var userId by property<Int>()
    var id by property<Int>()
    var title by property<String>()
    var body by property<String>()

    fun titleProperty() = getProperty(Post::title)

    fun bodyProperty() = getProperty(Post::body)

    override fun updateModel(json: JsonObject) {
        with (json) {
            id = json.int("id")
            userId = json.int("userId")
            title = json.string("title")
            body = json.string("body")
        }
    }
}

