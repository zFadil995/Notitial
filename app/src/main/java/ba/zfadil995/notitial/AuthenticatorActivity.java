package ba.zfadil995.notitial;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AuthenticatorActivity extends AppCompatActivity {

    public static final String ACCOUNT_TYPE = "student";
    public static final String AUTH_TOKEN_TYPE = "full_access";
    public static final String IS_ADDING_NEW_ACCOUNT = "is_adding_new_account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenicator);

        APIClient.Instance.InitAPIClient(this);
    }

    private void createAccount(String studentID, String password, String authToken) {
        Account account = new Account(studentID, ACCOUNT_TYPE);

        AccountManager am = AccountManager.get(this);
        am.addAccountExplicitly(account, password, null);
        am.setAuthToken(account, AUTH_TOKEN_TYPE, authToken);

        finish();
    }

    public void LogIn(View view) {
        try {
            findViewById(R.id.studentIDField).setEnabled(false);
            findViewById(R.id.passwordField).setEnabled(false);

            new LogIn().execute();
        } finally {
            findViewById(R.id.studentIDField).setEnabled(true);
            findViewById(R.id.passwordField).setEnabled(true);
        }
    }

    public class LogIn extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            final String studentID = ((EditText) findViewById(R.id.studentIDField)).getText().toString();
            final String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();

            String authToken = APIClient.Instance.LogIn(studentID, password);

            createAccount(studentID, password, authToken);
            return null;
        }
    }
}
