package com.example.githubtrendings.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.githubtrendings.R;
import com.example.githubtrendings.databinding.ActivitySettingsBinding;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SharedPreferences prefs;

    // Flag pour éviter que le spinner déclenche un changement au chargement initial
    private boolean isSpinnerReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.settings_title); // ← titre toujours présent
        }

        prefs = getSharedPreferences("github_prefs", MODE_PRIVATE);

        loadSettings();
        setupListeners();
    }

    private void loadSettings() {
        // Dark mode
        boolean darkMode = prefs.getBoolean("dark_mode", false);
        binding.switchDarkMode.setChecked(darkMode);

        // Notifications
        boolean notifs = prefs.getBoolean("notifications", false);
        binding.switchNotifications.setChecked(notifs);

        // Langue
        String lang = prefs.getString("app_language", "Français");
        String[] langs = getResources().getStringArray(R.array.app_languages);
        for (int i = 0; i < langs.length; i++) {
            if (langs[i].equals(lang)) {
                binding.spinnerLanguage.setSelection(i);
                break;
            }
        }

        // Version de l'app
        try {
            String version = getPackageManager()
                    .getPackageInfo(getPackageName(), 0).versionName;
            binding.tvVersion.setText("v" + version);
        } catch (Exception e) {
            binding.tvVersion.setText("v1.0");
        }
    }

    private void setupListeners() {

        // ── Dark Mode ──────────────────────────────────────────────────────────
        binding.switchDarkMode.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.edit().putBoolean("dark_mode", isChecked).apply();

            AppCompatDelegate.setDefaultNightMode(
                    isChecked
                            ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );

            // Recrée l'Activity pour appliquer le thème immédiatement
            // et évite que le titre disparaisse
            recreate();
        });

        // ── Notifications ──────────────────────────────────────────────────────
        binding.switchNotifications.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.edit().putBoolean("notifications", isChecked).apply();
            String msg = isChecked ? "Notifications activées" : "Notifications désactivées";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        // ── Langue ─────────────────────────────────────────────────────────────
        binding.spinnerLanguage.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(android.widget.AdapterView<?> parent,
                                               android.view.View view, int position, long id) {
                        if (!isSpinnerReady) {
                            // Premier appel au chargement → on l'ignore
                            isSpinnerReady = true;
                            return;
                        }

                        String selected = parent.getItemAtPosition(position).toString();
                        prefs.edit().putString("app_language", selected).apply();

                        // Applique la langue choisie
                        applyLocale(selected);
                    }

                    @Override
                    public void onNothingSelected(android.widget.AdapterView<?> parent) {}
                });

        // Active le flag après que le spinner est initialisé
        binding.spinnerLanguage.post(() -> isSpinnerReady = true);
    }

    /**
     * Change la locale de l'app puis recrée toutes les activités
     * pour que la langue soit appliquée partout.
     */
    private void applyLocale(String languageDisplay) {
        String langCode;
        if (languageDisplay.equals("English")) {
            langCode = "en";
        } else {
            langCode = "fr"; // Français par défaut
        }

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Recrée l'Activity pour appliquer la nouvelle langue
        recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}