package ba.zfadil995.notitial;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccountManager accountManager = AccountManager.get(this);

        Account[] accounts = accountManager.getAccountsByType(getResources().getString(R.string.account_type));

        if (accounts.length > 0)
            Toast.makeText(this, "TOAST", Toast.LENGTH_LONG).show();
        else
            startActivity(new Intent(this, AuthenticatorActivity.class));

        //finish();
    }

}
