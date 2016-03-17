package cz.softdeluxe.pokus.lib

import javafx.beans.binding.Binding
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventType
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.stage.Window
import javafx.stage.WindowEvent
import rx.Observable
import rx.javafx.sources.Change
import rx.observables.JavaFxObservable
import rx.schedulers.JavaFxScheduler
import rx.schedulers.Schedulers
import rx.subscribers.JavaFxSubscriber

/**
 * Created by balat on 17.3.2016.
 */
fun <T> Observable<T>.observeOnIo(): Observable<T> = observeOn(Schedulers.io())
fun <T> Observable<T>.observeOnFx(): Observable<T> = observeOn(JavaFxScheduler.getInstance())
fun <T> Observable<T>.subscribeOnFx(): Observable<T> = subscribeOn(JavaFxScheduler.getInstance())

fun <T> Observable<T>.toBinding(): Binding<T> = JavaFxSubscriber.toBinding(this)
fun <T> Observable<T>.toBinding(errorHandler: (Throwable) -> Unit): javafx.beans.Observable = JavaFxSubscriber.toBinding(this, errorHandler)
fun <T> ObservableValue<T>.toObservable(): Observable<T> = JavaFxObservable.fromObservableValue(this)
fun <T> ObservableValue<T>.toObservableChanges(): Observable<Change<T>> = JavaFxObservable.fromObservableValueChanges(this)


fun <T : Event> Node.toNodeEvents(eventType: EventType<T>) = JavaFxObservable.fromNodeEvents(this, eventType)

fun Button.actionEvents(): Observable<ActionEvent> = JavaFxObservable.fromActionEvents(this)
fun ContextMenu.actionEvents(): Observable<ActionEvent> = JavaFxObservable.fromActionEvents(this)
fun MenuItem.actionEvents(): Observable<ActionEvent> = JavaFxObservable.fromActionEvents(this)
fun Node.actionEvents(): Observable<ActionEvent> = JavaFxObservable.fromActionEvents(this)

fun <T : Event> Scene.eventsToObservable(eventType: EventType<T>): Observable<T> = JavaFxObservable.fromSceneEvents(this, eventType)
fun <T : WindowEvent> Window.eventsToObservable(eventType: EventType<T>): Observable<T> = JavaFxObservable.fromWindowEvents(this, eventType)