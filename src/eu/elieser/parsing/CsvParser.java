package eu.elieser.parsing;

import eu.elieser.Log;
import eu.elieser.data.Skill;
import eu.elieser.data.Talent;
import eu.elieser.data.Talents;

import java.util.*;

public class CsvParser
{


    public CsvParser()
    {

    }

    public Talents Parse(List<String> text)
    {
        Talents talents = new Talents();
        List<Talent> talentList = new ArrayList<>(350);

        int id = -1;

        // Basic parsing of the TSV data

        for (String line :
                text)
        {
            id++;

            if (id == 0)
            {
                continue;
            }


            String[] split = line.split("\t");

            Talent talent = new Talent();
            talent.setId(id);
            talent.setName(split[0].trim());

            String tier = split[1].replace("Tier:", "").trim();

            talent.setTier(Integer.valueOf(tier));
            talent.setActivation(split[2].replace("Activation:", "").trim());

            String ranked = split[3].replace("Ranked:", "").trim().toLowerCase();

            if (ranked.equals("no"))
            {
                talent.setRanked(false);
            }
            else if (ranked.equals("yes"))
            {
                talent.setRanked(true);
            }
            else
            {
                throw new IllegalArgumentException("Got " + split[3] + " for " + talent.getName());
            }

            talent.setDescription(split[4].trim());

            String source = split[5].replace("Source:", "").replace("Source:", "").trim();
            String[] sources = source.split(",");

            for (int i = 0; i < sources.length; i++)
            {
                sources[i] = sources[i].trim();
            }

            talent.setSource(Arrays.asList(sources));
            talent.setFrom(split[6]);

            talentList.add(talent);
        }

        // Parsing based on data

        Map<String, Talent> talentMap = new HashMap<String, Talent>();
        for (Talent t :
                talentList)
        {
            talentMap.put(t.getName(), t);
        }


        String pre = "Your character must have purchased the ";
        String post = " talent to benefit from this talent.";

        for (Talent talent :
                talentList)
        {
            // Prerequisite parsing
            if (talent.getDescription().contains("must have purchased"))
            {

                String desc = talent.getDescription().replace(pre, "");
                int index = desc.indexOf(post);
                String rname = desc.substring(0, index).trim();

                Talent rTalent = talentMap.get(rname);
                talent.setRequirement(rTalent.getId());
            }

            // double check requirement
            if (talent.getRequirement() == 0)
            {
                if (talent.getName().toLowerCase().contains("improved"))
                {
                    String name = talent.getName().replace("(Improved)", "").trim();

                    Talent rTalent = talentMap.get(name);
                    talent.setRequirement(rTalent.getId());
                }
                else if (talent.getName().toLowerCase().contains("supreme"))
                {
                    String name = talent.getName().replace("(Supreme)", "(Improved)").trim();

                    Talent rTalent = talentMap.get(name);
                    talent.setRequirement(rTalent.getId());
                }
            }

            // Adding skills to keywords

            talent.setKeywords(new ArrayList<>());

            for (Skill skill :
                    Skill.values())
            {
                if (talent.getDescription().toLowerCase().contains(skill.value.toLowerCase()))
                {
                    talent.addKeyword(skill.value.toLowerCase());
                }
            }

            // Splitting the Activation text

            if (!talent.getActivation().contains("Pass"))
            {
                String sub = talent.getActivation().replace("Active", "").replace("(", "").replace(")", "").trim();
                talent.setSubActivation(sub);
                talent.setActivation("Active");
            }

            // Checking for community created talents
            if (talent.getSource().contains("CCC"))
            {
                talent.addKeyword("community");
            }

        }


        talents.setTalents(talentList);

        return talents;
    }


}
