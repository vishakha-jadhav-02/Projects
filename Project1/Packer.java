// import java.io.BufferedReader;
import java.io.File;
// import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

class Packer
{
    FileOutputStream outstream = null;

    // String ValidExt[] = {".txt", ".java", ".c", ".cpp"};

    public Packer(String src, String Dest) throws Exception
    {
        byte Buffer[] = new byte[1024];
        int iRet = 0;
        boolean bRet;
        int PackCount = 0;   

        File fobj = new File(src);
        String Header = null;

        String PackFile = Dest;
        
        try
        {
            File Packobj = new File(PackFile);
            bRet = Packobj.createNewFile();
            if(bRet == false)
            {
                // System.out.println("Unable to create file.");
                return;
            }

            // System.out.println("\nPacked file gets successfully created...");

            FileOutputStream outobj = new FileOutputStream(Packobj);

            bRet = fobj.isDirectory();
            if(bRet == true)
            {
                File list[] = fobj.listFiles();

                // System.out.println("\nTotal number of files found in the directory are: " +list.length);

                for(int i = 0; i < list.length; i++)
                {
                    if((list[i].getName()).endsWith(".txt"))
                    {
                        Header = list[i].getName()+ " " + list[i].length();
                        for(int j = Header.length(); j < 100; j++)
                        {
                            Header = Header + " ";
                        }

                        byte bHeader[] = Header.getBytes();
                        outobj.write(bHeader, 0, bHeader.length);

                        FileInputStream inobj = new FileInputStream(list[i]);

                        // Loop to write data
                        while((iRet = inobj.read(Buffer)) != -1)
                        {
                            outobj.write(Buffer,0,iRet);
                        }

                        // System.out.println("File successfully packed with name: "+list[i].getName());
                        inobj.close();
                        PackCount++;
                    }
                }
                // System.out.println("\n---------------Packing Summary--------------------------");
                // System.out.println("Total number of files scanned: "+ list.length);
                // System.out.println("Total number of files packed: "+ PackCount);

                // System.out.println("\n...Thank you for using Packer Unpacker...");
            }
            outobj.close();
        }
        catch(Exception iobj)
        {
            System.out.println("Exception occured: "+iobj);
        }
    }
}