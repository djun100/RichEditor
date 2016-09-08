package jp.wasabeef.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.OutputStream;

import jp.wasabeef.richeditor.RichEditor;

public class MainActivity extends Activity {

  private RichEditor mEditor;
  private TextView mPreview;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEditor = (RichEditor) findViewById(R.id.editor);
    mEditor.setEditorHeight(200);
    mEditor.setEditorFontSize(22);
    mEditor.setEditorFontColor(Color.RED);
    //mEditor.setEditorBackgroundColor(Color.BLUE);
    //mEditor.setBackgroundColor(Color.BLUE);
    //mEditor.setBackgroundResource(R.drawable.bg);
    mEditor.setPadding(10, 10, 10, 10);
    //    mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
    mEditor.setPlaceholder("Insert text here...");

    mPreview = (TextView) findViewById(R.id.preview);
    mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
      @Override public void onTextChange(String text) {
        mPreview.setText(text);
      }
    });
//    mEditor.addJavascriptInterface(new runJavaScript(), "myjs");

    findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.undo();
      }
    });

    findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.redo();
      }
    });

    findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setBold();
      }
    });

    findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setItalic();
      }
    });

    findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setSubscript();
      }
    });

    findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setSuperscript();
      }
    });

    findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setStrikeThrough();
      }
    });

    findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setUnderline();
      }
    });

    findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(1);
      }
    });

    findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(2);
      }
    });

    findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(3);
      }
    });

    findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(4);
      }
    });

    findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(5);
      }
    });

    findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setHeading(6);
      }
    });

    findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
      private boolean isChanged;

      @Override public void onClick(View v) {
        mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
        isChanged = !isChanged;
      }
    });

    findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
      private boolean isChanged;

      @Override public void onClick(View v) {
        mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
        isChanged = !isChanged;
      }
    });

    findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setIndent();
      }
    });

    findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setOutdent();
      }
    });

    findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setAlignLeft();
      }
    });

    findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setAlignCenter();
      }
    });

    findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setAlignRight();
      }
    });

    findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.setBlockquote();
      }
    });

    findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
            "dachshund");
      }
    });

    findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
      }
    });
    findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mEditor.insertTodo();
      }
    });
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mEditor != null) {
      mEditor.pauseTimers();
      mEditor.onHide();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mEditor != null) {
      mEditor.resumeTimers();
      mEditor.onShow();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mEditor != null) {
      mEditor.onDestroy();
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK)
    {
      // Do Code here
    }
    else if(keyCode == KeyEvent.KEYCODE_0)
    {
      Log.e("tag","KEYCODE_0");
    }
    else if(keyCode == KeyEvent.KEYCODE_ENTER)
    {
      Log.e("tag","KEYCODE_ENTER");
    }
    return super.onKeyDown(keyCode, event); }

  @Override
  public boolean dispatchKeyEvent(KeyEvent event) {
    if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
      Toast.makeText(MainActivity.this,"enter",Toast.LENGTH_SHORT).show();
/*
            */
/*隐藏软键盘*//*

      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if(inputMethodManager.isActive()){
        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
      }

      edittext.setText("success");
      webview.loadUrl(URL);
*/
      return true;
    }
    return super.dispatchKeyEvent(event);
  }

  // The Java object that is bound runs in another thread and not in the
  // thread that it was constructed in.文档的一句话！
  final class runJavaScript {// 这个Java 对象是绑定在另一个线程里的，
    @JavascriptInterface
    public void runOnAndroidJavaScript() {

      Log.e("tag","删除拦截");
      execShellCmd("input keyevent 67");
      Runnable runnable = new Runnable() {
        public void run() {
          mEditor.load("javascript:setDelFlag()",null);
        }
      };
//      ((Activity)mContext).runOnUiThread(runnable);
      new Handler(Looper.getMainLooper()).postDelayed(runnable,1000);
    }
    @JavascriptInterface
    public void delete_img(String obj){
      Log.e("tag","obj:");
      Log.e("tag",obj);
    }
  }

  private void execShellCmd(String cmd) {

    try {
      // 申请获取root权限，这一步很重要，不然会没有作用
      Process process = Runtime.getRuntime().exec("sh");
      // 获取输出流
      OutputStream outputStream = process.getOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(
              outputStream);
      dataOutputStream.writeBytes(cmd);
      dataOutputStream.flush();
      dataOutputStream.close();
      outputStream.close();
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}
