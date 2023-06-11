package edu.fiuba.algo3.modelo.lectorJSON;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Lector{
    public static JSONArray leer(String archivo) {
        JSONParser jsonParser = new JSONParser();
        JSONArray lecturaDelJSON = null;

        try (FileReader reader = new FileReader(archivo)) {
            //diccionario medio raro
            JSONArray parser = (JSONArray) jsonParser.parse(reader);

            lecturaDelJSON = parser;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return lecturaDelJSON;
    }
}