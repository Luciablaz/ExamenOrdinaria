package es.ufv.dis.back.final2025.LBG;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
}