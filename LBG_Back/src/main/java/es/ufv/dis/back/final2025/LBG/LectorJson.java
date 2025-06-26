package es.ufv.dis.back.final2025.LBG;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LectorJson {
    public ArrayList<Usuario> leeFicheroJson(){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getClassLoader().getResourceAsStream("usuarios.json"))
            );
            ArrayList<Usuario> listaUsuario = new Gson().fromJson(reader, new TypeToken<ArrayList<Usuario>>() {}.getType());
            reader.close();
            return listaUsuario;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void escribeFicheroJson(List<Usuario> usuarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/main/resources/usuarios.json")) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}