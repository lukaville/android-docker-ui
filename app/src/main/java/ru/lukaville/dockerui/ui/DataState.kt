package ru.lukaville.dockerui.ui

sealed class DataState<T> {
    class Progress<T> : DataState<T>() {
        override fun equals(other: Any?): Boolean {
            return this.javaClass.equals(other?.javaClass)
        }

        override fun hashCode(): Int {
            return 0
        }
    }

    class Content<T>(val data: T) : DataState<T>() {
        override fun hashCode(): Int {
            return when (data) {
                null -> 0
                else -> data.hashCode()
            }
        }

        override fun equals(other: Any?): Boolean {
            if (other is Content<*> && data != null) {
                return data.equals(other.data)
            }
            return false
        }
    }

    class Error<T>(val exception: Throwable?) : DataState<T>() {
        override fun hashCode(): Int {
            return when (exception) {
                null -> 0
                else -> exception.hashCode()
            }
        }

        override fun equals(other: Any?): Boolean {
            if (other is Error<*> && exception != null) {
                return exception.equals(other.exception)
            }
            return false
        }
    }

    class Empty<T> : DataState<T>() {
        override fun equals(other: Any?): Boolean {
            return this.javaClass.equals(other?.javaClass)
        }

        override fun hashCode(): Int {
            return 0
        }
    }
}