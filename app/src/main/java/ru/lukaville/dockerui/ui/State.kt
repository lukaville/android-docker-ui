package ru.lukaville.dockerui.ui

sealed class State {
    class Progress : State()
    class Content<out T>(val data: T) : State()
    class Error(val exception: Exception) : State()
    class Empty : State()
}