/**
 * Write a description of class CyberInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CyberInfo  
{
    //Made function public so Enemies can call it. Function is also static
    public static String getAttackFact(String attackName)
    {
        if (attackName == null){
            return "A cyberattack was stopped.";
        }
        if (attackName.equals("Phishing") || attackName.equals("Crossbow")){
            return formatFact(
                "Phishing",
                "Fake messages steal information. Check senders and links.");
        }
        else if (attackName.equals("Malware") || attackName.equals("Calvary")){
            return formatFact(
                "Malware",
                "Harmful software damages devices. Install security updates.");
        }
        else if (attackName.equals("Ransomware") || attackName.equals("Knight")){
            return formatFact(
                "Ransomware",
                "It locks files for payment. Back up important files.");
        }

        return "A cyberattack was stopped.";
    }
    
    //Made function private so it can't accessed outside of this class. Function is also static
    private static String formatFact(String attackName, String fact)
    {
        return attackName + ": " + fact;
    }
}
