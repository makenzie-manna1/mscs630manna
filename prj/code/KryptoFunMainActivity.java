package edu.marist.enkryptme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MainActivity
 *
 * This class runs the entire application. It assigns the correct UI elements
 * to their respective variable names. It also calls the functions that process
 * the encryption and decryption of the input messages.
 *
 */
public class MainActivity extends AppCompatActivity {

  Button button_en, button_de;
  EditText en_edit, de_edit, shift_key;
  TextView output;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //input fields
    en_edit = findViewById(R.id.plainText);
    de_edit = findViewById(R.id.cipherText);
    shift_key = findViewById(R.id.shift_key);

    //encrypt button
    button_en = findViewById(R.id.encrypt_button);

    //decrypt button
    button_de = findViewById(R.id.decrypt_button);

    //output field
    output = findViewById(R.id.output);

    Code_Message();
    Decode_Message();
  }
  /**
   *
   * Code_Message
   *
   * This function tells the program to begin the function of encrypting the user
   * input message. It sets the function Caesar_cipher() to begin when the Encrypt
   * button is pressed.
   *
   */
  public void Code_Message() {
    button_en.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        Caesar_Cipher();
      }
    });
  }
  /**
   *
   * Caesar_Cipher
   *
   * This function performs the encryption of the user input using the shift key they
   * also provide. It reads the input from the plain text input field and stores it as
   * a string, reads the input from the shift key input field and stores it as an integer.
   * Then applies the cipher to the plain text input using the shift key.
   *
   * Parameters:
   *   p_text: the string input to be encrypted
   *   shift: the integer to be used as the shift key in the cipher
   *
   * Return Value:
   *   c_text: a string of text that has now had the cipher applied to it.
   *
   */
  private void Caesar_Cipher(){
    String p_text = en_edit.getText().toString();
    p_text = p_text.toLowerCase();
    int shift = Integer.parseInt(shift_key.getText().toString());
    String c_text = "";
    for (int i = 0; i < p_text.length(); i++) {
      if (Character.isWhitespace(p_text.charAt(i))) {
        c_text += " ";
        continue;
      }
      char c = (char) (p_text.charAt(i) + shift);
      if (c > 'z' || (c > 'z' && c < 'a')) {
        c -= 26;
      }
      c_text += c;
    }
    output.setText(c_text.toLowerCase());
  }
  /**
   *
   * Decode_Message
   *
   * This function tells the program to begin the function of decrypting the user
   * input message. It sets the function Decrypt_Caesar() to begin when the Decrypt
   * button is pressed.
   *
   */
  public void Decode_Message(){
    button_de.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        Decrypt_Caesar();
      }
    });
  }
  /**
   *
   * Decrypt_Caesar
   *
   * This function performs the decryption of the user input using the shift key they
   * also provide. It reads the input from the cipher text input field and stores it as
   * a string, reads the input from the shift key input field and stores it as an integer.
   * Then applies the decryption cipher to the cipher text input using the negated shift key.
   *
   * Parameters:
   *   c_text: the string input to be decrypted
   *   shift: the integer to be used as the negated shift key in the cipher
   *
   * Return Value:
   *   p_text: a string of text that has now been decrypted by the cipher.
   *
   */
  private void Decrypt_Caesar(){
    String c_text = de_edit.getText().toString();
    int shift = Integer.parseInt(shift_key.getText().toString());
    String p_text = "";
    for (int i = 0; i < c_text.length(); i++){
      if (Character.isWhitespace(c_text.charAt(i))) {
        p_text += " ";
        continue;
      }
      char c = (char) (c_text.charAt(i) - shift);
      if (c > 'z' || (c > 'z' && c < 'a')){
        c -= 26;
      }
      p_text += c;
    }
    output.setText(p_text.toLowerCase());
  }
}