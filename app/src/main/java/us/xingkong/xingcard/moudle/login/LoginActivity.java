package us.xingkong.xingcard.moudle.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.base.BaseActivity;
import us.xingkong.xingcard.moudle.main.MainActivity;

import static us.xingkong.xingcard.base.Constants.STAR_ID;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.edittext)
    EditText mEditText;
    @BindView(R.id.button_login)
    Button mLoginButton;

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void prepareData() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initEvent() {
    }

    @OnClick(R.id.button_login)
    public void onClick(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(STAR_ID, mEditText.getText().toString());
        startActivity(intent);
    }
}
