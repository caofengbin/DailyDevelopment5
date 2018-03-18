package cfb.com.dailydevelopment5.example6.mvp;

/**
 * Presenter层的具体实现类
 * Created by fengbincao on 2018/3/18.
 */

class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView loginView;
    private User user;

    LoginPresenterCompl(ILoginView view) {
        loginView = view;
        user = new User("张三", "123456");
    }

    @Override
    public void clear() {
        loginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        boolean result;
        int code;

        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
            result = true;
            code = 1;
        } else {
            result = false;
            code = 0;
        }

        loginView.onLoginResult(result, code);
    }
}
