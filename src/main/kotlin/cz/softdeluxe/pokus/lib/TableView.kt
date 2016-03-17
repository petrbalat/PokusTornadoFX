package cz.softdeluxe.pokus.lib

import javafx.scene.control.TableView
import rx.Observable

/**
 * Created by balat on 17.3.2016.
 */

val <T> TableView<T>.rowSelections: Observable<Int>
    get() = itemSelections.map { selectionModel.selectedIndex }

/*
val <T> TableView<T>.columnSelections: Observable<Int>
    get() = selectionModel.selectedcell
*/

val <T> TableView<T>.itemSelections: Observable<T>
    get() = this.selectionModel.selectedItemProperty().toObservable()