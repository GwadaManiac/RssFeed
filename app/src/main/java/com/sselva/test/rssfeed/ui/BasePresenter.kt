package com.sselva.test.rssfeed.ui

import io.reactivex.disposables.CompositeDisposable

class BasePresenter {

    interface MvpView

    interface Presenter<V : MvpView> {

        fun attachView(mvpView: V?)

        fun detachView()
    }

    open class BasePresenter<T : MvpView> : Presenter<T> {

        var mvpView: T? = null
        val compositeDisposable = CompositeDisposable()
        val isViewAttached = mvpView != null

        override fun attachView(mvpView: T?) {
            this.mvpView = mvpView
        }

        override fun detachView() {
            mvpView = null
            compositeDisposable.clear()
        }
    }
}
