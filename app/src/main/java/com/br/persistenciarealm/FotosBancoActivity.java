package com.br.persistenciarealm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class FotosBancoActivity extends AppCompatActivity {

    private ImageView imagem;
    private Bitmap imageBitmap;
    private final int TIRAR_FOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_banco);

        Button tirarFoto = (Button) findViewById(R.id.btnTirarFoto);
        Button salvar = (Button) findViewById(R.id.btnSalvar);
        Button pesquisar = (Button) findViewById(R.id.btnPesquisar);
        imagem = (ImageView) findViewById(R.id.ivImagemSalva);
        final EditText nome = (EditText) findViewById(R.id.edNomeFoto);

        tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, TIRAR_FOTO);
                }
            }
        });

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                Contato contato = realm.where(Contato.class).equalTo("nome", nome.getText().toString()).findFirst();
                if (contato != null) {
                    byte[] outImage = contato.getFoto();
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                    Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                    imagem.setImageBitmap(imageBitmap);
                }
                realm.close();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte imagemBytes[] = stream.toByteArray();
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                Contato contato = new Contato();
                contato.setNome(nome.getText().toString());
                contato.setFoto(imagemBytes);
                realm.copyToRealm(contato);
                realm.commitTransaction();
                realm.close();
                Toast.makeText(getApplicationContext(), "Foto armazenada com sucesso", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }
    }
}
