package com.builtbroken.woodletterblocks.filegen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.builtbroken.woodletterblocks.Type;

public class BlockstateFileGen
{
    public static void main(String[] args) throws IOException
    {
        for(Type.Color color : Type.Color.values())
        {
            for(Type.Wood wood : Type.Wood.values())
            {
                for(Type.Letter letter : Type.Letter.values())
                {
                    String w = wood.name().toLowerCase();
                    String l = letter.name().toLowerCase();
                    String c = color.name().toLowerCase();
                    String name = c + "_" + w + "_" + l;
                    List<String> content = new ArrayList<>();

                    content.add("{");
                    content.add("	\"variants\": {");
                    content.add("        \"facing=south\": { \"model\": \"woodletterblocks:block/" + c + "_" + w + "_" + l + "\"},");
                    content.add("        \"facing=west\": { \"model\": \"woodletterblocks:block/" + c + "_" + w + "_" + l + "\", \"y\": 90 },");
                    content.add("        \"facing=north\": { \"model\": \"woodletterblocks:block/" + c + "_" + w + "_" + l + "\", \"y\": 180 },");
                    content.add("        \"facing=east\": { \"model\": \"woodletterblocks:block/" + c + "_" + w + "_" + l + "\", \"y\": 270 }");
                    content.add("	}");
                    content.add("}");
                    FileUtils.writeLines(new File(System.getProperty("user.home") + "/Downloads/generated/blockstates/" + name + ".json"), content);
                }
            }
        }
    }
}