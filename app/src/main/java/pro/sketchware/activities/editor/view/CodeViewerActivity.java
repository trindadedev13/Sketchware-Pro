package pro.sketchware.activities.editor.view;

import static pro.sketchware.utility.ThemeUtils.isDarkThemeEnabled;

import android.os.Build;
import android.os.Bundle;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.besome.sketch.lib.base.BaseAppCompatActivity;

import com.google.android.material.color.MaterialColors;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;

import mod.hey.studios.util.Helper;
import mod.jbk.code.CodeEditorColorSchemes;
import mod.jbk.code.CodeEditorLanguages;

import pro.sketchware.databinding.ActivityCodeViewerBinding;

public class CodeViewerActivity extends BaseAppCompatActivity {

    public static final String SCHEME_XML = "xml";
    public static final String SCHEME_JAVA = "java";
    
    private ActivityCodeViewerBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodeViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        var code = getIntent().getStringExtra("code");
        var scheme = getIntent().getStringExtra("scheme");
        var scId = getIntent().getStringExtra("sc_id");
        binding.toolbar.setNavigationOnClickListener(Helper.getBackPressedClickListener(this));
        binding.toolbar.setSubtitle(scId);
        binding.editor.setTypefaceText(Typeface.MONOSPACE);
        binding.editor.setTextSize(14);
        binding.editor.setText(code);
        binding.editor.setEditable(false);
        binding.editor.setWordwrap(false);
        loadColorScheme(scheme);
    }

    private void loadColorScheme(final String scheme) {
        if (scheme.equals(SCHEME_XML)) {
            loadXmlScheme();
        } else {
            loadJavaScheme();
        }
        binding.editor.setColorScheme(getMaterialStyledScheme());
    }

    private void loadJavaScheme() {
        binding.editor.setEditorLanguage(new JavaLanguage());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (isDarkThemeEnabled(this)) {
                binding.editor.setColorScheme(new SchemeDarcula());
            } else {
                binding.editor.setColorScheme(new EditorColorScheme());
            }
        } else {
            binding.editor.setColorScheme(new EditorColorScheme());
        }
    }

    private void loadXmlScheme() {
        binding.editor.setEditorLanguage(CodeEditorLanguages.loadTextMateLanguage(CodeEditorLanguages.SCOPE_NAME_XML));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (isDarkThemeEnabled(this)) {
                binding.editor.setColorScheme(CodeEditorColorSchemes.loadTextMateColorScheme(CodeEditorColorSchemes.THEME_DRACULA));
            } else {
                binding.editor.setColorScheme(CodeEditorColorSchemes.loadTextMateColorScheme(CodeEditorColorSchemes.THEME_GITHUB));
            }
        } else {
            binding.editor.setColorScheme(CodeEditorColorSchemes.loadTextMateColorScheme(CodeEditorColorSchemes.THEME_GITHUB));
        }
    }

    @NonNull
    private EditorColorScheme getMaterialStyledScheme() {
        var scheme = binding.editor.getColorScheme();
        var primary = MaterialColors.getColor(binding.editor, com.google.android.material.R.attr.colorPrimary);
        var surface = MaterialColors.getColor(binding.editor, com.google.android.material.R.attr.colorSurface);
        var onSurface = MaterialColors.getColor(binding.editor, com.google.android.material.R.attr.colorOnSurface);
        scheme.setColor(EditorColorScheme.KEYWORD, primary);
        scheme.setColor(EditorColorScheme.FUNCTION_NAME, primary);
        scheme.setColor(EditorColorScheme.WHOLE_BACKGROUND, surface);
        scheme.setColor(EditorColorScheme.CURRENT_LINE, surface);
        scheme.setColor(EditorColorScheme.LINE_NUMBER_PANEL, surface);
        scheme.setColor(EditorColorScheme.LINE_NUMBER_BACKGROUND, surface);
        scheme.setColor(EditorColorScheme.TEXT_NORMAL, onSurface);
        return scheme;
    }
}
