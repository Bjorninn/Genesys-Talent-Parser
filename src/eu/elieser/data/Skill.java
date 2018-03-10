package eu.elieser.data;

public enum Skill
{
    Alchemy("Alchemy"),
    Astrocartography("Astrocartography"),
    Athletics("Athletics"),
    Computers("Computers"),
    Cool("Cool"),
    Coordination("Coordination"),
    Discipline("Discipline"),
    Driving("Driving"),
    Mechanics("Mechanics"),
    Medicine("Medicine"),
    Operating("Operating"),
    Perception("Perception"),
    Piloting("Piloting"),
    Resilience("Resilience"),
    Riding("Riding"),
    Skulduggery("Skulduggery"),
    Stealth("Stealth"),
    Streetwise("Streetwise"),
    Survival("Survival"),
    Vigilance("Vigilance"),
    Arcana("Arcana"),
    Divine("Divine"),
    Primal("Primal"),
    Brawl("Brawl"),
    Gunnery("Gunnery"),
    Melee("Melee"),
    Melee_Heavy("Melee (Heavy)"),
    Melee_Light("Melee (Light)"),
    Ranged("Ranged"),
    Ranged_Heavy("Ranged (Heavy)"),
    Ranged_Light("Ranged (Light)"),
    Charm("Charm"),
    Coercion("Coercion"),
    Deception("Deception"),
    Leadership("Leadership"),
    Negotiation("Negotiation"),
    Knowledge("Knowledge");


    public String value;

    Skill(String value)
    {
        this.value = value;
    }
}
