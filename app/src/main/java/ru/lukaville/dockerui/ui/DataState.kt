package ru.lukaville.dockerui.ui

sealed class DataState<T> {
    class Progress<T> : DataState<T>()
    class Content<T>(val data: T) : DataState<T>()
    class Error<T>(val exception: Throwable?) : DataState<T>()
    class Empty<T> : DataState<T>()
}