package Control;

public class CloudServerManager {
    private static CloudServerManager instance = null;

    public CloudServerManager GetInstance()
    {
        if(instance == null) instance = new CloudServerManager();
        return instance;
    }

    private CloudServerManager()
    {

    }

}
