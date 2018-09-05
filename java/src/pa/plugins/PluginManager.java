package pa.plugins;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class PluginManager {

    private static PluginManager INSTANCE = new PluginManager();

    public static PluginManager getInstance() { return INSTANCE; }

    private List<CameraPlugins> cameraPluginsList = new ArrayList<>();

    private List<Map2DPlugins> map2DPluginsList = new ArrayList<>();

    private String plugin_list = "src/pa/plugins/plugins_installed/plugin-list.txt";

    private String plugin_dir = "src/pa/plugins/plugins_installed";

    public boolean isActive(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(plugin_list));
        String line;
        while ( (line = reader.readLine()) != null ) {
            String[] array = line.split(":");
            if(array[0].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean uninstallPlugin( String fileName ) {
        // Supprimer le jar du repertoire
        return true;
    }

    public boolean activePlugin( String name ) throws IOException {
        // Ecrire le plugin dans le fichier
        if( !isActive(name) ) {
            try {
                PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(plugin_list)));
                file.println(name + ":" + plugin_dir + "/" + name + ".jar");
                file.close();

            } catch( IOException e) {
                e.printStackTrace();

            }
        } else {
            return false;
        }

        return true;
    }

    public boolean saveJarFile(URL path, String name) {
        String fileOutput = plugin_dir + path.getFile().substring(path.getFile().lastIndexOf("/"));
        try {
            PrintWriter writer = new PrintWriter(fileOutput);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try (InputStream sourceFile = new FileInputStream(path.getFile());
             OutputStream destinationFile = new FileOutputStream(fileOutput)) {
            // Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1){
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removePluginJarFile(URL path) {

        File file = new File(plugin_dir + path.getFile().substring(path.getFile().lastIndexOf("/")));

        return file.delete();
    }

    public boolean disablePlugin( String pluginName ) {
        // Supprimer le plugin dans le fichier
        return true;
    }


    public List<CameraPlugins> getCameraPluginsList() {
        return cameraPluginsList;
    }

    public void setCameraPluginsList(List<CameraPlugins> cameraPluginsList) {
        this.cameraPluginsList = cameraPluginsList;
    }

    public List<Map2DPlugins> getMap2DPluginsList() {
        return map2DPluginsList;
    }


    /**
     * Récupération du chemin du plugin
     * @param line
     * @return String
     */
    public String getPluginPath(String line) {
        String[] array = line.split(":");
        return array[1];
    }

    /**
     * Récupération du nom du plugin
     * @param line
     * @return String
     */
    public String getPluginName(String line) {
        String[] array = line.split(":");
        return array[0];
    }

    public void setMap2DPluginsList(List<Map2DPlugins> map2DPluginsList) {
        this.map2DPluginsList = map2DPluginsList;
    }


    public List<String> getInstalledPlugins() {
        List<String> list = new ArrayList<String>();
        File rep = new File(plugin_dir +"/");
        String[] listFile;
        listFile = rep.list();
        for( int i = 0 ; i < listFile.length ; i++ ) {
            if(!listFile[i].substring(listFile[i].length() - 4).equals(".txt"))
                list.add(listFile[i].replaceFirst(".jar", ""));
        }
        return list;
    }
}
