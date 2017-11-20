package ba.zfadil995.notitial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        APIClient.Instance.InitAPIClient(this);
    }

    public class LogIn extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            APIClient.Instance.LogIn("301014015", "aFewgoodmen");
            return null;
        }
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
}
