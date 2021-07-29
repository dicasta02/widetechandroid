package com.example.widetech.ui.menu.more;


import com.example.widetech.ui.adapter.MenuAdapter;
import com.example.widetech.ui.base.BaseView;

/**
 * @author dacastano
 * @version 1.0
 * @since 07/12/2017
 */
public interface MoreOptionsView extends BaseView {

    void loadAdapter(MenuAdapter menuAdapter);

    void loadInfoAccount(int product);

    void nextActivity(Class nextActivity);

    void hideFieldAccount();
}
