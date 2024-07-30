package fpoly.ph45160.ph45160_and103_assignment.AccountActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fpoly.ph45160.ph45160_and103_assignment.R;
import fpoly.ph45160.ph45160_and103_assignment.databinding.ActivityRegisterBinding;

public class ActivityRegister extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityRegister.this, ActivitySignIn.class);
            startActivity(intent);
        });

        binding.btnRegister.setOnClickListener(v -> {
            String userName = binding.edtName.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPass.getText().toString().trim();
            String rePass = binding.edtRePass.getText().toString().trim();

            boolean error = false;

            if (userName.isEmpty()) {
                binding.edtName.setError("Please enter your User Name!");
                error = true;
            }
            if (email.isEmpty()) {
                binding.edtEmail.setError("Please enter your Email!");
                error = true;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.setError("Wrong email format!");
                error = true;
            }
            if (password.isEmpty()) {
                binding.edtPass.setError("Please enter your Password!");
                error = true;
            } else if (password.length() < 6) {
                binding.edtPass.setError("Password must be at least 6 characters!");
                error = true;
            }
            if (rePass.isEmpty()) {
                binding.edtRePass.setError("Please confirm your Password!");
                error = true;
            } else if (!rePass.equals(password)) {
                binding.edtRePass.setError("Password not match, please enter again!");
                error = true;
            }

            if (!error) {
                // Proceed with registration
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Registration successful, get the current user
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Optionally, you can update user profile or handle other logic here

                                    // Show a success message and redirect to Sign In
                                    Toast.makeText(ActivityRegister.this, "Đăng kí tài khoản thành công.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivityRegister.this, ActivitySignIn.class);
                                    startActivity(intent);
                                    finish();  // Close the current activity
                                } else {
                                    // If registration fails, display a message to the user.
                                    Toast.makeText(ActivityRegister.this, "Đăng kí tài khoản thất bại: " + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
