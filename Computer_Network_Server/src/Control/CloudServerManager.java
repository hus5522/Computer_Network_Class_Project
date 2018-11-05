package Control;

import java.net.InetAddress;

public class CloudServerManager {

    private static CloudServerManager instance;
    private CloudServerManager(){}

    public static CloudServerManager getInstance(){
        if(instance==null)
            instance=new CloudServerManager();
        return instance;
    }


}
