package com.br.persistenciarealm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class EditLivroActivity extends AppCompatActivity {

    private ImageView imagem;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    private final int TIRAR_FOTO = 3;
    private final int CAMERA = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_livro);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }

        final Realm realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        final Livro livro = realm.where(Livro.class).equalTo("id", id).findFirst();

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);

        nome.setText(livro.getNome());
        autor.setText(livro.getAutor());
        ano.setText(String.valueOf(livro.getAno()));

        Button alterar = (Button) findViewById(R.id.btnEditLivro);
        alterar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realm.beginTransaction();
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno(Integer.parseInt(ano.getText().toString()));
                realm.copyToRealm(livro);
                realm.commitTransaction();

                Toast.makeText(getBaseContext(), "Livro alterado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        Button remover = (Button) findViewById(R.id.btnDeleteLivro);
        remover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realm.beginTransaction();
                livro.deleteFromRealm();
                realm.commitTransaction();

                Toast.makeText(getBaseContext(), "Livro removido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        imagem = (ImageView) findViewById(R.id.ivImagem);
        Button galeria = (Button) findViewById(R.id.btnImagem);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);
            }
        });

        Button foto = (Button) findViewById(R.id.btnFoto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    try {
                        arquivoFoto = criarArquivo();
                    } catch (IOException ex) {

                    }
                    if (arquivoFoto != null) {
                        Uri photoURI = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".provider", arquivoFoto);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAMERA);
                    }
                }
            }
        });
    }

    private File arquivoFoto = null;

    private File criarArquivo() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = new File(pasta.getPath() + File.separator + "JPG_" + timeStamp + ".jpg");
        return imagem;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK & requestCode == 1) {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(imagemGaleria);
        }

        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(arquivoFoto)));
            exibirImagem();
        }

    }

    private void exibirImagem() {
        int targetW = imagem.getWidth();
        int targetH = imagem.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);
        imagem.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
            return;
        }
    }
}
