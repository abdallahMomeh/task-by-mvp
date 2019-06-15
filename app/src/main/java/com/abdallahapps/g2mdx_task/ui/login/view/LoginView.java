package com.abdallahapps.g2mdx_task.ui.login.view;

import com.abdallahapps.g2mdx_task.model.data.dto.User;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseView;

public interface LoginView  extends BaseView {

    void onSuccessLogin(User user);

}
