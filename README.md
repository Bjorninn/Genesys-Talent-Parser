# Genesys-Talent-Parser
Parses the **Genesys Talents Expanded** found here:  
https://community.fantasyflightgames.com/topic/265863-genesys-talents-expanded/

This version parses **Version 4.2**

It uses a .tsv file created from the Excel version.

Great thanks to *TheSapient* and *ESP77* for creating this talent list.

### Json format

```
{
  "talents": [
    {
      "id": 0,
      "name": "Talent name",
      "tier": 1,
      "activation": "Active" | "Passive",
      "sub_activation": null | "Incidental" | "Maneuver" | "Action" | "Incidental, Out of Turn",
      "ranked": true | false,
      "description": "Talent description",
      "source": [
        "ACRB",
        "ETU"
      ],
      "from": "Talent origin name",
      "keywords": [
        "driving"
      ],
      "requirement": null | 0,
      "depriciated" : null | "vX.Y"
    }
}
```
