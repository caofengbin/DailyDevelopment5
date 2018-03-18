package cfb.com.dailydevelopment5.example6.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cfb.com.dailydevelopment5.R;

/**
 * 尝试使用MVP模式
 */
public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private Button mLoginBtn;
    private Button mClear;
    private EditText mName;
    private EditText mPassword;

    // 业务逻辑接口定义
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mClear = (Button) findViewById(R.id.btn_clear);
        mName = (EditText) findViewById(R.id.et_name);
        mPassword = (EditText) findViewById(R.id.et_pass);

        mLoginBtn.setOnClickListener(this);
        mClear.setOnClickListener(this);

        loginPresenter = new LoginPresenterCompl(this);
    }

    @Override
    public void onClearText() {
        // 视图逻辑的具体实现方法
        mName.setText("");
        mPassword.setText("");
        Toast.makeText(this, "clear", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        // 视图逻辑的具体实现方法
        mLoginBtn.setEnabled(true);
        mClear.setEnabled(true);

        if (result) {
            Toast.makeText(this, "登陆成功" + code, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "登陆失败" + code, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String name = mName.getText().toString();
        String password = mPassword.getText().toString();

        switch (id) {
            case R.id.btn_login:
                loginPresenter.doLogin(name, password);
                break;
            case R.id.btn_clear:
                loginPresenter.clear();
                break;
        }
    }
}
