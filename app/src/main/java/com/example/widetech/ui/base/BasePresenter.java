package com.example.widetech.ui.base;

/**
 * @author dacastano
 * @version 1.0
 * @since 07/12/2017
 */
public class BasePresenter<T extends BaseView> {
    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }
}
