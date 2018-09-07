package pa.plugins;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;

public class PluginLoader {

    // Chemin de la liste des plugins
    private String listPluginsPath = "src/pa/plugins_installed/plugin-list.txt";

    // Chamin de la liste des classes
    private ArrayList<Class> listClasses;

    // Listes des chemins des plugins à installer
    private ArrayList<String> pluginsPath;

    private ArrayList<Plugins> listPlugins;

    private PluginManager pluginManager = PluginManager.getInstance();

    public PluginLoader() {
        // Get installed and active plugin
        System.out.println( "0" );
        this.pluginsPath = this.getActivePlugins();
        System.out.println( "1" );
        this.loadPlugins();
        System.out.println( "2" );
        System.out.println( this.pluginsPath );
    }

    public PluginLoader(ArrayList<String> paths) {
        this.pluginsPath = paths;
        this.loadPlugins();
    }

    private void loadPlugins() {

        if (this.pluginsPath.isEmpty()) {
            return;
        }
        try {
            if( this.initializeLoader(pluginsPath) ) {
                Map2DPlugins map2D = PluginManager.getInstance().getMap2DPluginsList().get(0);
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des plugins");
        }
    }


    private boolean initializeLoader(ArrayList<String> pluginsPath) throws MalformedURLException {

        if( pluginsPath.isEmpty() ) {
            System.out.println("Erreur : Aucun fichier ");
            return false;
        }

        URLClassLoader loader;
        String tmp;
        Enumeration enumeration;
        File[] f = new File[pluginsPath.size()];

        for( int i = 0 ; i < pluginsPath.size() ; i++) {
            f[i] = new File( pluginsPath.get(i) );
            if( !f[i].exists() ) {
                System.out.println("Fichier incorrect");
                return false;
            }
            JarFile jar;
            URL url;
            url = f[i].toURL();
            try {

                System.out.println("URL : " + url);
                loader = new URLClassLoader(new URL[] {url});
                jar = new JarFile(f[i].getAbsolutePath());

            } catch ( IOException e ) {
                e.printStackTrace();
                break;
            }

            enumeration = jar.entries();

            Class tmpClass = null;

            String name = f[i].getName();
            String[] array = name.split("\\.");
            name = array[0];

            int j = 0;
            while( enumeration.hasMoreElements() ) {
                tmp = enumeration.nextElement().toString();
                System.out.println("File : " + tmp);
                if( tmp.length() > 6 && tmp.substring(tmp.length() - 6).compareTo(".class") == 0 ) {
                    tmp = tmp.substring(0, tmp.length() - 6);
                    tmp = tmp.replaceAll("/", ".");
                    try {
                        tmpClass = Class.forName(tmp, true, loader);
                        for( int k = 0 ; k < tmpClass.getInterfaces().length ; k++ ) {
                            String map2DInterface = "interface pa.plugins.Map2DPlugins";
                            String CameraInterface = "interface pa.plugins.CameraPlugins";
                            String interfaceName = tmpClass.getInterfaces()[k].toString();
                            if(interfaceName.equals(CameraInterface))  {
                                CameraPlugins pl = (CameraPlugins) tmpClass.getDeclaredConstructor().newInstance();
                                this.pluginManager.getCameraPluginsList().add(pl);
                            } else if (interfaceName.equals(map2DInterface)) {
                                Map2DPlugins pl = (Map2DPlugins) tmpClass.getDeclaredConstructor().newInstance();
                                this.pluginManager.getMap2DPluginsList().add(pl);
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
            }
            try {
                System.out.println("URL3 : " + pluginsPath.get(i));
                if( !pluginsPath.get(i).substring(0, 33).equals("src/pa/plugins/plugins_installed/") ) {
                    savePlugin(url, name);

                }
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return true;
    }


    /**
     * Récupération des plugins actifs
     * @return ArrayList
     */
    public ArrayList<String> getActivePlugins() {
        ArrayList<String> tmpPaths = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(listPluginsPath));
            String line;
            while ( (line = file.readLine()) != null ) {
                if(!line.equals("")) {
                    String tmp = PluginManager.getInstance().getPluginPath(line);
                    tmpPaths.add(tmp);
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occured while reading the file");
        }
        return tmpPaths;
    }

    public boolean savePlugin(URL path, String name) throws IOException {
        PluginManager.getInstance().saveJarFile(path, name);
        PluginManager.getInstance().enablePlugin(name);
        return true;
    }
}
