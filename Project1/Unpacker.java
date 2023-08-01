// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Unpacker 
{
    FileOutputStream outstream = null;

    public Unpacker(String src) throws Exception
    {
        byte Header[] = new byte[100];
        int iRet = 0;
        String HeaderStr;
        String Tokens[];
        int iCount = 0;

        // System.out.println("----------------- Packer Unpacker ------------------\n");

        // System.out.println("Unpacking activity of the application is started...\n");

        // System.out.println("Enter the file name which contains the packed data: ");
        String PackFile = src;

        try
        {
            File Packobj = new File(PackFile);

            FileInputStream inobj = new FileInputStream(Packobj);

            while((iRet = inobj.read(Header, 0, 100)) > 0)

            {
                HeaderStr = new String(Header);
                // System.out.println(HeaderStr);

                Tokens = HeaderStr.split(" ");

                File newfileobj = new File(Tokens[0]);
                newfileobj.createNewFile();

                FileOutputStream outobj = new FileOutputStream(newfileobj);
                int FileSize = Integer.parseInt(Tokens[1]); 
                byte Buffer[] = new byte[FileSize];

                inobj.read(Buffer, 0, FileSize);
                outobj.write(Buffer, 0, FileSize);

                // System.out.println("File successfully extracted with name: " + Tokens[0]);
                iCount++;

                outobj.close();
            }

            // System.out.println("\n--------------Unpacking Summary--------------------------");
            // System.out.println("Total number of files extracted: "+ iCount);

            // System.out.println("\n...Thank you for using Packer Unpacker...");
            inobj.close();
        }
        catch(Exception obj)
        {
            System.out.println("Exception occured: "+obj);
        }
    }

    
}