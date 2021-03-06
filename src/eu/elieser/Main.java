package eu.elieser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.elieser.data.Talents;
import eu.elieser.parsing.CsvParser;
import eu.elieser.reader.ReadWriteTextFileJDK7;

import java.util.List;

public class Main
{

    public static void main(String[] args)
    {
        ReadWriteTextFileJDK7 reader = new ReadWriteTextFileJDK7();
        List<String> textFile = reader.readTextFile("data/Genesys Talents Expanded 4_2.tsv");

        CsvParser parser = new CsvParser();
        Talents talents = parser.Parse(textFile);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(talents, Talents.class);

        reader.write(json, "data/genesys-talents");
    }
}
