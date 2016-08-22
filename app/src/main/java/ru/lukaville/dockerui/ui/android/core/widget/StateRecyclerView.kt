package ru.lukaville.dockerui.ui.android.core.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import ru.lukaville.dockerui.ui.DataState

class StateRecyclerView : RecyclerView {

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mProgressView: View? = null

    private var mCurrentState: DataState<*> = DataState.Progress<Unit>()

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    private fun setCurrentViewVisibility(visibilityState: Int) {
        val view = when (mCurrentState) {
            is DataState.Empty -> mEmptyView
            is DataState.Error -> mErrorView
            is DataState.Progress -> mProgressView
            is DataState.Content -> null
        }

        if (view != null) {
            view.visibility = visibilityState
        }
    }

    private fun updateState(newState: DataState<*>) {
        setCurrentViewVisibility(View.GONE)
        mCurrentState = newState
        setCurrentViewVisibility(View.VISIBLE)
    }

    fun setState(newState: DataState<*>) {
        if (newState !== mCurrentState) {
            updateState(newState)
        }
    }

    fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
    }

    fun setErrorView(errorView: View) {
        mErrorView = errorView
    }

    fun setProgressView(progressView: View) {
        mProgressView = progressView
    }
}
