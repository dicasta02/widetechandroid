package com.example.widetech;


import com.example.widetech.data.dataManager.RepositoryDataManager;
import com.example.widetech.data.network.RetrofitService;
import com.example.widetech.ui.login.LoginPresenter;
import com.example.widetech.ui.login.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;


/**
 * @author sarango
 * @version 1.0
 * @since 07/03/2018
 */

public class LoginPresenterTest {

    @Mock
    LoginView loginView;
    @InjectMocks
    RepositoryDataManager repositoryDataManager;

    @Mock
    RetrofitService retrofitService;

    private LoginPresenter presenter;

    @Before
    public void prepareDataForTest() {
        MockitoAnnotations.initMocks(LoginPresenterTest.this);
        presenter = new LoginPresenter(repositoryDataManager);
        presenter.attachView(loginView);

    }

    @Test
    public void methodValidateDataWithParameterPhoneEmptyShouldReturnMessageError() {
        presenter.validateData("", "");
        Mockito.verify(loginView).setStringMessage(R.string.app_name);
    }

    @Test
    public void methodValidateDataWithParameterPhoneEmptyAndParameterConfirmShouldReturnMessageError() {
        presenter.validateData("", "154545");
        Mockito.verify(loginView).setStringMessage(R.string.app_name);
    }

    @Test
    public void methodValidateDataWithParameterConfirmEmptyShouldReturnMessageError() {
        presenter.validateData("dicasta02@gmail.com", "");
        Mockito.verify(loginView).setStringMessage(R.string.app_name);
    }

    @Test
    public void methodValidateDataWithDirefentsParametersShouldReturnMessageError() {
        presenter.validateData("dicas", "1215154");
        Mockito.verify(loginView).setStringMessage(R.string.app_name);
    }

    @Test
    public void methodForValidateServiceInDoLoginMethod() {

    }
}
