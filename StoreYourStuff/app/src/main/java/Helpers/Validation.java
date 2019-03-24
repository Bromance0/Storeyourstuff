package Helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class Validation {
    private Context context;


    public Validation(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText InputEditText, TextInputLayout InputLayout, String message) {
        String value = InputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            InputLayout.setError(message);
            hideKeyboardFrom(InputEditText);
            return false;
        } else {
            InputLayout.setErrorEnabled(false);
        }

        return true;
    }
public boolean isInputEditTextEmail(TextInputEditText InputEditText, TextInputLayout InputLayout, String message) {
        String value = InputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            InputLayout.setError(message);
            hideKeyboardFrom(InputEditText);
            return false;
        } else {
            InputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText InputEditText1, TextInputEditText InputEditText2, TextInputLayout InputLayout, String message) {
        String value1 = InputEditText1.getText().toString().trim();
        String value2 = InputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            InputLayout.setError(message);
            hideKeyboardFrom(InputEditText2);
            return false;
        } else {
            InputLayout.setErrorEnabled(false);
        }
        return true;
    }


    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
