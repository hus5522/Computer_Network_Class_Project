package Control;

import Former.HttpFormer;
import HTTPHeader.*;
import Helper.FileReader;

import java.io.File;

public class TestClass {
    public static void main(String[] args)
    {
        CloudServerManager server = CloudServerManager.getInstance();
        server.OperateServer();
    }
}
