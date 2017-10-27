package com.br.persistenciarealm;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.Realm;

public class AddLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_livro);

        final Realm realm = Realm.getDefaultInstance();

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);
        Button adicionar = (Button) findViewById(R.id.btnAddLivro);
        Button imagens = (Button) findViewById(R.id.btnFotoBanco);

        ImageView imagem1 = (ImageView) findViewById(R.id.ivImagem1);
        ImageView imagem2 = (ImageView) findViewById(R.id.ivImagem2);

        imagem1.setImageBitmap(redimensionarFromResource(getResources(), R.drawable.praia, 300, 200));
        imagem2.setImageBitmap(redimensionarFromResource(getResources(), R.drawable.praia, 200, 100));

        imagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLivroActivity.this, FotosBancoActivity.class);
                startActivity(intent);
            }
        });

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livro livro = new Livro();

                int proximoId = 1;
                if (realm.where(Livro.class).max("id") != null)
                    proximoId = realm.where(Livro.class).max("id").intValue() + 1;

                livro.setId(proximoId);
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno(Integer.parseInt(ano.getText().toString()));

                realm.beginTransaction();
                realm.copyToRealm(livro);
                realm.commitTransaction();

                realm.close();

                Toast.makeText(getBaseContext(), "Livro inserido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static int calcularTamanho(BitmapFactory.Options options, int LarguraDesejada, int AlturaDesejada) {
        final int altura = options.outHeight;
        final int largura = options.outWidth;
        int fator = 1;
        if (altura > AlturaDesejada || largura > LarguraDesejada) {
            final int metadeAltura = altura / 2;
            final int metadeLargura = largura / 2;
            while ((metadeAltura / fator) > AlturaDesejada && (metadeLargura / fator) > LarguraDesejada) {
                fator *= 2;
            }
        }
        return fator;
    }

    public static Bitmap redimensionarFromResource(Resources res, int resId, int larguraDesejada, int alturaDesejada) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calcularTamanho(options, larguraDesejada, alturaDesejada);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
